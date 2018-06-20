package com.example.dell.newscenter.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionUtil {
static  final public  int REQUEST_CAMERA = 101;
static public  boolean requestCamera(Context context){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.CAMERA},REQUEST_CAMERA );
            return false;
        }
        return true;
    }
}
