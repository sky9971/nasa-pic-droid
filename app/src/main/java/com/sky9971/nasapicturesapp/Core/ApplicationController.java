package com.sky9971.nasapicturesapp.Core;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class ApplicationController extends Application {

    private static ApplicationController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
    }

    public static ApplicationController getInstance() {
        return instance;
    }
}
