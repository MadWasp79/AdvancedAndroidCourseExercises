package com.mwhive.advancedandroid.details;

import android.os.Bundle;
import com.bluelinelabs.conductor.Controller;
import com.mwhive.advancedandroid.R;
import com.mwhive.advancedandroid.base.BaseController;


/**
 * Created by MadWasp79 on 11-Apr-18.
 */
public class RepoDetailsController extends BaseController {


  public final static String REPO_NAME_KEY = "repo_name";
  public final static String REPO_OWNER_KEY = "repo_owner";

  public static Controller newInstance(String repoName, String repoOwner) {
    Bundle bundle = new Bundle();
    bundle.putString(REPO_NAME_KEY, repoName);
    bundle.putString(REPO_OWNER_KEY, repoOwner);
    return new RepoDetailsController(bundle);


  }

  public RepoDetailsController(Bundle bundle) {
    super(bundle);
  }



  @Override
  protected int layoutRes() {
    return R.layout.screen_repo_details;
  }
}
