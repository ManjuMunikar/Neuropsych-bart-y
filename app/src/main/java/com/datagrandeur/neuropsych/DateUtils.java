package com.datagrandeur.neuropsych;

import android.text.format.DateFormat;

import java.util.Calendar;

public class DateUtils {
    public static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        CharSequence dateFormat = DateFormat.format("yyyy-MM-dd HH:mm:ss", calendar);
        return dateFormat.toString();
    }
}