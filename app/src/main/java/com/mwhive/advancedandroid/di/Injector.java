package com.mwhive.advancedandroid.di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;

public class Injector {

    private Injector() {

    }

    //inject Activity
    public static void inject(Activity activity) {
        ActivityInjector.get(activity).inject(activity);
    }

    //clear Activity after it finished
    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }

    //inject Controller
    public static void inject(Controller controller) {
        ScreenInjector.get(controller.getActivity()).inject(controller);
    }
    //clear Controller
    public static void clearComponent(Controller controller) {
        ScreenInjector.get(controller.getActivity()).clear(controller);
    }
}
