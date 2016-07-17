/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javaexample.dto;

import com.mycompany.javaexample.type.Sex;
import java.util.Objects;

/**
 *
 * @author murayamakenichirou
 */
public class Friend {
    private String name;
    private Integer age;
    private Sex sex;
    public Friend(String name, Integer age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name : ").append(name).append("; ")
                .append("age : ").append(age).append("; ")
                .append("sex : ").append(sex);
        return sb.toString();
    }
    
}
