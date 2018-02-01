package org.smart4j.smart.helper;

/**
 * Created by Dean on 2018/1/3.
 */

import org.smart4j.smart.annotation.Inject;
import org.smart4j.smart.util.ArrayUtil;
import org.smart4j.smart.util.MapUtil;
import org.smart4j.smart.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * 先通过BeanHelper获取所有Bean Map(是一个Map<Class<?>, Object>结构，记录了类与对象的映射关系)。
 * 然后遍历这个映射关系，分别取出Bean类与Bean实现。进而通过反射获取类中的所有成员变量。继续遍历这些成员
 * 变量，在循环中判断当前成员变量是否具有Inject注解，若带有该注解，则从Bean Map中根据Bean类取出Bean
 * 实例，最后通过ReflectionUtil#setField方法来修改当前成员变量的值。
 *
 * Ioc框架中管理的对象都是单例的。
 */
public final class IocHelper {
    static {
        //获取所有的Bean类和Bean实例之间的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if (MapUtil.isNotEmpty(beanMap)){
            //遍历beanMap
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
                //从BeanMap中获取bean类和bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                //获取bean类所定义的所有成员变量
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)){
                    //遍历bean的field
                    for (Field beanField : fields){
                        //判断当前的Bean field是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)){
                            //在Bean map中获取Bean Field对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
