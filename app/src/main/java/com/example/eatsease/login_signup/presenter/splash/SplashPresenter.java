package com.example.eatsease.login_signup.presenter.splash;

import android.util.Log;

import com.example.eatsease.login_signup.model.repo.Respiratory;
import com.example.eatsease.login_signup.ui.activity.splash.ISplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashPresenter {
    private ISplashView iSplashView;
    private static SplashPresenter splashPresenter;
    private Respiratory respiratory;

    SplashPresenter(ISplashView iSplashView) {
        this.iSplashView = iSplashView;
    }

    public static SplashPresenter getInstance(ISplashView iSplashView) {
        if (splashPresenter == null) {
            splashPresenter = new SplashPresenter(iSplashView);
        }
        return splashPresenter;
    }

    public void isAppUser() {
        if(respiratory.readFromPreferences())
        {
            iSplashView.isAuthenticated(true);
        }
        else
        {
            iSplashView.isAuthenticated(false);
        }
    }
    public void start(Long timeInMillis) {
        Observable.timer(timeInMillis, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io()) // Run delay on the IO thread
                .observeOn(AndroidSchedulers.mainThread()) // Observe on the main thread
                .subscribe(aLong -> {
                    Log.d("cscs", "onCreate: 2");
                    // This block will be executed after the specified delay
                    iSplashView.navigateToLogin();
                });
    }

}
