package com.mwhive.advancedandroid.base;


import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.data.TestRepoService;
import com.mwhive.advancedandroid.data.TestRepoServiceModule;
import com.mwhive.advancedandroid.networking.ServiceModule;
import com.mwhive.advancedandroid.trending.TrendingReposControllerTest;
import com.mwhive.advancedandroid.ui.NavigationModule;

import com.mwhive.advancedandroid.ui.TestActivityViewInterceptorModule;
import com.mwhive.advancedandroid.ui.TestNavigationalModule;
import com.mwhive.advancedandroid.ui.TestScreenNavigator;
import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
    ApplicationModule.class,
    TestActivityBindingModule.class,
    TestRepoServiceModule.class,
    ServiceModule.class,
    TestNavigationalModule.class,
    TestActivityViewInterceptorModule.class,
})

public interface TestApplicationComponent extends ApplicationComponent {

  void inject(TrendingReposControllerTest trendingReposControllerTest);

  TestScreenNavigator screenNavigator();

  TestRepoService repoService();

  RepoRepository repoRepository();

}
