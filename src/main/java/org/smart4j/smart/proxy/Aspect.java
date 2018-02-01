package org.smart4j.smart.proxy;

/**
 * Created by Dean on 2018/1/4.
 */

/**
 * 代理接口
 */
public interface Aspect {
	/**
	 * 执行链式代理
	 * @param aspectChain
	 * @return
	 * @throws Throwable
	 */
	Object doAspect(AspectChain aspectChain) throws Throwable;
}
