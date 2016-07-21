package com.mycompany.javaexample.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mycompany.javaexample.type.Sex;

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
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
