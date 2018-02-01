package org.smart4j.smart.annotation;

/**
 * Created by Dean on 2018/1/4.
 */

import java.lang.annotation.*;

/**
 * 切面注解
 * 作用目标是：接口、类、枚举、注解
 * 注解会在class字节码文件中存在，在运行时可以通过反射得到
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

	/**
	 * 注解
	 * @return
	 */
	Class<? extends Annotation> value();
}
