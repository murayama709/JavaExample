/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javaexample.dto;

/**
 *
 * @author murayamakenichirou
 */
public class People {
    public enum Sex {MALE, FEMALE};
    private String name;
    private Integer age;
    private Sex sex;
    public People(String name, Integer age, Sex sex) {
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
}
