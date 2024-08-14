package com.example.eatsease.login_signup.presenter.login;

import android.content.Context;
import android.widget.Toast;

import com.example.eatsease.login_signup.ui.activity.login.ILoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void loginUser(String email, String password, FirebaseAuth auth, Context context) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show();
                    // Optionally navigate to another activity
                }
            } else {
                if (loginView != null) {
                    loginView.showLoginError(task.getException().getMessage());
                }
            }
        });
    }
}
