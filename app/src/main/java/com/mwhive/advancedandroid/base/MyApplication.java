package com.mwhive.advancedandroid.base;

import android.app.Application;

import com.mwhive.advancedandroid.BuildConfig;
import com.mwhive.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by madwa on 08-Feb-18.
 */
@SuppressWarnings("FieldCanBeLocal")
public class MyApplication extends Application {

    @Inject ActivityInjector mActivityInjector;

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }


    }

    public ActivityInjector getActivityInjector() {
        return mActivityInjector;
    }
}
