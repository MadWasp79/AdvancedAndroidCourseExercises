package com.mwhive.advancedandroid.details;


import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mwhive.advancedandroid.R;
import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.mwhive.advancedandroid.ui.ScreenNavigator;
import com.mwhive.advancedandroid.util.ButterKnifeUtil;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by MadWasp79 on 05-May-18.
 */
@ScreenScope
public class RepoDetailsUiManager extends ScreenLifecycleTask {

  @BindView(R.id.toolbar) Toolbar toolbar;

  private final String repoName;
  private final ScreenNavigator screenNavigator;

  private Unbinder unbinder;

  @Inject
  RepoDetailsUiManager(@Named("repo_name") String repoName, ScreenNavigator screenNavigator) {

    this.repoName = repoName;
    this.screenNavigator = screenNavigator;
  }

  @Override
  public void onEnterScope(View view) {
    unbinder = ButterKnife.bind(this, view);
    toolbar.setTitle(repoName);
    toolbar.setNavigationIcon(R.drawable.ic_back);
    toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
  }

  @Override
  public void onExitScope() {
    toolbar.setNavigationOnClickListener(null);
    ButterKnifeUtil.unbind(unbinder);
  }
}
