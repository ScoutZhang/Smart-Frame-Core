package org.smart4j.smart.util;

/**
 * Created by liangyue on 2018/1/2.
 */

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * 集合工具类
 */
public class CollectionUtil {
    /**
     * 判断collection是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断collection是否为非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }
}
