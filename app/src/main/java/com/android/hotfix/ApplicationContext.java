package com.android.hotfix;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import me.ele.amigo.Amigo;
import me.ele.amigo.sdk.AmigoSdk;
import me.ele.amigo.utils.FileUtils;
import me.ele.amigo.utils.ProcessUtils;

/**
 * Created by kiddo on 16-12-12.
 */

public class ApplicationContext extends Application {

    private static final String TAG = ApplicationContext.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e(TAG, "attachBaseContext: " + base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AmigoSdk.init(this);
    }


}