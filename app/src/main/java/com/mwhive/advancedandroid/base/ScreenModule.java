package com.mwhive.advancedandroid.base;


import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import dagger.Module;
import dagger.multibindings.Multibinds;
import java.util.Set;

/**
 * Created by MadWasp79 on 04-May-18.
 */

@Module
public abstract class ScreenModule {

  @Multibinds
  abstract Set<ScreenLifecycleTask> screenLifecycleTasks();

}