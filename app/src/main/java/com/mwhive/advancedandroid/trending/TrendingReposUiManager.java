package com.mwhive.advancedandroid.trending;


import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mwhive.advancedandroid.R;
import com.mwhive.advancedandroid.di.ScreenScope;
import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.mwhive.advancedandroid.util.ButterKnifeUtil;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 05-May-18.
 */

@ScreenScope
public class TrendingReposUiManager extends ScreenLifecycleTask {

  @BindView(R.id.toolbar) Toolbar toolbar;
  private Unbinder unbinder;

  @Inject TrendingReposUiManager(){}

  @Override
  public void onEnterScope(View view) {
    unbinder = ButterKnife.bind(this, view);
    toolbar.setTitle(R.string.screen_title_trending);
  }

  @Override
  public void onExitScope() {
    ButterKnifeUtil.unbind(unbinder);
  }
}
