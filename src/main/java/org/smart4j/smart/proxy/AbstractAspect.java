package org.smart4j.smart.proxy;

/**
 * Created by Dean on 2018/1/4.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理
 * 在该类中，需要在目标方法被调用前后增加相应的逻辑。
 * 这是一个抽象类，提供了模板方法，在该抽象类的具体实现中扩展相应的抽象方法。
 */
public abstract class AbstractAspect implements Aspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAspect.class);

	public final Object doAspect(AspectChain aspectChain) throws Throwable{
		Object result = null;
		Class<?> cls = aspectChain.getTargetClass();
		Method method = aspectChain.getTargetMethod();
		Object[] params = aspectChain.getMethodParams();
		begin();
		try {
			if (intercept(cls, method, params)){
				beforeAdvice(cls, method, params);
				result = aspectChain.dealWithAspect();
				afterAdvice(cls, method, params, result);
			}
			else {
				result = aspectChain.dealWithAspect();
			}
		} catch (Exception e) {
			LOGGER.error("proxy failure", e);
			error(cls, method, params, e);
		}finally {
			end();
		}
		return result;
	}

	public void begin(){

	}

	public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
		return true;
	}

	public void beforeAdvice(Class<?> cls, Method method, Object[] params) throws  Throwable{

	}

	public void afterAdvice(Class<?> cls, Method method, Object[] params, Object result) throws  Throwable{

	}

	public void error(Class<?> cls, Method method, Object[] params, Throwable e){

	}

	public void end(){

	}
}
