/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javaexample.util;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author murayamakenichirou
 */
public class FriendUtil {
    public static List<Friend> getFriends() {
        return Arrays.asList(
                new Friend("keishi", 31, Sex.MALE),
                new Friend("keishi", 45, Sex.MALE),
                new Friend("keishi", 79, Sex.MALE),
                new Friend("mari", 31, Sex.FEMALE),
                new Friend("yoshiko", 31, Sex.FEMALE),
                new Friend("shinya", 31, Sex.MALE),
                new Friend("yuki", 22, Sex.FEMALE),
                new Friend("yuki", 31, Sex.MALE),
                new Friend("shinji", 18, Sex.MALE),
                new Friend("keita", 47, Sex.MALE)
        );
    }
}
