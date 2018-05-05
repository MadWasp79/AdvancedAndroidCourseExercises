package com.mwhive.advancedandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.mwhive.advancedandroid.di.Injector;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mwhive.advancedandroid.lifecycle.ScreenLifecycleTask;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.Set;
import javax.inject.Inject;

public abstract class BaseController extends Controller {

  @Inject Set<ScreenLifecycleTask> screenLifecycleTasks;
  private final CompositeDisposable disposables = new CompositeDisposable();

  private boolean injected = false;
  private Unbinder unbinder;

  public BaseController() {
    super();
  }

  public BaseController(Bundle bundle) {
    super(bundle);
  }

  @Override
  protected void onContextAvailable(@NonNull Context context) {
    //Controller instances are retained across config changes, so this method can be called more than once.
    //This makes sure we don't waste any time injecting more than once, though technically it wouldn't change functionality.
    if (!injected) {
      Injector.inject(this);
      injected = true;
    }
    super.onContextAvailable(context);
  }

  @NonNull
  @Override
  protected final View onCreateView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    View view = inflater.inflate(layoutRes(), container, false);
    unbinder = ButterKnife.bind(this, view);
    onViewBound(view);
    disposables.addAll(subscriptions());
    return view;
  }

  @Override
  protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler,
      @NonNull ControllerChangeType changeType) {
    for (ScreenLifecycleTask task : screenLifecycleTasks) {
      if (changeType.isEnter) {
        task.onEnterScope(getView());
      } else {
        task.onExitScope();
      }
    }
  }

  @Override
  protected void onDestroyView(@NonNull View view) {
    disposables.clear();
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    for (ScreenLifecycleTask task : screenLifecycleTasks) {
      task.onDestroy();
    }
  }

  //callback that subclasses could use once all view bindings is taken care of
  protected void onViewBound(View view) {

  }

  //return array of disposables that the subclasses
  //can add that will be automatically un-subscribed from or disposed once the view is destroyed
  protected Disposable[] subscriptions() {
    return new Disposable[0];
  }

  //abstract layout resource method which will allow the subclass to provide its layout
  @LayoutRes
  protected abstract int layoutRes();
}
