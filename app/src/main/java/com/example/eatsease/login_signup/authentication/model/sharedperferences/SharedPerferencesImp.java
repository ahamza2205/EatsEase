package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPerferencesImp implements SharedPerference {
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    private final String emailKey = "Email_key";
    private final String passKey = "Pass_key";
    private final String defaultValue = "N/A";
    private final String shPreferenceName = "mySharedPerfernce";
    private static SharedPerferencesImp instance = null;

    private SharedPerferencesImp(Context context) {
        this.setting = context.getSharedPreferences(shPreferenceName, Context.MODE_PRIVATE);
        editor = setting.edit();
    }

    public static SharedPerferencesImp getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPerferencesImp(context);
        }
        return instance;
    }

    public void addToPreferences(String email, String pass) {
        editor.putString(emailKey, email);
        editor.putString(passKey, pass);
        editor.commit();
    }

    public boolean readFromPreferences() {
        String userEmail = setting.getString(emailKey, defaultValue);
        String userPass = setting.getString(passKey, defaultValue);

        return !userEmail.equals(defaultValue) && !userPass.equals(defaultValue);
    }
}
