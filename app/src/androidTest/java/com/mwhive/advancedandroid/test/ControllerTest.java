package com.mwhive.advancedandroid.test;


import android.content.Intent;
import com.bluelinelabs.conductor.Controller;
import com.mwhive.advancedandroid.data.RepoRepository;
import com.mwhive.advancedandroid.data.TestRepoService;
import com.mwhive.advancedandroid.home.MainActivity;
import com.mwhive.advancedandroid.ui.TestScreenNavigator;
import org.junit.Rule;

/**
 * Created by MadWasp79 on 13-Apr-18.
 */
public abstract class ControllerTest {

  @Rule
  public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

  protected TestRepoService repoService = testRule.repoService;
  protected RepoRepository repoRepository = testRule.repoRepository;
  protected TestScreenNavigator screenNavigator = testRule.screenNavigator;

  public ControllerTest(){
    screenNavigator.overrideInitialController(controllerToLaunch());
  }

  protected abstract Controller controllerToLaunch();

  protected void launch() {
    launch(null);
  }

  protected void launch(Intent intent){
    testRule.launchActivity(intent);
  }


}
