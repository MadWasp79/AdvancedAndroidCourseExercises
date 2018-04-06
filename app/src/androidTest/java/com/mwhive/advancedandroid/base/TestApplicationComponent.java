package com.mwhive.advancedandroid.base;


import com.mwhive.advancedandroid.data.TestRepoServiceModule;
import com.mwhive.advancedandroid.networking.ServiceModule;
import com.mwhive.advancedandroid.trending.TrendingReposControllerTest;
import com.mwhive.advancedandroid.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})

public interface TestApplicationComponent extends ApplicationComponent {

    void inject(TrendingReposControllerTest trendingReposControllerTest);

}
