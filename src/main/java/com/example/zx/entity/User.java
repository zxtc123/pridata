package com.example.zx.entity;

import java.util.Date;

/**
 * @Auther zhaoxin
 * @Date 2021/1/29 17:30
 */
public class User {
    private String name;
    private String age;
    private Date birthDay;

    public User(String name, String age, Date birthDay) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
