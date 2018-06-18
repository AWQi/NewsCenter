package com.example.dell.newscenter.utils;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;

import org.litepal.LitePal;

public class ApplicationUtil extends Application{
    private static Context context;
    private static User user;
    static final public String LIVE_PATH = "rtmp://140.143.16.51/";
    static final public String IMAGE_PATH = "http://140.143.16.51/image/";
    static final public String VIDEO_PATH = "http://140.143.16.51/video/";


    public static int genderId[] = new int[]{R.drawable.men, R.drawable.women,R.drawable.secrecy};//  0 1 2 分别对应 男 女  保密
    static private boolean nightMode = false;// 是否是夜间模式

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }
    static  public Context getContext(){
        return  context;
    }

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        ApplicationUtil.user = user;
    }
    /**
     * 初始化夜间模式
     */
    static  public void exchangeNightMode() {
        nightMode = !nightMode;
        AppCompatDelegate.setDefaultNightMode(nightMode ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
