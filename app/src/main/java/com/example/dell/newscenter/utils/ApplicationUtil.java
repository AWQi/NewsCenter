package com.example.dell.newscenter.utils;

import android.app.Application;
import android.content.Context;

import com.example.dell.newscenter.bean.User;

public class ApplicationUtil extends Application{
    private static Context context;
    private static User user;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
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
}
