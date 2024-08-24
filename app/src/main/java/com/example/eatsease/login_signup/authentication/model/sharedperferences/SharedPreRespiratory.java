package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;

public class SharedPreRespiratory {
    private SharedPerferencesImp sharedPerferencesImp;

    private SharedPreRespiratory(Context context) {
        sharedPerferencesImp = SharedPerferencesImp.getInstance(context);
    }

    public static SharedPreRespiratory getInstance(Context context) {
        return new SharedPreRespiratory(context);
    }

    public void addToPreferences(String email, String pass) {
        sharedPerferencesImp.addToPreferences(email, pass);
    }

    public void removeUserDetails() {
        sharedPerferencesImp.removePreferences();
    }

    public boolean isUserLoggedIn() {
        return sharedPerferencesImp.isUserLoggedIn();
    }

    public String getUserEmail() {
        return sharedPerferencesImp.getUserEmail();
    }

    public String getUserPassword() {
        return sharedPerferencesImp.getUserPassword();
    }
}
