package com.example.dell.newscenter.utils;

import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    //   自带回调函数，内部开启线程执行网络请求
static public void sendOkHttpRequest(String address,okhttp3.Callback callback){
//      如果没网就 提示网络 不可用   直接停止 请求
//    if (!isNetWorkAvailable){
//        Toast.makeText(ApplicationUtil.getContext(),"network is unvailable",Toast.LENGTH_SHORT).show();
//        return;
//    }

        OkHttpClient client = new OkHttpClient();
        Request request = new  Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
