package com.example.eatsease.login_signup.authentication.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.login_signup.authentication.presenter.login.LoginPresenter;
import com.example.eatsease.homeactivity.HomeActivity;
import com.example.eatsease.login_signup.authentication.activity.signup.SignUp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LogIn extends AppCompatActivity implements ILoginView, OnLoginWithGmailResponse {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;
    private Button skipButton;
    private LoginPresenter loginPresenter;
    private ImageButton googleBtn;

    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views
        emailEditText = findViewById(R.id.loginEmailAddress);
        passwordEditText = findViewById(R.id.loginTextPassword);
        loginButton = findViewById(R.id.loginBtn);
        signupButton = findViewById(R.id.signupBtn);
        skipButton = findViewById(R.id.skipBtn);
        googleBtn = findViewById(R.id.googleBtn1);

        // Initialize SharedPreRespiratory and Presenter
        SharedPreRespiratory sharedPreRespiratory = SharedPreRespiratory.getInstance(this);
        loginPresenter = new LoginPresenter(this, sharedPreRespiratory);

        // Check if user is already logged in
        if (sharedPreRespiratory.isUserLoggedIn()) {
            navigateToHome();
            return;
        }

        // Set click listeners
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                loginPresenter.loginUser(email, password);
            } else {
                Toast.makeText(LogIn.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            }
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LogIn.this, SignUp.class);
            startActivity(intent);
        });

        googleBtn.setOnClickListener(v -> signInUsingGoogle());



        skipButton.setOnClickListener(v -> loginPresenter.signInAnonymously());
    }

    private void signInUsingGoogle() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
        mGoogleSignInClient.signOut();  // Optional: Sign out any existing users

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                if (account != null) {
                    loginPresenter.signInUsingGmailAccount(account.getIdToken(), this);
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSignInSuccess() {
        navigateToHome();
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(LogIn.this, "Login failed: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginWithGmailSuccess() {
        // Save Google user email in SharedPreferences
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            SharedPreRespiratory.getInstance(this).addToPreferences(account.getEmail(), null);
        }
        onSignInSuccess();
    }

    @Override
    public void onLoginWithGmailError(String error) {
        showLoginError("Google Sign-In failed: " + error);
    }

    private void navigateToHome() {
        Intent intent = new Intent(LogIn.this, HomeActivity.class);
        startActivity(intent);
        finish();  // Close the login screen so the user can't go back to it
    }
}
