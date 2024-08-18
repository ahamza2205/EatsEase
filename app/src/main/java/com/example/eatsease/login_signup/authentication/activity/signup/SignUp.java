package com.example.eatsease.login_signup.authentication.activity.signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.authentication.presenter.signup.SignUpPresenter;
import com.example.eatsease.login_signup.authentication.activity.login.LogIn;

public class SignUp extends AppCompatActivity implements ISignupView {
    private EditText EmailAddress;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button signupBtn;
    private ImageButton facebookBtn;
    private ImageButton googleBtn;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Initialize Views
        EmailAddress = findViewById(R.id.signUpEmailAddress);
        Password = findViewById(R.id.signUpTextPassword);
        ConfirmPassword = findViewById(R.id.signUpTextPassword2);
        signupBtn = findViewById(R.id.signupBtn);
        facebookBtn = findViewById(R.id.fbBtn);
        googleBtn = findViewById(R.id.googleBtn);

        // Initialize Presenter
        signUpPresenter = new SignUpPresenter(this);

        // Set click listener for Sign Up button
        signupBtn.setOnClickListener(v -> {
            String email = EmailAddress.getText().toString();
            String password = Password.getText().toString();
            String confirmPassword = ConfirmPassword.getText().toString();

            signUpPresenter.signUpUser(email, password, confirmPassword);
        });
        googleBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Google Sign-Up is coming soon!", Toast.LENGTH_SHORT).show();
        });
        facebookBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Facebook Sign-Up is coming soon!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showSignupSuccess() {
        Toast.makeText(this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUp.this, LogIn.class));
        finish();
    }

    @Override
    public void showSignupError(String message) {
        Toast.makeText(this, "Sign Up Failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordsDoNotMatchError() {
        Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
    }
}
