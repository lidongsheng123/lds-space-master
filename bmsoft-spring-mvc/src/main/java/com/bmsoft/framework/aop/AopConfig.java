package com.bmsoft.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName AopConfig.java
 * @Description 切点信息封装类
 * @createTime 2018年10月17日 15:58:00
 */
public class AopConfig {

    //以目标对象需要增强的Method作为key，需要增强的代码内容作为value
    private Map<Method, Aspect> points = new HashMap<Method, Aspect>();

    public void put(Method target, Object aspect, Method[] points) {
        this.points.put(target, new Aspect(aspect, points));
    }

    public Aspect get(Method method) {
        return this.points.get(method);
    }

    public boolean contains(Method method) {
        return this.points.containsKey(method);
    }


    //对增强的代码的封装
    public class Aspect {
        private Object aspect; //待会将LogAspet这个对象赋值给它
        private Method[] points;//会将LogAspet的before方法和after方法赋值进来

        public Aspect(Object aspect, Method[] points) {
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public Method[] getPoints() {
            return points;
        }
    }
}
