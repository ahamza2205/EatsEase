package com.example.eatsease.login_signup.authentication.presenter.login;

import com.example.eatsease.login_signup.authentication.model.auth_manager.NetworkCallback;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.login_signup.authentication.activity.login.ILoginView;
import com.example.eatsease.login_signup.authentication.activity.login.OnLoginWithGmailResponse;
import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginPresenter implements ILoginPresenter, NetworkCallback {

    private ILoginView loginView;
    private SharedPreRespiratory sharedPreRespiratory;
    private FirebaseAuthManager firebaseAuthManager;
    private String email;
    private String password;

    public LoginPresenter(ILoginView loginView, SharedPreRespiratory sharedPreRespiratory) {
        this.loginView = loginView;
        this.sharedPreRespiratory = sharedPreRespiratory;
        this.firebaseAuthManager = FirebaseAuthManager.getInstance();
    }

    @Override
    public void loginUser(String email, String password) {
        this.email = email;
        this.password = password;
        firebaseAuthManager.loginUser(email, password, this);
    }

    public void signInUsingGmailAccount(String idToken, OnLoginWithGmailResponse listener) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onLoginWithGmailSuccess();
            } else {
                listener.onLoginWithGmailError(Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

    @Override
    public void onSuccess() {
        sharedPreRespiratory.addToPreferences(email, password);
        loginView.onSignInSuccess();
    }

    @Override
    public void onFailure(Exception e) {
        loginView.showLoginError(e.getMessage());
    }

    public void signInAnonymously() {
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Save guest information to SharedPreferences
                String guestEmail = "guest@example.com"; // You can customize this as needed
                sharedPreRespiratory.addToPreferences(guestEmail, "hamza");
                loginView.onSignInSuccess();
            } else {
                // Handle error
                loginView.showLoginError(Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

}
