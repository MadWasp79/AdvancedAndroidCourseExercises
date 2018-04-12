package com.mwhive.advancedandroid.test;


import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import com.mwhive.advancedandroid.base.TestApplication;
import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.data.TestRepoService;
import com.mwhive.advancedandroid.ui.TestScreenNavigator;

/**
 * Created by MadWasp79 on 13-Apr-18.
 */
public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

  public final TestScreenNavigator screenNavigator;
  public final TestRepoService repoService;
  public final RepoRepository repoRepository;

  public ControllerTestRule(Class<T> activityClass) {
    super(activityClass, true, false);
    screenNavigator = TestApplication.getComponent().screenNavigator();
    repoService = TestApplication.getComponent().repoService();
    repoRepository = TestApplication.getComponent().repoRepository();
  }

  public void clearState() {
    repoService.clearErrorFlags();
    repoService.clearHoldFlags();
    repoRepository.clearCache();
  }

}
