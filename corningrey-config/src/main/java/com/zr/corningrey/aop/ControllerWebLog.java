package com.zr.corningrey.aop;

import java.lang.annotation.*;

/**
 * @author heqifeng
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControllerWebLog {
    //所调用接口的名称
    String name();

    //标识该条操作日志是否需要持久化存储
    boolean intoDb() default false;

}