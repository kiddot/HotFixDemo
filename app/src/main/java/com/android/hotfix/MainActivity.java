package com.android.hotfix;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import me.ele.amigo.Amigo;

/**
 * Created by kiddo on 16-12-12.
 */

public class MainActivity extends Activity{
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String APK_NAME = "app-release.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void applyPatchApkImmediately(View v) {
        Log.d(TAG, "applyPatchApkImmediately: i have enter");
        File dir = Environment.getExternalStorageDirectory();
        File patchApkFile = new File(dir, APK_NAME);
        if (!patchApkFile.exists()) {
            Toast.makeText(this,
                    "No amigo_patch.apk found in the directory: " + dir.getAbsolutePath(),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        boolean patchWorked = Amigo.hasWorked();
        if (!patchWorked) {
            Amigo.work(this, patchApkFile);
            return;
        }
        Amigo.work(this, patchApkFile);
    }

    public void applyPatchApkLater(View v) {
        File dir = Environment.getExternalStorageDirectory();
        File patchApkFile = new File(dir, APK_NAME);
        if (!patchApkFile.exists()) {
            Toast.makeText(this,
                    "No amigo_patch.apk found in the directory: " + dir.getAbsolutePath(),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        boolean patchWorked = Amigo.hasWorked();
        if (!patchWorked) {
            Amigo.workLater(this, patchApkFile, new Amigo.WorkLaterCallback() {
                @Override
                public void onPatchApkReleased() {
                    Toast.makeText(MainActivity.this, "dex opt done!", Toast.LENGTH_SHORT).show();
                }
            });
            Toast.makeText(this,
                    "waiting for seconds, and kill this app and relaunch the app to check result",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Amigo.workLater(this, patchApkFile, new Amigo.WorkLaterCallback() {
            @Override
            public void onPatchApkReleased() {
                Toast.makeText(MainActivity.this, "dex opt done!", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this,
                "waiting for seconds, and kill this app and relaunch the app to check result",
                Toast.LENGTH_SHORT).show();
    }

    public void clearPatchApk(View v) {
        Amigo.clear(getApplication());
        Toast.makeText(this, "Kill this app, restart the app and check the running apk",
                Toast.LENGTH_SHORT).show();
    }
}
