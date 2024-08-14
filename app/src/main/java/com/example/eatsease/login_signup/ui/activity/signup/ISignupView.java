package com.example.eatsease.login_signup.ui.activity.signup;

public interface ISignupView {
    void showSignupSuccess();
    void showSignupError(String message);
    void showPasswordsDoNotMatchError();
}
