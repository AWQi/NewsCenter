package com.example.dell.news;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
public class JSONUtil {
    static final private Gson gson = new Gson();

    static  public String ObjToStr(Object bean){
            return gson.toJson(bean);
    }
    static public <T>T StrToObj(String jsonDate,Class objClass){
        return (T) gson.fromJson(jsonDate,objClass);
    }
    static public String ListToStr(List list){
        return gson.toJson(list);
    }
    static public <T> List<T>  StrToList(String jsonDate){
        return  gson.fromJson(jsonDate,new TypeToken<List<T>>(){}.getType());
    }

}
