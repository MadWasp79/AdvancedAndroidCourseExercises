package com.mwhive.advancedandroid.di;


import com.mwhive.advancedandroid.lifecycle.DisposableManager;
import dagger.android.AndroidInjector;

/**
 * Created by MadWasp79 on 05-May-18.
 */

public interface ScreenComponent<T> extends AndroidInjector<T> {

  @ForScreen
  DisposableManager disposableManager();

}
