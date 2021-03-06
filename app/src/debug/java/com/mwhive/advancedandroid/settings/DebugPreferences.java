package com.mwhive.advancedandroid.settings;


import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by MadWasp79 on 18-Apr-18.
 */
@Singleton
public class DebugPreferences {

  private static final String MOCK_RESPONSES_KEY = "mock_response";

  private final SharedPreferences sharedPreferences;

  @Inject
  public DebugPreferences(Context context) {
    sharedPreferences = context.getSharedPreferences("debug_settings", Context.MODE_PRIVATE);
  }

  public boolean useMockResponsesEnabled() {
    return sharedPreferences.getBoolean(MOCK_RESPONSES_KEY, false);
  }

  public void setUseMockResponses(boolean useMockResponses) {
    sharedPreferences.edit().putBoolean(MOCK_RESPONSES_KEY, useMockResponses).apply();
  }
}
