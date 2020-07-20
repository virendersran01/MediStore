package com.virtualstudios.medistore.data.volley;
import android.content.Context;

import com.virtualstudios.medistore.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class ApiUrls {

    private static final String serverPrimaryDomain = "https://rs.chemist.techneyo.com/";
    private static final String apiVersion = "web/v1/";
    public static final String urlLogin = serverPrimaryDomain + apiVersion + "auth/login";
    public static final String urlSignUp = serverPrimaryDomain + apiVersion + "auth/signup";
    public static final String urlForgotPassword = serverPrimaryDomain + apiVersion + "auth/change-password";
    public static final String urlUserDetails = serverPrimaryDomain + apiVersion + "user/detail";
    public static final String urlUserUploadImage = serverPrimaryDomain + apiVersion + "user/upload-image";
    public static final String urlUserProfileImage = serverPrimaryDomain + apiVersion + "user/get-image";
    private static final String authPrefix = "Bearer ";

    public static Map<String, String> getHeaders(Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authPrefix + Constants.getSPreferences(context).getAuthKey());
        headers.put("source", Constants.getSPreferences(context).getSourceDevice());
        return headers;
    }
}