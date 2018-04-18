package com.mwhive.advancedandroid.ui;

import dagger.Module;
import dagger.Provides;

@Module
public class TestActivityViewInterceptorModule {

  @Provides
  static ActivityViewInterceptor provideActivityViewInterceptor() {
    return ActivityViewInterceptor.DEFAULT;
  }

}
