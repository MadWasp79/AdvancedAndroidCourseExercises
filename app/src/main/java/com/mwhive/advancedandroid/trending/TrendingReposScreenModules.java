package com.mwhive.advancedandroid.trending;


import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.mwhive.poweradapter.adapter.RecyclerDataSource;
import com.mwhive.poweradapter.item.ItemRenderer;
import com.mwhive.poweradapter.item.RecyclerItem;
import com.mwhive.poweradapter.item.RenderKey;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import java.util.Map;

/**
 * Created by MadWasp79 on 05-May-18.
 */
@Module
public abstract class TrendingReposScreenModules {

  @Binds
  @IntoSet
  abstract ScreenLifecycleTask bindUiManager(TrendingReposUiManager uiManager);

  @Binds
  @IntoMap
  @RenderKey("Repo")
  abstract ItemRenderer<? extends RecyclerItem> bindRepoRenderer(RepoRenderer repoRenderer);

  @Provides
  @ScreenScope
  static RecyclerDataSource provideRecyclerDataSource(
      Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
    return new RecyclerDataSource(renderers);
  }
}
