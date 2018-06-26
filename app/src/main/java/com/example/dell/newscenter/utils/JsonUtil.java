package com.example.dell.newscenter.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
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
    public static <T> List<T> StrToList(String jsonString,Class<T> cls){
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}