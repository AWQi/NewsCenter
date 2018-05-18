package com.example.dell.newscenter.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
public class JsonUtil {
    static final private Gson gson = new Gson();
    //  javabean  转 json
    static  public String ObjToStr(Object bean){
        return gson.toJson(bean);
    }
    //  json 转 javabean
    static public <T>T StrToObj(String jsonDate,Class objClass){
        return (T) gson.fromJson(jsonDate,objClass);
    }
    //  list 转 json
    static public String ListToStr(List list){
        return gson.toJson(list);
    }
    // json 转list
    static public <T> List<T>  StrToList(String jsonDate){
        return  gson.fromJson(jsonDate,new TypeToken<List<T>>(){}.getType());
    }

}