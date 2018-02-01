package org.smart4j.smart.demo.service;

import org.smart4j.smart.annotation.Service;
import org.smart4j.smart.demo.domain.Member;
import org.smart4j.smart.helper.DatabaseHelper;

import java.sql.ResultSet;

/**
 * Created by DEAN on 2018/1/11.
 */
@Service
public class WelcomeService {
    public String getUserName(){
        System.out.println("*****Inject success*****");
        return "scout";
    }

    public Member getMemberInfo(){
        Member member=new Member();
        ResultSet rs=null;
        try{
            String sql = "select username, name, age from member where username = 'zhangshuhao'";
            rs = DatabaseHelper.executeSql(sql);
            while(rs.next()){
                member.setUsername((String)rs.getObject("username"));
                member.setName((String)rs.getObject("name"));
                member.setAge((Integer)rs.getObject("age"));
            }
        }catch(Exception e){
            System.out.println("-*-*-*-*-* sql异常 *-*-*-*-*-");
            e.printStackTrace();
        }
        System.out.println("***** member: "+member+" *****");
        return member;
    }
}
