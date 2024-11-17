package com.watermelon;

import android.app.Application;
import android.content.Context;

public class WatermelonApplication extends Application {
    private static Context context;
    private static Application application;

    public void onCreate() {
        super.onCreate();

        application = this;
        WatermelonApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return WatermelonApplication.context;
    }

    public static Application getApplication() {
        return application;
    }
}
