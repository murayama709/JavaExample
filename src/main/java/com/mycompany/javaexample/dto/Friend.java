package com.mycompany.javaexample.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mycompany.javaexample.type.Sex;

public class Friend {
    private String name;
    private Integer age;
    private Sex sex;
    private Double shoeSize;
    private Date birthDay;
    public Friend(String name, Integer age, Sex sex, Double shoeSize, Date birthDay) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.shoeSize = shoeSize;
        this.birthDay = birthDay;
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
    public Double getShoeSize() {
        return shoeSize;
    }
    public void setShoeSize(Double shoeSize) {
        this.shoeSize = shoeSize;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
