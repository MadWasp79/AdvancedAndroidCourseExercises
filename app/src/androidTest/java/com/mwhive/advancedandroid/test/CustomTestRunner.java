package com.mwhive.advancedandroid.test;


import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.mwhive.advancedandroid.base.TestApplication;

/**
 * Created by MadWasp79 on 06-Apr-18.
 */
public class CustomTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }
}
