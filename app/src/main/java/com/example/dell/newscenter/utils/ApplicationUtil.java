package com.example.dell.newscenter.utils;

import android.app.Application;
import android.content.Context;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;

public class ApplicationUtil extends Application{
    private static Context context;
    private static User user;
    public static int genderId[] = new int[]{R.drawable.men, R.drawable.women,R.drawable.secrecy};//  0 1 2 分别对应 男 女  保密

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
