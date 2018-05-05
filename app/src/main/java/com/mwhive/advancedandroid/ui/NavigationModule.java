package com.mwhive.advancedandroid.ui;

import com.mwhive.advancedandroid.di.ActivityScope;
import com.mwhive.advancedandroid.lifecycle.ActivityLifecycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * Created by madwa on 14-Feb-18.
 */

@Module
public abstract class NavigationModule {

  @Binds
  abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

  @Binds
  @IntoSet
  abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
