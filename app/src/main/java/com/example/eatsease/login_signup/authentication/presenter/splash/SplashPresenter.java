package com.example.eatsease.login_signup.authentication.presenter.splash;

import android.content.Context;
import android.util.Log;

import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.login_signup.authentication.activity.splash.ISplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashPresenter {
    private ISplashView iSplashView;
    private SharedPreRespiratory sharedPreRespiratory;

    public SplashPresenter(Context context, ISplashView iSplashView) {
        this.iSplashView = iSplashView;
        sharedPreRespiratory = SharedPreRespiratory.getInstance(context);
    }

    public void isAppUser() {
        if(sharedPreRespiratory.readFromPreferences()) {
            iSplashView.isAuthenticated(true);
        } else {
            iSplashView.isAuthenticated(false);
        }
    }

    public void start(Long timeInMillis) {
        Observable.timer(timeInMillis, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Log.d("hamza-splash", "onCreate: 2");
                });
    }
}

