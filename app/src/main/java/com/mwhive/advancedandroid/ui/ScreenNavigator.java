package com.mwhive.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;


public interface ScreenNavigator {

  boolean pop();

  void goToRepoDetails(String repoOwner, String repoName);


}
