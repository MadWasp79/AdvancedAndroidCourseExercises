package com.mwhive.advancedandroid.di;

import android.app.Activity;

/**
 * Created by madwa on 09-Feb-18.
 */

public class Injector {

    private Injector() {

    }

    public static void inject(Activity activity) {
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }
}
