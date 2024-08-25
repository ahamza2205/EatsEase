
package com.example.eatsease.login_signup.authentication.model.sharedperferences;
public interface SharedPerference {
    public void addToPreferences(String email , String pass);
    public boolean readFromPreferences();
}