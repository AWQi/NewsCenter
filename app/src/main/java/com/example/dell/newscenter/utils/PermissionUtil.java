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
static  final  public  int REQUEST_STORAGE = 102;
static public  boolean requestCamera(Context context){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.CAMERA},REQUEST_CAMERA );
            return false;
        }
        return true;
    }
static  public  boolean requestStorage(Context context){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(context, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.MOUNT_FORMAT_FILESYSTEMS,
                    Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, REQUEST_STORAGE);
            return  false;
        }
    return true;
}
}
