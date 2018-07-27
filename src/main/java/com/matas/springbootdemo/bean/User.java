package com.matas.springbootdemo.bean;

import java.io.Serializable;

/**
 * @author matas
 * @date 2018/7/20 10:40
 * @email mataszhang@163.com
 */
public class User implements Serializable {

    private static final long serialVersionUID = -234119105457234320L;
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
