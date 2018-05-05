package com.mwhive.advancedandroid.trending;

import com.mwhive.advancedandroid.base.ScreenModule;
import com.mwhive.advancedandroid.di.ScreenComponent;
import com.mwhive.advancedandroid.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by madwa on 13-Feb-18.
 */

@ScreenScope
@Subcomponent(modules = {
    ScreenModule.class,
    TrendingReposScreenModules.class,
})
public interface TrendingReposComponent extends ScreenComponent<TrendingReposController> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

    @Override
    public void seedInstance(TrendingReposController instance) {

    }
  }

}
