package com.virtualstudios.medistore.utils;

import android.content.res.Resources;

public class Utils {

    public static String getShortName(String name) {
        String[] strings = name.split(" ");//no i18n
        String shortName;
        if (strings.length == 1) {
            shortName = strings[0].substring(0, 2);
        } else {
            shortName = strings[0].substring(0, 1) + strings[1].substring(0, 1);
        }
        return shortName.toUpperCase();
    }

    public static float dpToPixel(int i, Resources resources) {
    }
}
