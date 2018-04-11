package com.mwhive.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.mwhive.advancedandroid.details.RepoDetailsComponent;
import com.mwhive.advancedandroid.details.RepoDetailsController;
import com.mwhive.advancedandroid.di.ControllerKey;
import com.mwhive.advancedandroid.model.Repo;
import com.mwhive.advancedandroid.trending.TrendingReposComponent;
import com.mwhive.advancedandroid.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import javax.inject.Inject;


@Module(subcomponents = {
    TrendingReposComponent.class,
    RepoDetailsComponent.class,
})
public abstract class MainScreenBindingModule {

  @Binds
  @IntoMap
  @ControllerKey(TrendingReposController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(
      TrendingReposComponent.Builder builder);

  @Binds
  @IntoMap
  @ControllerKey(RepoDetailsController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(
      RepoDetailsComponent.Builder builder);
}
