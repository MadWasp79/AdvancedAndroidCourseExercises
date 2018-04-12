package com.mwhive.advancedandroid.ui;


import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestNavigationalModule {

  @Binds
  abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);
}
