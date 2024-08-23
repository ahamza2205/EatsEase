

package com.example.eatsease.login_signup.authentication.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.authentication.presenter.splash.SplashPresenter;
import com.example.eatsease.homeactivity.HomeActivity;
import com.example.eatsease.login_signup.authentication.activity.login.LogIn;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Initialize presenter
        splashPresenter = new SplashPresenter(this, this);

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
        Intent intent;
        if (isAuthenticated) {
            intent = new Intent(SplashActivity.this, HomeActivity.class);
            Log.d("hamza", "isAuthenticated: yes");
        } else {
            intent = new Intent(SplashActivity.this, LogIn.class);
            Log.d("hamza", "isAuthenticated: no");
        }
        startActivity(intent);
        finish(); // Finish SplashActivity so the user can't navigate back to it
    }
}
