package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.rxjava3.annotations.Nullable;

public class SharedPerferencesImp {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String EMAIL_KEY = "Email_key";
    private static final String PASS_KEY = "Pass_key";
    private static final String SHARED_PREF_NAME = "mySharedPreference";
    private static SharedPerferencesImp instance = null;

    private SharedPerferencesImp(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public static SharedPerferencesImp getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPerferencesImp(context);
        }
        return instance;
    }

    public void addToPreferences(String email, @Nullable String pass) {
        editor.putString(EMAIL_KEY, email);
        if (pass != null) {
            editor.putString(PASS_KEY, pass);
        }
        editor.apply(); // Use apply() instead of commit() for asynchronous saving
    }

    public void removePreferences() {
        editor.remove(EMAIL_KEY);
        editor.remove(PASS_KEY);
        editor.apply(); // Use apply() instead of commit() for asynchronous saving
    }

    public boolean isUserLoggedIn() {
        // Check if email exists in preferences to determine login status
        return sharedPreferences.contains(EMAIL_KEY);
    }

    public String getUserEmail() {
        return sharedPreferences.getString(EMAIL_KEY, "N/A"); // Return null if no email is saved
    }

    public String getUserPassword() {
        return sharedPreferences.getString(PASS_KEY, null); // Return null if no password is saved
    }
}
