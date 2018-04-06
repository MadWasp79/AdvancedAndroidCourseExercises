package com.mwhive.advancedandroid.home;


import com.bluelinelabs.conductor.Controller;
import com.mwhive.advancedandroid.di.ControllerKey;
import com.mwhive.advancedandroid.trending.TrendingReposComponent;
import com.mwhive.advancedandroid.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})

public abstract class TestScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);
}
