package com.example.eatsease.login_signup.presenter.login;

import android.content.Context;
import android.util.Log;

import com.example.eatsease.login_signup.ui.activity.login.ILoginView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void loginUser(String email, String password, FirebaseAuth auth, Context context) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("LoginPresenter", "Login successful");
                        loginView.navigateToHome();
                    } else {
                        Log.d("LoginPresenter", "Login failed");
                        loginView.showLoginError(task.getException().getMessage());
                    }
                });
    }
}
