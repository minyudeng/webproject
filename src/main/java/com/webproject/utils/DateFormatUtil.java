package com.webproject.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {
    public static String formatTo(LocalDateTime originDate, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedDateTime = originDate.format(formatter);
        return formattedDateTime;
    }
}
