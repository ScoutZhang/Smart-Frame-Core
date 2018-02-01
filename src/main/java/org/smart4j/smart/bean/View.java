package org.smart4j.smart.bean;

/**
 * Created by Dean on 2018/1/3.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象
 * 从Handler对象获取Action方法的返回值，若返回值是View类型的视图对象，
 * 则返回一个JSP页面
 */
public class View {
    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public View(String path){
        this.path = path;
        this.model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
