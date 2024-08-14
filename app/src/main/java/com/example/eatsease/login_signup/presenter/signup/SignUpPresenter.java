package com.example.eatsease.login_signup.presenter.signup;

import com.example.eatsease.login_signup.ui.activity.signup.ISignupView;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenter {
    private ISignupView signupView;
    private FirebaseAuth auth;

    public SignUpPresenter(ISignupView signupView) {
        this.signupView = signupView;
        this.auth = FirebaseAuth.getInstance(); // Initialize Firebase Auth here
    }

    public void signUpUser(String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            signupView.showPasswordsDoNotMatchError();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signupView.showSignupSuccess();
                    } else {
                        signupView.showSignupError(task.getException().getMessage());
                    }
                });
    }
}
