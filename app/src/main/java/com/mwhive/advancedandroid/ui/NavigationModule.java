package com.mwhive.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by madwa on 14-Feb-18.
 */

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
