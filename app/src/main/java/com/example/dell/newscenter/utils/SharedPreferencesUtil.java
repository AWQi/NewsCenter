package com.example.dell.newscenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dell.newscenter.bean.Project;

import java.util.Map;

public class SharedPreferencesUtil {
    /*    identification文件标识   */
    static public Map<String, ?> getAllSharedDate(Context context,String identification){
        SharedPreferences spf = context.getSharedPreferences(identification,Context.MODE_PRIVATE);
        Map<String,?> map = spf.getAll();
        return map;
    }
    static public Object getStringSharedDate(Context context,String identification,String key){
        SharedPreferences spf = context.getSharedPreferences(identification,Context.MODE_PRIVATE);
        return  spf.getString(key,"");
    }
    {}
    static  public void putStringSharedDate(Context context,String identification, String key, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(identification,Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.commit();
}

}
