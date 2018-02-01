package org.smart4j.smart.demo.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smart.annotation.Aspect;
import org.smart4j.smart.annotation.Service;
import org.smart4j.smart.proxy.AbstractAspect;

import java.lang.reflect.Method;

/**
 * Created by DEAN on 2018/1/11.
 * 拦截被Service注解的类的所有方法
 * 该切面逻辑用于判断方法的执行所花时间
 */
@Aspect(Service.class)
public class ServiceAspect extends AbstractAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    private long begin;
    private long end;

    @Override
    public void beforeAdvice(Class<?> cls, Method method, Object[] params) throws Throwable{
        LOGGER.debug("---------- begin ----------");
        LOGGER.debug(String.format("class: %s", cls.getName()));
        LOGGER.debug(String.format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
        System.out.println("*****切面逻辑执行，当前时间："+begin+" ******");
    }

    @Override
    public void afterAdvice(Class<?> cls, Method method, Object[] params, Object result) throws Throwable{
        end = System.currentTimeMillis()-begin;
        LOGGER.debug(String.format("time：%dms", end));
        LOGGER.debug("---------- end ----------");
        System.out.println("*****该方法执行共花时间："+end+" *****");
    }
}
