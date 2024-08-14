package com.example.eatsease.login_signup.model.repo;

import com.example.eatsease.login_signup.model.auth_manager.FirebaseAuthManager;

public class SplashRepo {
    private FirebaseAuthManager firebaseAuthManager;

    public SplashRepo() {
        firebaseAuthManager = new FirebaseAuthManager();
    }

   public void createUserWithEmailAndPassword(String email, String password) {
       firebaseAuthManager.createUserWithEmailAndPassword(email, password);
   }

}
