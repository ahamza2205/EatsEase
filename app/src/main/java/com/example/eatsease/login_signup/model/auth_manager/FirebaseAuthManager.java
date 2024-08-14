package com.example.eatsease.login_signup.model.auth_manager;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class FirebaseAuthManager {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Hamza", "createUserWithEmailAndPassword: ");
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        Log.d("Hamza", "createUserWithEmailAndPassword:2 ");
                    }
                });
    }

    public void loginUser(String email, String password) {
         mAuth.signInWithEmailAndPassword(email,
                        password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Hamza", "loginUser: ");
                    } else {
                        Log.d("Hamza", "loginUser:2 ");
                    }
                });
    }


    public void signOut() {
        mAuth.signOut();
    }

    public void signInAnonymously() {
        mAuth.signInAnonymously();
    }
}


