package com.mwhive.advancedandroid.base;


import com.mwhive.advancedandroid.di.ForScreen;
import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.lifecycle.DisposableManager;
import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import java.util.Set;

/**
 * Created by MadWasp79 on 04-May-18.
 */

@Module
public abstract class ScreenModule {

  @Provides
  @ScreenScope
  @ForScreen
  static DisposableManager provideDisposableManager() {
    return new DisposableManager();
  }

  @Multibinds
  abstract Set<ScreenLifecycleTask> screenLifecycleTasks();

}