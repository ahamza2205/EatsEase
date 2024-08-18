package com.example.eatsease.login_signup.authentication.model.repo;

import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;

public class AuthRepository {
    private FirebaseAuthManager firebaseAuthManager;

    public AuthRepository() {
        firebaseAuthManager = new FirebaseAuthManager();
    }

    public void createUserWithEmailAndPassword(String email, String password) {
        firebaseAuthManager.createUserWithEmailAndPassword(email, password);
    }

    public void signInApp(String email, String password, NetworkCallback callback) {
        firebaseAuthManager.loginUser(email, password, callback);
    }
}
