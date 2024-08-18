package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;

public class SharedPreRespiratory {
    private SharedPerferencesImp sharedPerferencesImp;

    public SharedPreRespiratory(Context context) {
        sharedPerferencesImp = SharedPerferencesImp.getInstance(context);
    }

    public void addToPreferences(String email, String pass) {
        sharedPerferencesImp.addToPreferences(email, pass);
    }

    public boolean readFromPreferences() {
        return sharedPerferencesImp.readFromPreferences();
    }
}
