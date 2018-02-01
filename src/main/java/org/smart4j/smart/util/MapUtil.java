package org.smart4j.smart.util;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * Created by DEAN on 2018/1/10.
 */
public class MapUtil {
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }
}
