package com.mwhive.advancedandroid.details;


import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * Created by MadWasp79 on 05-May-18.
 */

@Module
public abstract class RepoDetailsScreenModule {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);


}
