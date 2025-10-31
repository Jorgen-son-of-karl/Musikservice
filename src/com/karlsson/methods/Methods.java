package com.karlsson.methods;

import com.karlsson.entity.member.Member;
import com.karlsson.entity.member.MembershipLevel;

import java.util.Random;
import java.util.Scanner;

import static com.karlsson.data.Data.members;

public class Methods {

    public static String generateIdNumber() {
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 5) {
            int index = (int) (rnd.nextFloat() * charList.length());
            str.append(charList.charAt(index));
        }
        return str.toString();
    }
}
