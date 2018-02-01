package org.smart4j.smart.helper;

/**
 * Created by Dean on 2018/1/3.
 */

import org.smart4j.smart.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 * 我们需要获取所有被smart框架管理的Bean类，此时需要调用ClassHelper类的getBeanClassSet方法，
 * 随后需要循环调用Reflection类的newInstance方法，根据类来实例化对象，最后将每次创建的对象存放
 * 在一个静态的Map<Class<?>, Object>中。需要随时获取该Map，还需要通过该Map的key(类名)去获
 * 取所对应的value（Bean对象）。
 */
public final class BeanHelper {
    /**
     * 定义Bean映射（用于存放Bean类与Bean映射之间的关系）
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     * 并且屏蔽某些编译时的警告信息
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置Bean实例
     * @param cls
     * @param obj
     */
    public static void setBean(Class<?> cls, Object obj){
        BEAN_MAP.put(cls,obj);
    }
}
