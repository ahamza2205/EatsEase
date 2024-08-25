package com.example.eatsease.login_signup.authentication.model.auth_manager;

import android.util.Log;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.GoogleAuthProvider;

public class FirebaseAuthManager {

    private static final String TAG = "FirebaseAuthManager";
    private FirebaseAuth mAuth;

    // Singleton instance
    private static FirebaseAuthManager instance;

    // Private constructor to prevent instantiation
    private FirebaseAuthManager() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    // Public method to provide access to the Singleton instance
    public static synchronized FirebaseAuthManager getInstance() {
        if (instance == null) {
            instance = new FirebaseAuthManager();
        }
        return instance;
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void createUserWithEmailAndPassword(String email, String password, NetworkCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "User created: " + (user != null ? user.getEmail() : "Unknown"));
                        callback.onSuccess();
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Log.d(TAG, "User creation failed: " + errorMessage);
                        callback.onFailure(new Exception(errorMessage));
                    }
                });
    }

    public void loginUser(String email, String password, NetworkCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "User logged in: " + email);
                        callback.onSuccess();
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Log.d(TAG, "Login failed: " + errorMessage);
                        callback.onFailure(new Exception(errorMessage));
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
        Log.d(TAG, "User signed out");
    }

    public void signInAnonymously(NetworkCallback callback) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Signed in anonymously");
                        callback.onSuccess();
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Log.d(TAG, "Anonymous sign-in failed: " + errorMessage);
                        callback.onFailure(new Exception(errorMessage));
                    }
                });
    }

//    public void linkWithGoogle(GoogleSignInAccount googleAccount, NetworkCallback callback) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(googleAccount.getIdToken(), null);
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if (currentUser != null) {
//            currentUser.linkWithCredential(credential)
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Account linked with Google");
//                            callback.onSuccess();
//                        } else {
//                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
//                            Log.d(TAG, "Linking failed: " + errorMessage);
//                            callback.onFailure(new Exception(errorMessage));
//                        }
//                    });
//        } else {
//            Log.d(TAG, "No current user to link with Google");
//            callback.onFailure(new Exception("No current user"));
//        }
//    }
}
