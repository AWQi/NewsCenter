package com.example.dell.newscenter.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.example.dell.newscenter.activity.GuideActivity;

/**
 *  缓存软件的一些参数和数据
 */
public class CacheUtils {
    /**
     *
     * 获取缓存值
     * @param context
     * @param key
     * @return
     */
    public  static  boolean getBoolean (Context context,String key){

    SharedPreferences sp = context.getSharedPreferences("News",Context.MODE_PRIVATE);

    return sp.getBoolean(key,false);
}

    /**
     *    保存软件参数
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("News",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}
