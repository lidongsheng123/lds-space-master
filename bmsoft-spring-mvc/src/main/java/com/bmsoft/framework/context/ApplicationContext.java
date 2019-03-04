package com.bmsoft.framework.context;

import com.bmsoft.framework.annotation.Autowired;
import com.bmsoft.framework.annotation.Controller;
import com.bmsoft.framework.annotation.Service;
import com.bmsoft.framework.aop.AopConfig;
import com.bmsoft.framework.beans.BeanDefinition;
import com.bmsoft.framework.beans.BeanPostProcessor;
import com.bmsoft.framework.beans.BeanWrapper;
import com.bmsoft.framework.context.support.BeanDefinitionReader;
import com.bmsoft.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName ApplicationContext.java
 * @Description TODO
 * @createTime 2018年10月17日 12:44:00
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    //用来存入的XML 文件
    private String[] configLocations;

    private BeanDefinitionReader reader;

    //用来保证注册式单例的容器
    private Map<String, Object> beanCacheMap = new ConcurrentHashMap<>();

    //用来存储所有的被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();


    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public void refresh() {
        //定位
        this.reader = new BeanDefinitionReader(configLocations);

        //加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        //注册
        doRegisty(beanDefinitions);

        //依赖注入（lazy-init = false），要是执行依赖注入
        //在这里自动调用getBean方法
        doAutowrited();

    }

    //开始执行自动化的依赖注入
    private void doAutowrited() {

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }

        //暴力注入 此方法待优化
        for (Map.Entry<String, BeanWrapper> beanWrapperEntry : this.beanWrapperMap.entrySet()) {
            populateBean(beanWrapperEntry.getValue().getOriginalInstance());
        }

    }


    /**
     * 功能描述: 依赖注入
     *
     * @param instance
     * @return: void
     * @auther: Mr.li
     * @date: 2018/10/19 9:42
     */
    public void populateBean(Object instance) {

        Class clazz = instance.getClass();

        //不是所有牛奶都叫特仑苏
        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            Autowired autowired = field.getAnnotation(Autowired.class);

            String autowiredBeanName = autowired.value().trim();

            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            field.setAccessible(true);

            try {
                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 真正的将BeanDefinitions注册到beanDefinitionMap中
     */
    private void doRegisty(List<String> beanDefinitions) {
        //beanName有三种情况:
        //1、默认是类名首字母小写
        //2、自定义名字
        //3、接口注入
        try {
            for (String className : beanDefinitions) {

                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，是不能实例化的
                //用它实现类来实例化
                if (beanClass.isInterface()) {
                    continue;
                }
                //通过类名将类信息存入对象中
                BeanDefinition beanDefinition = reader.registerBean(className);

                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }

                //将此类的接口 父类跟此类相关联
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖这个时候，可以自定义名字
                    this.beanDefinitionMap.put(i.getName(), beanDefinition);
                }
                //到这里为止，容器初始化完毕
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础）
    @Override
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        try {
            //生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

            //根据类信息获取类对象
            Object instance = instantionBean(beanDefinition);

            if (null == instance) {
                return null;
            }

            //在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            //实现动态代理部分
            BeanWrapper beanWrapper = new BeanWrapper(instance);

            beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));

            //设置通知
            beanWrapper.setPostProcessor(beanPostProcessor);

            this.beanWrapperMap.put(beanName, beanWrapper);

            //在实例初始化以后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);

            //populateBean(instance);

            //通过这样一调用，相当于给我们自己留有了可操作的空间   获取代理对象
            return this.beanWrapperMap.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private AopConfig instantionAopConfig(BeanDefinition beanDefinition) throws Exception {
        AopConfig config = new AopConfig();
        String expression = reader.getConfig().getProperty("pointCut");
        String[] before = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = reader.getConfig().getProperty("aspectAfter").split("\\s");

        String className = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(className);

        Pattern pattern = Pattern.compile(expression);

        Class aspectClass = Class.forName(before[0]);
        //在这里得到的方法都是原生的方法
        for (Method m : clazz.getMethods()) {
            //public .* com.bmsoft.demo.service.service\..*Service\..*\(.*\)
            Matcher matcher = pattern.matcher(m.toString());
            if (matcher.matches()) {
                //能满足切面规则的类，添加的AOP配置中
                config.put(m, aspectClass.newInstance(), new Method[]{aspectClass.getMethod(before[1]), aspectClass.getMethod(after[1])});
            }
        }
        return config;
    }

    //传一个BeanDefinition，就返回一个实例Bean
    private Object instantionBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            //因为根据Class才能确定一个类是否有实例
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.getBeanDefinitionCount()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
