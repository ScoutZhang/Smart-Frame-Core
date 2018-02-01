package org.smart4j.smart.annotation;

/**
 * Created by Dean on 2018/1/3.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action方法注解
 * 注解的作用目标是方法
 * 注解会在class字节码文件中存在，在运行时可以通过反射得到
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 请求类型与路径
     * @return
     */
    String value();
}
