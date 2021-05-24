package com.tuta.tutadriver.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.tuta.tutadriver.activity.LoginActivity;
import com.tuta.tutadriver.model.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "TutaDriver";
    private static final String KEY_USERNAME = "KEYUSERMAE";
    private static final String KEY_EMAIL = "KEYEMAIL";
    private static final String KEY_ID = "KEYID";
    private static final String KEY_PHONENUMBER = "KEYPHONENUMBER";
    private static final String KEY_USERTYPE = "KEYUSERTYPE";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getUserId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getUserEmail());
        editor.putString(KEY_PHONENUMBER, user.getUserPhone());
        editor.putString(KEY_USERTYPE, user.getUserType());
        editor.apply();
    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PHONENUMBER, null),
                sharedPreferences.getString(KEY_USERTYPE, null)
                );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}