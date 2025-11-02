package com.karlsson.methods;

import java.util.Random;

public class Methods {

    public static String generateIdNumber() { //metod för att skapa ett random ID
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
