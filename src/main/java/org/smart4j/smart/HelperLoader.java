package org.smart4j.smart;

/**
 * Created by Dean on 2018/1/3.
 */

import org.smart4j.smart.helper.*;
import org.smart4j.smart.util.ClassUtil;

/**
 * 加载相应的Helper类
 * 实际上当我们第一次访问类时就会加载static块，这里只是为了让类加载更加集中
 */
public final class HelperLoader {

    /**
     * 需要注意的是，AopHelper需要在IocHelper之前加载，首先需要通过AopHelper获取
     * 代理对象，然后才能通过IocHelper进行依赖注入
     */
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
