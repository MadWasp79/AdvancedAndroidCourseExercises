package com.mwhive.advancedandroid.ui;


import android.view.textservice.TextServicesManager;
import com.mwhive.advancedandroid.lifecycle.ActivityLifecycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TestNavigationalModule {

  @Binds
  abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);

  @Binds
  @IntoSet
  abstract ActivityLifecycleTask bindScreenNavigatorTask(TestScreenNavigator testScreenNavigator);
}
