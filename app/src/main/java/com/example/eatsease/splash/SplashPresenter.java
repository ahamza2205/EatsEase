package com.example.eatsease.splash;

import android.content.Context;

import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;

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
                    isAppUser();
                });
    }
}

