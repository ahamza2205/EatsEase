package com.example.eatsease.login_signup.presenter.login;

import com.example.eatsease.login_signup.model.repo.NetworkCallback;
import com.example.eatsease.login_signup.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.login_signup.ui.activity.login.ILoginView;
import com.example.eatsease.login_signup.model.auth_manager.FirebaseAuthManager;

public class LoginPresenter implements ILoginPresenter, NetworkCallback {

    private ILoginView loginView;
    private SharedPreRespiratory sharedPreRespiratory;
    private FirebaseAuthManager firebaseAuthManager;

    public LoginPresenter(ILoginView loginView, SharedPreRespiratory sharedPreRespiratory) {
        this.loginView = loginView;
        this.sharedPreRespiratory = sharedPreRespiratory;
        this.firebaseAuthManager = new FirebaseAuthManager();
    }

    @Override
    public void onSuccess() {
        // Save the email and password to preferences after successful login
        sharedPreRespiratory.addToPreferences(firebaseAuthManager.getCurrentUser().getEmail(), "YourPassword"); // Replace "YourPassword" with actual pass if required
        loginView.onSignInSuccess();
    }

    @Override
    public void onFailure(Exception e) {
        loginView.showLoginError(e.getMessage());
    }

    @Override
    public void loginUser(String email, String password) {
        firebaseAuthManager.loginUser(email, password, this);
    }
}
