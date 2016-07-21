package com.mycompany.javaexample.util;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;
import java.util.Arrays;
import java.util.List;

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
