package org.smart4j.smart.helper;

/**
 * Created by Dean on 2018/1/3.
 */

import org.smart4j.smart.annotation.Action;
import org.smart4j.smart.bean.Handler;
import org.smart4j.smart.bean.Request;
import org.smart4j.smart.util.ArrayUtil;
import org.smart4j.smart.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 * 在ControllerHelper中封装了一个ActionMap,通过它来存放Request与Handler之间的关系，
 * 然后通过ClassHelper来获取所有带有Controller的注解，接着遍历这些Controller类，从Action
 * 注解中提取URL，最后初始化Request与Handler之间的映射关系。
 */
public final class ControllerHelper {

    /**
     * 用于存放请求与处理器之间的映射关系
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        //获取所有的controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            //遍历controller集合中的所有类
            for (Class<?> controllerClass : controllerClassSet){
                //获取当前controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    //遍历当前controller类中的所有方法
                    for (Method method : methods){
                        //判断当前的方法是否带有Action注解
                        if (method.isAnnotationPresent(Action.class)){
                            //从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                                    //获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    System.out.println("*****"+requestMethod+ " " +requestPath+"*****");
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

    public static Map<Request, Handler> getActionMap() {
        return ACTION_MAP;
    }
}
