package com.example.eatsease.login_signup.authentication.model.auth_manager;

import android.util.Log;

import com.example.eatsease.login_signup.authentication.model.repo.NetworkCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("AuthManager", "User created: " + user.getEmail());
                    } else {
                        Log.d("AuthManager", "User creation failed");
                    }
                });
    }

    public void loginUser(String email, String password, NetworkCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
    }
}
