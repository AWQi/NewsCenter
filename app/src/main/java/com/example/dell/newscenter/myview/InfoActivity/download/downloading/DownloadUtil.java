package com.example.dell.newscenter.myview.InfoActivity.download.downloading;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class DownloadUtil {
    public static final int REQUEST_STORAGE_GROUP_CODE = 1;
    private static final String TAG = "DownloadUtil";

    static public void beginDownload(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//        ||ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
                ) {
            Log.d(TAG, "没有权限: ");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_GROUP_CODE);
            return;
        }
    }

    static public void pauseDownload() {

    }

    static public void continueDownload() {

    }

    static public void cancelDownload() {

    }
}
