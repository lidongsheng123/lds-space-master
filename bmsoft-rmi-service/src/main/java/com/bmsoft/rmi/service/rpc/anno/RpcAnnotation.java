package com.bmsoft.rmi.service.rpc.anno;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcAnnotation {

    /**
     * 对外发布的服务的接口地址
     * @return
     */
    Class<?> value();

    String version() default "";

}
