package com.mwhive.advancedandroid.util;


import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 05-May-18.
 */

public class ButterKnifeUtil {

  private ButterKnifeUtil() {

  }

  public static void unbind(Unbinder unbinder) {
    if (unbinder != null) {
      try {
        unbinder.unbind();
      } catch (IllegalStateException ise) {
        Timber.e(ise, "Error unbinding views");
      }
    }
  }

}
