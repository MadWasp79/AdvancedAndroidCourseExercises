package com.mwhive.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.mwhive.advancedandroid.di.ActivityScope;
import com.mwhive.advancedandroid.di.ScreenInjector;

import javax.inject.Inject;

/**
 * Created by madwa on 14-Feb-18.
 */

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator{

    private Router mRouter;

    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        mRouter = router;
        if (!mRouter.hasRootController()) {
            mRouter.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        return mRouter != null && mRouter.handleBack();
    }

    @Override
    public void clear() {
        mRouter = null;
    }
}
