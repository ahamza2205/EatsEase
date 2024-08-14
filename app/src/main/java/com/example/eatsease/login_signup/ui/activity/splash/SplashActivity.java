package com.example.eatsease.login_signup.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.model.repo.Respiratory;
import com.example.eatsease.login_signup.presenter.splash.SplashPresenter;
import com.example.eatsease.login_signup.ui.activity.home.HomeActivity;
import com.example.eatsease.login_signup.ui.activity.login.LogIn;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    Intent intent ;
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Initialize presenter
        splashPresenter = SplashPresenter.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Start the delay
        splashPresenter.start(1000L); // 1000 milliseconds delay
    }
    @Override
    public void isAuthenticated(boolean isAuthenticated) {
     if (isAuthenticated) {
         intent = new Intent(SplashActivity.this, HomeActivity.class);
     }else {
         intent = new Intent(SplashActivity.this, LogIn.class);
     }
    }
    @Override
    public void navigateToLogin() {
        startActivity(intent);
        finish(); // Finish SplashActivity so the user can't navigate back to it
    }


}
