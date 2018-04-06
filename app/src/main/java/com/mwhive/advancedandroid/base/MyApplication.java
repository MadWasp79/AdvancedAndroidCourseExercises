package com.mwhive.advancedandroid.base;

import android.app.Application;

import com.mwhive.advancedandroid.BuildConfig;
import com.mwhive.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;


@SuppressWarnings("FieldCanBeLocal")
public class MyApplication extends Application {

    @Inject ActivityInjector mActivityInjector;

    protected ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = initComponent();
        mComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return mActivityInjector;
    }
}
