package com.mwhive.advancedandroid.di;

import android.app.Activity;
import android.content.Context;

import com.mwhive.advancedandroid.base.BaseActivity;
import com.mwhive.advancedandroid.base.MyApplication;
import com.mwhive.advancedandroid.home.MainActivityComponent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

/**
 * Created by madwa on 09-Feb-18.
 */

public class ActivityInjector {

    private final Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> mActivityInjectors;
    private final Map<String, AndroidInjector<? extends Activity>> cache = new HashMap<>();


    @Inject
    public ActivityInjector(Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors){
        mActivityInjectors = activityInjectors;
    }

    void inject(Activity activity) {
        //проверяем, является ли активити инстансом БейзАктивити
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Activity must extend BaseActivity");
        }

        //получаем инстан айди
        String instanceId = ((BaseActivity) activity).getInstanceId();

        //проверяем, есть ли єтот инстанс в кеше, и если есть - инжектим активити из кеша
        if (cache.containsKey(instanceId)) {
            //noinspection unchecked
            ((AndroidInjector<Activity>) cache.get(instanceId)).inject(activity);
            return;
        }

        //создаем новый инстанс, добавляем в кеш и инжектим.
        //noinspection unchecked
        AndroidInjector.Factory<Activity> injectorFactory =
                (AndroidInjector.Factory<Activity>)
                        mActivityInjectors.get(activity.getClass()).get();
        AndroidInjector<Activity> injector = injectorFactory.create(activity);
        cache.put(instanceId, injector);
        injector.inject(activity);

    }

    //убираем активити из кеша (когда активити finished)
    void clear(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Activity must extend BaseActivity");
        }

        cache.remove(((BaseActivity) activity).getInstanceId());
    }

    //метод для получения этого инжектора извне
    static ActivityInjector get(Context context) {
        return ((MyApplication) context.getApplicationContext()).getActivityInjector();
    }

}
