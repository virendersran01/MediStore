package com.virtualstudios.medistore.utils;

import android.content.Context;

public class Constants {

    public static final String TAG_FRAGMENT_SIGN_IN = "signIn";
    public static final String TAG_FRAGMENT_SIGN_UP = "signUp";
    public static final String TAG_FRAGMENT_FORGOT_PASSWORD = "forgotPassword";

    private static SPreferences sPreferences;

    public static SPreferences getSPreferences(Context context){
        if(sPreferences == null){
            sPreferences = new SPreferences(context);
        }
        return sPreferences;
    }
}