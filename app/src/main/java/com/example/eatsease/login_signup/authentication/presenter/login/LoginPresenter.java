package com.example.eatsease.login_signup.authentication.presenter.login;

import android.util.Log;

import com.example.eatsease.login_signup.authentication.model.repo.NetworkCallback;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.login_signup.authentication.activity.login.ILoginView;
import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

import io.reactivex.rxjava3.annotations.NonNull;

public class LoginPresenter implements ILoginPresenter, NetworkCallback {

    private ILoginView loginView;
    private SharedPreRespiratory sharedPreRespiratory;
    private FirebaseAuthManager firebaseAuthManager;
    private String email;
    private String password;
    public LoginPresenter(ILoginView loginView, SharedPreRespiratory sharedPreRespiratory) {
        this.loginView = loginView;
        this.sharedPreRespiratory = sharedPreRespiratory;
        this.firebaseAuthManager = new FirebaseAuthManager();
    }

    @Override
    public void onSuccess() {
        // Save the email and password to preferences after successful login
        sharedPreRespiratory.addToPreferences(email, password); // Replace "YourPassword" with actual pass if required
        loginView.onSignInSuccess();
        Log.d("Hamza", "onSuccess: ");
    }

    @Override
    public void onFailure(Exception e) {
        loginView.showLoginError(e.getMessage());
    }

    @Override
    public void loginUser(String email, String password) {
        this.email = email;
        this.password = password;
        firebaseAuthManager.loginUser(email, password, this);
    }

//    @Override
//    public void signInUsingGmailAccount(String idToken, OnLoginWithGmailResponse response) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    response.onLoginWithGmailSuccess();
//                } else {
//                    response.onLoginWithGmailError(Objects.requireNonNull(task.getException()).toString());
//                }
//            }
//        });
//    }
}
