package org.smart4j.smart.demo.controller;

import org.smart4j.smart.annotation.Action;
import org.smart4j.smart.annotation.Controller;
import org.smart4j.smart.annotation.Inject;
import org.smart4j.smart.bean.Data;
import org.smart4j.smart.bean.Param;
import org.smart4j.smart.bean.View;
import org.smart4j.smart.demo.domain.Member;
import org.smart4j.smart.demo.service.WelcomeService;
import org.smart4j.smart.demo.vo.User;
import org.smart4j.smart.util.CastUtil;

import java.util.Map;

/**
 * Created by DEAN on 2018/1/10.
 */
@Controller
public class WelcomeController {
    @Inject
    private WelcomeService welcomeService;

    @Action("get:/welcomeTest")
    public View welcomeTest(Param param){
        long viewId = param.getLong("viewId");
        if(viewId==1){
            System.out.println("*****welcome.jsp*****");
            //当viewId为1时，返回welcome.jsp页面
            String name = welcomeService.getUserName();
            return new View("welcome.jsp").addModel("name",name);
        }else{
            //其它情况返回index.jsp页面（通过重定向方式）
            System.out.println("*****index.jsp*****");
            return new View("/index.jsp");
        }
    }

    @Action("get:/testJson")
    public Data testJson(Param param) {
        //接受特定的传入参数：name和age
        Map<String, Object> paramMap = param.getParamMap();
        System.out.println("*****JSON return*****");
        String name = (String)paramMap.get("name");
        int age = CastUtil.castInt(paramMap.get("age"));
        User user = new User(name,age);

        return new Data(user);
    }

    @Action("get:/testDb")
    public Data testDb(Param param) {
        Member member = welcomeService.getMemberInfo();
        String name = member.getName();
        int age = member.getAge();
        User user = new User(name,age);
        System.out.println("*****username: "+member.getUsername()+"*****");

        return new Data(user);
    }
}
