package com.datagrandeur.neuropsych.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocalUtils {
    public static void setLanguage(String language, Resources resources) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static String getLanguage(String location) {
        switch (location) {

            case "global":
                return "";

            case "colombia":
                return "es";

            case "nepal":
                return "ne";

            case "afrikaans":
                return "af";

            case "isiXhosa":
                return "xh";

            default:
                return "";

        }
    }

}
