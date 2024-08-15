package com.example.eatsease.login_signup.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.presenter.login.LoginPresenter;
import com.example.eatsease.login_signup.ui.activity.home.HomeActivity;
import com.example.eatsease.login_signup.ui.activity.signup.SignUp;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements ILoginView {
    private FirebaseAuth auth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;
    private LoginPresenter loginPresenter;
    private ImageButton googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Log.d("LogIn", "onCreate: Initializing login activity");

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Initialize Views
        emailEditText = findViewById(R.id.loginEmailAddress);
        passwordEditText = findViewById(R.id.loginTextPassword);
        loginButton = findViewById(R.id.loginBtn);
        signupButton = findViewById(R.id.signupBtn);
        googleBtn = findViewById(R.id.googleBtn1);

        // Initialize Presenter
        loginPresenter = new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    loginPresenter.loginUser(email, password, auth, LogIn.this);
                } else {
                    Toast.makeText(LogIn.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, SignUp.class);
            startActivity(intent);
        });

        googleBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Google Sign-In not implemented yet", Toast.LENGTH_SHORT).show();
            // Implement Google Sign-In here if needed
        });
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LogIn.this, HomeActivity.class);
        startActivity(intent);
      finish();  // Optional: Close the login screen so the user can't go back to it
        Log.d("hazmahome", "Login successful");
    }

    @Override
    public void navigateToSignUp() {
        // Implement this method if needed
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(LogIn.this, "Login failed: " + error, Toast.LENGTH_SHORT).show();
    }
}
