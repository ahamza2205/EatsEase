package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;
import android.util.Log;
public class SharedPreRespiratory {
    private SharedPerferencesImp sharedPerferencesImp;

    private SharedPreRespiratory(Context context) {
        sharedPerferencesImp = SharedPerferencesImp.getInstance(context);
    }

    public static SharedPreRespiratory getInstance(Context context) {
        return new SharedPreRespiratory(context);
    }

    public void addToPreferences(String email, String pass) {
        Log.d("hamza", "addToPreferences: " + email + " " + pass);
        sharedPerferencesImp.addToPreferences(email, pass);
    }

    public boolean readFromPreferences() {
        return sharedPerferencesImp.readFromPreferences();
    }
}

