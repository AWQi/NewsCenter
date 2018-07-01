package com.example.dell.newscenter.utils;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatDelegate;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.myview.base.ConnectionChangeReceiver;

import org.litepal.LitePal;

public class ApplicationUtil extends Application{
    private static Context context;
    private static User user;

    public static int genderId[] = new int[]{R.drawable.men, R.drawable.women,R.drawable.secrecy};//  0 1 l2 分别对应 男 女  保密
    static private boolean nightMode = false;// 是否是夜间模式

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        registerReceiver();
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


    public void registerReceiver() {
       IntentFilter filter  = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
       ConnectionChangeReceiver mReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(mReceiver,filter);
    }
}
