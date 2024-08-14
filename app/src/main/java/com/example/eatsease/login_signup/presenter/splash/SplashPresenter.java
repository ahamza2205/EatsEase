package com.example.eatsease.login_signup.presenter.splash;

import com.example.eatsease.login_signup.model.repo.Respiratory;
import com.example.eatsease.login_signup.ui.activity.splash.ISplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
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
        Observable<Long> doAction = Observable.timer(timeInMillis, TimeUnit.MILLISECONDS);

        doAction.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((i) -> {
                    iSplashView.navigateToLogin();
                });
    }

}
