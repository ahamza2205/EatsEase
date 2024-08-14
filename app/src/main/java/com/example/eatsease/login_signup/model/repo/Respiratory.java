package com.example.eatsease.login_signup.model.repo;

import com.example.eatsease.login_signup.model.auth_manager.FirebaseAuthManager;
import com.example.eatsease.login_signup.model.sharedperferences.SharedPerferencesImp;

public class Respiratory {
    private FirebaseAuthManager firebaseAuthManager;
    private SharedPerferencesImp sharedPerferencesImp;

    public Respiratory(SharedPerferencesImp sharedPerferencesImp) {
        this.sharedPerferencesImp = sharedPerferencesImp;
    }
    public Respiratory() {
        firebaseAuthManager = new FirebaseAuthManager();
    }

    public void createUserWithEmailAndPassword(String email, String password) {
        firebaseAuthManager.createUserWithEmailAndPassword(email, password);
    }
    public void addToPreferences(String email , String pass)
    {
        sharedPerferencesImp.addToPreferences(email , pass);
    }

    public boolean readFromPreferences()
    {
        return sharedPerferencesImp.readFromPreferences();
    }
}

