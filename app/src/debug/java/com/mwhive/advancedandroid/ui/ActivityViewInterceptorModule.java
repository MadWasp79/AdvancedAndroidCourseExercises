package com.mwhive.advancedandroid.ui;


import dagger.Binds;
import dagger.Module;

@Module
public abstract class ActivityViewInterceptorModule {

  @Binds
  abstract ActivityViewInterceptor bindDebugActivityInterceptor(
      DebugActivityViewInterceptor activityViewInterceptor);

}
