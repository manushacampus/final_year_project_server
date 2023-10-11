package com.bit.final_project.commons;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {
    public static Date nowAsDate() {
        return new Date();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate asDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate asDate(String date) {
        if(date == null) return null;
        return LocalDate.parse(date);
    }

    public static LocalTime asTime(String time) {
        return LocalTime.parse(time);
    }

    public static LocalDateTime asDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalTime asTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String toString(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public static LocalDate todayDate() {
        Date date = new Date();
        return asDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}
