package org.smart4j.smart.bean;

/**
 * Created by Dean on 2018/1/3.
 */

/**
 * 返回数据对象
 * 从Handler对象获取Action方法的返回值，若返回值是Data类型的数据对象，
 * 则返回一个JSON数据
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
