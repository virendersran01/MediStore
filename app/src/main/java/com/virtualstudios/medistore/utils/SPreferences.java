package com.virtualstudios.medistore.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.virtualstudios.medistore.data.models.User;

import java.util.Random;

public class SPreferences {

    private final String PREF_KEY_SOURCE = "source";
    private final String PREF_KEY_AUTH = "authKey";
    private final String PREF_KEY_REFRESH_TOKEN = "refreshToken";
    private final String PREF_KEY_USER_ID = "userId";
    private final String PREF_KEY_USER_NAME = "username";
    private final String PREF_KEY_USER_FULL_NAME = "userFullName";
    private final String PREF_KEY_USER_EMAIL = "email";
    private final String PREF_KEY_USER_PROFILE_PIC_URL = "profilePicture";
    private final String PREF_KEY_IS_GOOGLE_LOGIN = "isGoogleLogin";
    private final String PREF_KEY_LOGIN_STATUS = "loginStatus";
    private final String PREF_KEY_USER_BUSINESS_NAME = "businessName";
    private final String PREF_KEY_USER_ADDRESS = "address";

    private SharedPreferences mSharedPreferences;

    public SPreferences(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
    }

    public void putSourceDevice(String source){
        mSharedPreferences.edit().putString(PREF_KEY_SOURCE, source).apply();
    }

    public String getSourceDevice() {
        return mSharedPreferences.getString(PREF_KEY_SOURCE, null);
    }

    public void putAuthKey(String authKey){
        mSharedPreferences.edit().putString(PREF_KEY_AUTH, authKey).apply();
    }

    public String getAuthKey() {
        return mSharedPreferences.getString(PREF_KEY_AUTH, null);
    }

    public void putRefreshToken(String refreshToken){
        mSharedPreferences.edit().putString(PREF_KEY_REFRESH_TOKEN, refreshToken).apply();
    }

    public String getRefreshToken() {
        return mSharedPreferences.getString(PREF_KEY_REFRESH_TOKEN, null);
    }

    public void putUserId(String userId){
        mSharedPreferences.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }

    public String getUserId() {
        return mSharedPreferences.getString(PREF_KEY_USER_ID, null);
    }

    public void putUserName(String userName){
        mSharedPreferences.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }

    public String getUserName() {
        return mSharedPreferences.getString(PREF_KEY_USER_NAME, null);
    }

    public void putUserEmail(String userEmail){
        mSharedPreferences.edit().putString(PREF_KEY_USER_EMAIL,userEmail).apply();
    }

    public String getUserEmail(){
        return mSharedPreferences.getString(PREF_KEY_USER_EMAIL,null);
    }

    public void clear(){
        mSharedPreferences.edit().clear().apply();
    }

    public void putIsGoogleLogin(boolean isGoogleLogin){
        mSharedPreferences.edit().putBoolean(PREF_KEY_IS_GOOGLE_LOGIN,isGoogleLogin).apply();
    }

    public boolean isGoogleLogin(){
        return mSharedPreferences.getBoolean(PREF_KEY_IS_GOOGLE_LOGIN, false);
    }

    public void putProfilePicUrl(String profilePicUrl){
        mSharedPreferences.edit().putString(PREF_KEY_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    public String getProfilePicUrl(){
        return mSharedPreferences.getString(PREF_KEY_USER_PROFILE_PIC_URL, null);
    }

    public String getUserFullName() {
        return mSharedPreferences.getString(PREF_KEY_USER_FULL_NAME, null);
    }

    public void setUserFullName(String userFullName){
        mSharedPreferences.edit().putString(PREF_KEY_USER_FULL_NAME, userFullName).apply();
    }

    public boolean getLoginStatus() {
        return mSharedPreferences.getBoolean(PREF_KEY_LOGIN_STATUS, false);
    }

    public void setLoginStatus(boolean loginStatus) {
        mSharedPreferences.edit().putBoolean(PREF_KEY_LOGIN_STATUS, loginStatus).apply();
    }

    public void setBusinessName(String businessName){
        mSharedPreferences.edit().putString(PREF_KEY_USER_BUSINESS_NAME, businessName).apply();
    }

    public String getBusinessName(){
        return mSharedPreferences.getString(PREF_KEY_USER_BUSINESS_NAME, null);
    }

    public void setAddress(String address){
        mSharedPreferences.edit().putString(PREF_KEY_USER_ADDRESS, address).apply();
    }

    public String getAddress(){
        return mSharedPreferences.getString(PREF_KEY_USER_ADDRESS, null);
    }

    public void clearUserData(){
        mSharedPreferences.edit().clear().apply();
    }

    public User getLoggedInUser(){
        if (getLoginStatus()){
            User user = new User();
            user.setName(getUserName());
            Random rnd = new Random();
            user.setColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            return user;
        }
        return null;
    }
}