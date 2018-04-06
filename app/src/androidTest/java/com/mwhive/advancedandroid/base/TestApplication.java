package com.mwhive.advancedandroid.base;


import android.support.test.InstrumentationRegistry;

/**
 * Created by MadWasp79 on 06-Apr-18.
 */
public class TestApplication extends MyApplication {

    @Override
    protected ApplicationComponent initComponent() {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static TestApplicationComponent getComponent() {
        return (TestApplicationComponent)
                ((TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext()).mComponent;
    }
}
