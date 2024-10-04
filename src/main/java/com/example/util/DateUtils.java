package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.constants.Constants;

public final class DateUtils {

    private DateUtils() {
    }

    public static String format(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(Constants.ARTICLES_DATETIME_FORMAT));
    }

    public static LocalDateTime from(String dateString) {
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern(Constants.ARTICLES_DATETIME_FORMAT);
        return LocalDateTime.parse(dateString, dFormatter);
    }
}
