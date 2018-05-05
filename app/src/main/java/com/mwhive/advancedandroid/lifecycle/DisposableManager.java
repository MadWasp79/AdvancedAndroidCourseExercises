package com.mwhive.advancedandroid.lifecycle;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by MadWasp79 on 05-May-18.
 */

public class DisposableManager {

  private final CompositeDisposable compositeDisposable = new CompositeDisposable();

  public void add(Disposable... disposables) {
    compositeDisposable.addAll(disposables);
  }

  public void dispose() {
    compositeDisposable.clear();
  }
}
