package com.mwhive.advancedandroid.ui;


import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * Created by MadWasp79 on 01-May-18.
 */

public interface RouterProvider {

  Router getRouter();

  Controller initialScreen();

}
