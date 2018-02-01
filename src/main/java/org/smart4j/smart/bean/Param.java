package org.smart4j.smart.bean;

/**
 * Created by Dean on 2018/1/3.
 */

import org.smart4j.smart.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     * @param name
     * @return
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段的信息
     * @return
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
