package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    private StringUtils() { }

    public static String escapeCommasInQuotes(String json) {
        // Regex to find quoted text
        String regex = "\"([^\"]*)\""; // Matches any sequence of characters inside double quotes
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        StringBuffer result = new StringBuffer();

        // Iterate through each match (quoted text) found
        while (matcher.find()) {
            // Get the matched quoted string (group 1)
            String quotedText = matcher.group(1);

            // Replace commas inside the quoted text with \,
            String escapedQuotedText = quotedText.replace(",", "\\,");

            // Replace the original quoted text with the escaped version
            matcher.appendReplacement(result, "\"" + escapedQuotedText + "\"");
        }

        // Append the remaining part of the string after the last match
        matcher.appendTail(result);

        return result.toString();
    }
}
