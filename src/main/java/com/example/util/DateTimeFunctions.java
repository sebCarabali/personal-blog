package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFunctions {

    public static String formatDateTime(String pubTime, String pattern) {
        LocalDateTime dateTime = LocalDateTime.parse(pubTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}
