package com.example.dell.newscenter.utils;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    static final public String LIVE_PATH = "rtmp://140.143.16.51/hls/";
    static final public String IMAGE_PATH = "http://140.143.16.51/image/";
    static final public String VIDEO_PATH = "http://140.143.16.51/video/";

    private static final String TAG = "HttpUtil";
    /**
     *      1、
     */
    private void sendRequestWithHttpURLConnect() {

        // 开启线程 发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection conn = null;
                StringBuilder response;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://www.baidu.com");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream is = conn.getInputStream();
                    // 对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(is));
                    response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.d(TAG, "run: 发送请求");
                    //                    return response.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
    /**
     *      l2、okhttp
     *
     */
    static  public  void sendPostWithOkHttp3(final String url, final Map<String,String> params, final Callback callback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                // post  请求  用于添加参数
                FormBody.Builder builder = new FormBody.Builder();;
                if (params!=null) {
                    for (String key : params.keySet()) {
                        builder.add(key, params.get(key));
                    }
                }
                Request request = new Request.Builder()
                        .url(url)
                        .post(builder.build())
                        .build();
                //
                Call call = client.newCall(request);
                // 执行异步请求
                call.enqueue(callback);

            }
        }).start();
    }


}
