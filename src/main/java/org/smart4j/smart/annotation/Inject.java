package org.smart4j.smart.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Dean on 2018/1/3.
 */

/**
 * 依赖注入注解
 * 作用目标是字段、枚举的常量
 * 注解会在class字节码文件中存在，在运行时可以通过反射得到
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
