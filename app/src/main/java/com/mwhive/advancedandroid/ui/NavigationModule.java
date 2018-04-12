package com.mwhive.advancedandroid.ui;

import com.mwhive.advancedandroid.di.ActivityScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by madwa on 14-Feb-18.
 */

@Module
public abstract class NavigationModule {

  @Binds
  @ActivityScope
  abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
