package com.mwhive.advancedandroid.di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;
import com.mwhive.advancedandroid.base.BaseActivity;
import com.mwhive.advancedandroid.base.BaseController;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {

    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
    private final Map<String, AndroidInjector<Controller>> cache = new HashMap<>();
    @Inject
    ScreenInjector(Map<Class<? extends Controller>,
            Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors) {

        this.screenInjectors = screenInjectors;
    }

    void inject(Controller controller) {
        if (!(controller instanceof BaseController)) {
            throw new IllegalArgumentException("Controller must extend BaseController");
        }

        String instancId = controller.getInstanceId();
        if (cache.containsKey(instancId)) {
            cache.get(instancId).inject(controller);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectorFactory =
                (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();
        AndroidInjector<Controller> injector = injectorFactory.create(controller);

        cache.put(instancId, injector);

        injector.inject(controller);

    }

    void clear(Controller controller) {
        cache.remove(controller.getInstanceId());
    }

    static ScreenInjector get(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Controller must be hosted by BaseActivity");
        }

        return ((BaseActivity) activity).getScreenInjectror();
    }

}
