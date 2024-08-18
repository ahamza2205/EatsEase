
package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPerferencesImp {
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    private static final String EMAIL_KEY = "Email_key";
    private static final String PASS_KEY = "Pass_key";
    private static final String DEFAULT_VALUE = "N/A";
    private static final String SHARED_PREF_NAME = "mySharedPreference";
    private static SharedPerferencesImp instance = null;

    private SharedPerferencesImp(Context context) {
        this.setting = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = setting.edit();
    }

    public static SharedPerferencesImp getInstance(Context context) {
        if(instance == null) {
            instance = new SharedPerferencesImp(context);
        }
        return instance;
    }

    public void addToPreferences(String email, String pass) {
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASS_KEY, pass);
        editor.commit();
    }

    public void removePreferences() {
        editor.putString(EMAIL_KEY, DEFAULT_VALUE);
        editor.putString(PASS_KEY, DEFAULT_VALUE);
        editor.commit();
    }

    public boolean readFromPreferences() {
        String userEmail = setting.getString(EMAIL_KEY, DEFAULT_VALUE);
        String userPass = setting.getString(PASS_KEY, DEFAULT_VALUE);

        return !userEmail.equals(DEFAULT_VALUE) && !userPass.equals(DEFAULT_VALUE);
    }
}

/*
package com.example.eatsease.login_signup.authentication.model.sharedperferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPerferencesImp implements SharedPerference {
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    private final String emailKey = "Email_key";
    private final String passKey = "Pass_key";
    private final String defultValue = "N/A";
    private final String shPreferenceName = "mySharedPerfernce";
    private static SharedPerferencesImp instance = null;

    private SharedPerferencesImp(Context context)
    {
        this.setting = context.getSharedPreferences(shPreferenceName, Context.MODE_PRIVATE);
        editor = setting.edit();
    }

    public static SharedPerferencesImp getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new SharedPerferencesImp(context);
        }
        return instance;
    }

    public void addToPreferences(String email , String pass)
    {
        editor.putString(emailKey , email);
        editor.putString(passKey , pass);
        editor.commit();
    }

    public void removePreferences()
    {
        editor.putString(emailKey , defultValue);
        editor.putString(passKey , defultValue);
        editor.commit();
    }

    public boolean readFromPreferences()
    {
        String userEmail = setting.getString(emailKey,defultValue);
        String userPass = setting.getString(passKey,defultValue);

        if(userEmail.equals(defultValue) || userPass.equals(defultValue))
        {
            return false;
        }
        else
        {
            return true;
  }
}

}
*/
