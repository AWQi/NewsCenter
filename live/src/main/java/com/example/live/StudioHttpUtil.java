package com.example.live;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StudioHttpUtil {
//static  final  public String HOST = "192.168.225.133:8080";
//    static  final  public String HOST = "10.0.2.2:8080";
    static  final  public String HOST = "10.0.161.106:8080";
    static  final  public  int NETWORK_FAIL = 111;
//    static  final  public String HOST = "140.143.16.51:8080/joy";
    private static final String TAG = "StudioHttpUtil";
//    static  public final  Type OBJECT_TTYPE =  new TypeToken<R>() {}.getType();
//    static  public final  Type STRING_TYPE =  new TypeToken<JoyResult.JoyObj<String>>() {}.getType();
//    static  public final  Type INTEGER_OBJ_TYPE =  new TypeToken<JoyResult.JoyObj<Integer>>() {}.getType();



    //    开启直播
    static  final  private  String START_STUDIO = "http://"+HOST+"/startStudio";
    static  public void startStudio(String userId, JoyHttpCallBack joyHttpCallBack){
        Map<String ,String>  param = new HashMap();
        param.put("userId",userId);
        joyPostHttp(START_STUDIO,null,null,param,joyHttpCallBack);
    }
    //    关闭直播
    static  final  private  String STOP_STUDIO = "http://"+HOST+"/stopStudio";
    static  public void stopStudio(String userId, JoyHttpCallBack joyHttpCallBack){
        Map<String ,String>  param = new HashMap();
        param.put("userId",userId);
        joyPostHttp(STOP_STUDIO,null,null,param,joyHttpCallBack);
    }
    //  注册直播
    static  final  private  String REGISTER_STUDIO = "http://"+HOST+"/registerStudio";
    static  public void registerStudio(Studio studio, JoyHttpCallBack joyHttpCallBack){
       Gson gson = new Gson();
       String body = gson.toJson(studio);
        joyPostHttp(REGISTER_STUDIO,body,null,null,joyHttpCallBack);
    }
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==NETWORK_FAIL){
                Toast.makeText(ApplicationUtil.getContext(),"获取网络数据失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     *     Joy   发送http 并解析,返回数据 到  JoyResult  可以使用回调函数处理
     *
     */
    static  public  void joyPostHttp(final String url,final String body,final Map<String,String>  head  ,final Map<String,String> params, final JoyHttpCallBack joyHttpCallBack){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //10.0.2.2 shi 相对于模拟器来讲主机的地址 10.0.2.3是模拟器的地址
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(15000L, TimeUnit.MILLISECONDS)
                        .readTimeout(30000L, TimeUnit.MILLISECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException { Request original = chain.request();
                                /**
                                 *    添加1   尾部  参数
                                 */
                                HttpUrl originalHttpUrl = original.url();
                                HttpUrl.Builder httpBuilder  = originalHttpUrl.newBuilder();
                                if (params!=null) {
                                    for (String key : params.keySet()) {
                                        httpBuilder = httpBuilder.addQueryParameter(key, params.get(key));
                                    }
                                }
                                HttpUrl url = httpBuilder.build();
                                /**
                                 *    添加   head  参数
                                 */
                                Request.Builder requestBuilder = original.newBuilder().url(url);
                                if (head!=null) {
                                    for (String key : head.keySet()) {
                                        requestBuilder = requestBuilder.addHeader(key, head.get(key));
                                    }
                                }
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }).build();

                String realBody = null;
                if (body==null){
                    realBody = "";
                }else {
                    realBody = body;
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),realBody);
                Request request =  new Request.Builder().url(url).post(requestBody).build();
                //
                Call call = client.newCall(request);
                // 执行异步请求
                call.enqueue(joyHttpCallBack);

            }
        }).start();
    }
   static public  abstract class    JoyHttpCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
//            JoyResult joyResult = new JoyResult(300,"请求失败");
//            joyResultCallBack.analyticData(joyResult);

            Message msg  = new Message();
            msg.what = NETWORK_FAIL;
            handler.sendMessage(msg);
//                    Toast.makeText(context,"网络连接错误",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onFailure: -------------------------"+"获取网络数据失败");
            Log.d(TAG, "onFailure: "+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String jsonStr = response.body().string();
            Log.d(TAG, "onResponse: "+jsonStr);
            try {
                 Gson gson = new Gson();
                 StudioResult studioResult =gson.fromJson(jsonStr,StudioResult.class);
              analyticData(studioResult);
            } catch (JsonSyntaxException e) {
                Log.d(TAG, "网络数据解析错误------------------------------------ ");
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
       abstract public  void analyticData(StudioResult studioResult);
    }
}
