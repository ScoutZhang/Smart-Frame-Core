package org.smart4j.smart.proxy;

/**
 * Created by liangyue on 2018/1/4.
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理类
 *
 */
public class ProxyManager {

	/**
	 * 提供创建代理对象的方法，输入一个目标类和一组Proxy接口实现，输出一个代理对象
	 * 使用Enhancer#create方法来创建代理对象，将intercept的参数传入ProxyChain
	 * @param targetClass
	 * @param aspectList
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass, final List<Aspect> aspectList){
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {
			public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
				return new AspectChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, aspectList).dealWithAspect();
			}
		});
	}

}
