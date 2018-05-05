package com.mwhive.advancedandroid.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.mwhive.advancedandroid.R;
import com.mwhive.advancedandroid.di.Injector;
import com.mwhive.advancedandroid.di.ScreenInjector;
import com.mwhive.advancedandroid.lifecycle.ActivityLifecycleTask;
import com.mwhive.advancedandroid.ui.ActivityViewInterceptor;
import com.mwhive.advancedandroid.ui.RouterProvider;
import com.mwhive.advancedandroid.ui.ScreenNavigator;

import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity implements RouterProvider{

  private static String INSTANCE_ID_KEY = "instance_id";

  @Inject ScreenInjector screenInjector;
  @Inject ScreenNavigator screenNavigator;
  @Inject ActivityViewInterceptor activityViewInterceptor;
  @Inject Set<ActivityLifecycleTask> activityLifecycleTasksSet;

  private String instanceId;
  private Router router;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
    } else {
      instanceId = UUID.randomUUID().toString();
    }
    Injector.inject(this);

    activityViewInterceptor.setContentView(this, layoutRes());
    ViewGroup screenContainer = findViewById(R.id.screen_container);
    if (screenContainer == null) {
      throw new NullPointerException("Activity must have a view with id: screen_container");
    }

    router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
    monitorBackStack();
    for (ActivityLifecycleTask task : activityLifecycleTasksSet) {
      task.onCreate(this);
    }
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void onStart() {
    super.onStart();
    for (ActivityLifecycleTask task : activityLifecycleTasksSet) {
      task.onStart(this);
    }

  }

  @Override
  protected void onResume() {
    super.onResume();
    for (ActivityLifecycleTask task : activityLifecycleTasksSet) {
      task.onResume(this);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(INSTANCE_ID_KEY, instanceId);
  }

  @Override
  public void onBackPressed() {
    if (!screenNavigator.pop()) {
      super.onBackPressed();
    }
  }

  @Override
  public Router getRouter() {
    return router;
  }

  @Override
  protected void onPause() {
    super.onPause();
    for (ActivityLifecycleTask task : activityLifecycleTasksSet) {
      task.onPause(this);
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    for (ActivityLifecycleTask task : activityLifecycleTasksSet) {
      task.onStop(this);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (isFinishing()) {
      Injector.clearComponent(this);
    }
    activityViewInterceptor.clear();
    for (ActivityLifecycleTask task : activityLifecycleTasksSet) {
      task.onDestroy(this);
    }
  }

  @LayoutRes
  protected abstract int layoutRes();

  public abstract Controller initialScreen();

  public String getInstanceId() {
    return instanceId;
  }

  public ScreenInjector getScreenInjector() {
    return screenInjector;
  }

  private void monitorBackStack() {
    router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
      @Override
      public void onChangeStarted(
          @Nullable Controller to,
          @Nullable Controller from,
          boolean isPush,
          @NonNull ViewGroup container,
          @NonNull ControllerChangeHandler handler) {

      }

      @Override
      public void onChangeCompleted(
          @Nullable Controller to,
          @Nullable Controller from,
          boolean isPush,
          @NonNull ViewGroup container,
          @NonNull ControllerChangeHandler handler) {
        if (!isPush && from != null) {
          Injector.clearComponent(from);
        }
      }
    });
  }
}
