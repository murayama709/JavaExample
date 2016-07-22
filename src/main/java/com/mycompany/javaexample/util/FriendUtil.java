package com.mycompany.javaexample.util;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class FriendUtil {
    public static List<Friend> getManyFriends() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Arrays.asList(
                new Friend("keishi", 31, Sex.MALE, 26.5, sdf.parse("19850215")),
                new Friend("keishi", 45, Sex.MALE, 27.5, sdf.parse("19760515")),
                new Friend("keishi", 79, Sex.MALE, 28.0, sdf.parse("19420216")),
                new Friend("mari", 31, Sex.FEMALE, 21.5, sdf.parse("19851023")),
                new Friend("yoshiko", 31, Sex.FEMALE, 22.5, sdf.parse("19850824")),
                new Friend("shinya", 31, Sex.MALE, 28.5, sdf.parse("19850830")),
                new Friend("yuki", 22, Sex.FEMALE, 21.0, sdf.parse("19940215")),
                new Friend("yuki", 31, Sex.MALE, 25.5, sdf.parse("19851102")),
                new Friend("shinji", 18, Sex.MALE, 26.5, sdf.parse("19980401")),
                new Friend("keita", 47, Sex.MALE, 25.5, sdf.parse("19791231"))
        );
    }

    public static List<Friend> getFriends() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Arrays.asList(
                new Friend("keishi", 31, Sex.MALE, 26.0, sdf.parse("19850215")),
                new Friend("shinya", 30, Sex.MALE, 25.0, sdf.parse("19860830")),
                new Friend("yuki", 29, Sex.FEMALE, 24.0, sdf.parse("19870215"))
        );
    }
}
