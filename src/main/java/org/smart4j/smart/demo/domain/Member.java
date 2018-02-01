package org.smart4j.smart.demo.domain;

/**
 * Created by DEAN on 2018/1/11.
 */
public class Member {
    private String username;
    private String name;
    private int age;

    public Member() {
    }

    public Member(String username, String name, int age) {
        this.username = username;
        this.name = name;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "member{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
