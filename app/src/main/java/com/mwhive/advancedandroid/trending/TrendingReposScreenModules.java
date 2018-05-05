package com.mwhive.advancedandroid.trending;


import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * Created by MadWasp79 on 05-May-18.
 */
@Module
public abstract class TrendingReposScreenModules {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManager(TrendingReposUiManager uiManager);

}
