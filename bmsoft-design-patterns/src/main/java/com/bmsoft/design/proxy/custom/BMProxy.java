package com.bmsoft.design.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: lds-space-master
 * @description: 生成代理类对象
 * @author: 李东升
 * @create: 2019-02-28 14:57
 * <p>
 * 动态代理实际上就是字节码重组
 **/
public class BMProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(BMClassLoader classLoader, Class<?>[] interfaces, BMInvocationHandler h) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1、动态生成源代码.java文件
        String src = generateSrc(interfaces);

        //2、Java文件输出磁盘
        String filePath = BMProxy.class.getResource("").getPath();
        File f = new File(filePath + "$Proxy0.java");
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();

        //3、把生成的.java文件编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(f);
        JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
        task.call();
        manager.close();
        //4、编译生成的.class文件加载到JVM中来
        Class proxyClass =  classLoader.findClass("$Proxy0");

        Constructor c = proxyClass.getConstructor(BMInvocationHandler.class);
        /*f.delete();*/

        //5、返回字节码重组以后的新的代理对象
        return c.newInstance(h);

    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.bmsoft.design.proxy.custom;"+ln);
        sb.append("import com.bmsoft.design.proxy.PerSon;" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
            sb.append("BMInvocationHandler h;" + ln);
                sb.append(" public $Proxy0(BMInvocationHandler h) {" + ln);
                     sb.append("this.h = h;");
                sb.append("}" + ln);
            for (Method m : interfaces[0].getMethods()){
                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "() {" + ln);
                    sb.append("try{" + ln);
                            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{});" + ln);
                            sb.append("this.h.invoke(this,m,null);" + ln);
                        sb.append("}catch(Throwable e){" + ln);
                        sb.append("e.printStackTrace();" + ln);
                    sb.append("}");
                sb.append("}");
            }
            sb.append("}"+ln);
        return sb.toString();
    }

}
