package org.smart4j.smart.util;

/**
 * Created by liangyue on 2018/1/2.
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否为非空
     */
    public static boolean isNotEmpty(String str){
        return ! isEmpty(str);
    }

    /**
     * 将字符串分割
     * @param str
     * @param delimeter
     * @return
     */
    public static String[] split(String str, String delimeter){
        return StringUtils.split(str, delimeter);
    }
}
