
package com.example.eatsease.login_signup.authentication.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.login_signup.authentication.presenter.login.LoginPresenter;
import com.example.eatsease.login_signup.homeactivity.HomeActivity;
import com.example.eatsease.login_signup.authentication.activity.signup.SignUp;

public class LogIn extends AppCompatActivity implements ILoginView {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;
    private LoginPresenter loginPresenter;
    private ImageButton googleBtn;
    private ImageButton facebookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views
        emailEditText = findViewById(R.id.loginEmailAddress);
        passwordEditText = findViewById(R.id.loginTextPassword);
        loginButton = findViewById(R.id.loginBtn);
        signupButton = findViewById(R.id.signupBtn);
        googleBtn = findViewById(R.id.googleBtn1);
        facebookBtn = findViewById(R.id.fbBtn);

        // Initialize SharedPreRespiratory and Presenter
        SharedPreRespiratory sharedPreRespiratory = SharedPreRespiratory.getInstance(this);
        loginPresenter = new LoginPresenter(this, sharedPreRespiratory);

        // Set click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginPresenter.loginUser(email, password);
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
            Toast.makeText(this, "Google Sign-In is coming soon!", Toast.LENGTH_SHORT).show();
        });
        facebookBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Facebook Sign-In is coming soon!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onSignInSuccess() {
        Intent intent = new Intent(LogIn.this, HomeActivity.class);
        startActivity(intent);
        finish();  // Close the login screen so the user can't go back to it
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(LogIn.this, "Login failed: " + error, Toast.LENGTH_SHORT).show();
    }
}

