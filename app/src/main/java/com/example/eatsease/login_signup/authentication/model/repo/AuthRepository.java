package com.example.eatsease.login_signup.authentication.model.repo;

import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class AuthRepository {
    private FirebaseAuthManager firebaseAuthManager;

    public AuthRepository() {
        // Correct way to get the instance of FirebaseAuthManager
        firebaseAuthManager = FirebaseAuthManager.getInstance();
    }

    public void createUserWithEmailAndPassword(String email, String password) {
        firebaseAuthManager.createUserWithEmailAndPassword(email, password, null);
    }

    public void signInApp(String email, String password, NetworkCallback callback) {
        firebaseAuthManager.loginUser(email, password, callback);
    }
    public void signInAnonymously(NetworkCallback callback) {
        firebaseAuthManager.signInAnonymously(callback);
    }

    public void linkWithGoogle(GoogleSignInAccount googleAccount, NetworkCallback callback) {
        firebaseAuthManager.linkWithGoogle(googleAccount, callback);
    }
}
