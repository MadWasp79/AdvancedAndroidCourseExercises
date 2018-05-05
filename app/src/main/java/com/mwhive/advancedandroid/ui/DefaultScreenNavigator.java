package com.mwhive.advancedandroid.ui;

import android.support.v7.app.AppCompatActivity;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.mwhive.advancedandroid.details.RepoDetailsController;
import com.mwhive.advancedandroid.di.ActivityScope;

import com.mwhive.advancedandroid.lifecycle.ActivityLifecycleTask;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

@ActivityScope
public class DefaultScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

  private Router router;

  @Inject
  DefaultScreenNavigator() {

  }

  @Override
  public void onCreate(AppCompatActivity activity) {
    if(!(activity instanceof RouterProvider)){
      throw new IllegalArgumentException("Activity must be instance of RouterProvider");
    }
    initWithRouter(((RouterProvider) activity).getRouter(), ((RouterProvider) activity).initialScreen());
  }

  void initWithRouter(Router router, Controller rootScreen) {
    this.router = router;
    if (!this.router.hasRootController()) {
      this.router.setRoot(RouterTransaction.with(rootScreen));
    }
  }

  @Override
  public boolean pop() {
    return router != null && router.handleBack();
  }

  @Override
  public void goToRepoDetails(String repoOwner, String repoName) {
    if (router != null) {
      router
          .pushController(RouterTransaction.with(
              RepoDetailsController.newInstance(repoName, repoOwner))
              .pushChangeHandler(new FadeChangeHandler())
              .popChangeHandler(new FadeChangeHandler()));
    }
  }

  @Override
  public void onDestroy(AppCompatActivity activity) {
    router = null;
  }
}
