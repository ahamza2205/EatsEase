package com.example.eatsease.login_signup.authentication.activity.signup;

public interface ISignupView {
    void showSignupSuccess();
    void showSignupError(String message);
    void showPasswordsDoNotMatchError();
}
