package com.example.util;

public class StringFunctions {
    public static String truncate(String input, int length) {
        if (input == null) {
            return "";
        }
        return input.length() > length ? input.substring(0, length) + "..." : input;
    }
}
