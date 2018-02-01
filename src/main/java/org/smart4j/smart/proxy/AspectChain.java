package org.smart4j.smart.proxy;

/**
 * Created by Dean on 2018/1/4.
 */

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理链
 * 所谓链式代理，就是讲多个代理通过一条链子串起来，一个一个去执行，执行顺序取决于添加到链上的先后顺序。
 */
public class AspectChain {
	//	目标类
	private final Class<?> targetClass;

	//	目标对象
	private final Object targetObject;

	//	目标方法
	private final Method targetMethod;

	//	方法代理，CGLib提供的方法代理对象
	private final MethodProxy methodProxy;

	//	方法参数
	private final Object[] methodParams;

	private List<Aspect> aspectList = new ArrayList<Aspect>();

	//代理对象计数器
	private int proxyIndex = 0;

	public AspectChain(Class<?> targetClass, Object targetObject,
                       Method targetMethod, MethodProxy methodProxy,
                       Object[] methodParams, List<Aspect> aspectList) {
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.aspectList = aspectList;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}

	public Object dealWithAspect() throws Throwable{
		Object methodResult;
		//若计数器未达到proxyList的上限，则从proxyList中取出相应的Proxy对象，并调用其doProxy方法
		//在Proxy的接口实现中会提供相应的横切逻辑，并调用doProxyChain方法，（即随后将再次调用当前对象
		//的doProxyChain方法），直到达到上限为止，最后调用methodProxy的invokeSuper方法，执行目标对象
		//的业务逻辑。
		if (proxyIndex < aspectList.size()){
			methodResult = aspectList.get(proxyIndex++).doAspect(this);
		}
		else {
			methodResult = methodProxy.invokeSuper(targetObject, methodParams);
		}
		return methodResult;
	}
}
