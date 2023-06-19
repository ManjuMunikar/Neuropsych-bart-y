package com.datagrandeur.neuropsych;

import android.text.format.DateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class DateUtils {
    public static LocalDateTime getCurrentDateTime() {

        return LocalDateTime.now();
    }
    public static Long getDifferenceInMillisecond(LocalDateTime firstDateTime, LocalDateTime secondDateTime){
        return ChronoUnit.MILLIS.between(firstDateTime,secondDateTime);

    }
    public static String getFormatDateTime(LocalDateTime localDateTime){

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ms");
        String formatDateTime= localDateTime.format(formatter);
        return formatDateTime.toString();
    }
}