package com.flikster;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by abhishek on 11-11-2017.
 */

public class GlobalDataStorage extends Application {

    RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context)
    {
        GlobalDataStorage globalDataStorage=(GlobalDataStorage)context.getApplicationContext();
        return globalDataStorage.refWatcher;
    }

    @Override public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher=LeakCanary.install(this);
        // Normal app init code...
    }

}
