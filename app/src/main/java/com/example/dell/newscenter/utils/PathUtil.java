package com.example.dell.newscenter.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public   class PathUtil {
    private static final String TAG = "PathUtil";
    //  获取内部目录
    public static String getPath(Context context){
        File file = context.getFilesDir();
        return file.getPath();
    }

    //获得  joy  目录
    private static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在

        if (sdCardExist)  //如果SD卡存在，则获取跟目录
        {
            sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);//获取数据目录
//            sdDir = Environment.getDataDirectory();
        }

        String joyPath = sdDir.toString()+"/joy/";
        File joyFile = new File(joyPath);
        if (!joyFile.exists()){
           joyFile.mkdir();
        }
        Log.d(TAG, "getSDPath: "+joyPath);
        return joyPath;
    }
    public  static String getPhotoPath(){
        String imagePath =   getSDPath()+"photo/";
        File imageDir = new File(imagePath);
        if (!imageDir.exists()){
            imageDir.mkdirs();
        }

        return imagePath;
    }
    public  static String getVideoPath(){
        String videoPath = getSDPath()+"video/";
        File recordDir = new File(videoPath);
        if (!recordDir.exists()){
            recordDir.mkdirs();
        }
        return videoPath;
    }
}
