package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches("\\d{10}", phoneNumber);
    }


    public static boolean isValidDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
