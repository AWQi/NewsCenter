package com.example.dell.newscenter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.dell.newscenter.bean.Comment;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.bean.RegisterUser;
import com.example.dell.newscenter.bean.Studio;
import com.example.dell.newscenter.bean.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class JoyHttpUtil {
//static  final  public String HOST = "192.168.225.133:8080";
//    static  final  public String HOST = "10.0.2.2:8080";
    static  final  public String HOST = "10.0.161.106:8080";
    static  final  public  int NETWORK_FAIL = 111;
//    static  final  public String HOST = "140.143.16.51:8080/joy";
    private static final String TAG = "JoyHttpUtil";
    static  public final  Type PROJECT_LIST_TYPE = new TypeToken<JoyResult.JoyList<Project>>() {}.getType();
    static  public final  Type COMMENT_LIST_TYPE = new TypeToken<JoyResult.JoyList<Comment>>() {}.getType();
    static  public final  Type USER_LIST_TYPE =     new TypeToken<JoyResult.JoyList<User>>() {}.getType();
    static  public final  Type STUDIO_LIST_TYPE =  new TypeToken<JoyResult.JoyList<Studio>>() {}.getType();


    static  public final  Type OBJECT_TTYPE =  new TypeToken<JoyResult.JoyObj<Object>>() {}.getType();
    static  public final  Type STRING_TYPE =  new TypeToken<JoyResult.JoyObj<String>>() {}.getType();
    static  public final  Type INTEGER_OBJ_TYPE =  new TypeToken<JoyResult.JoyObj<Integer>>() {}.getType();
    static  public final  Type USER_OBJ_TYPE =  new TypeToken<JoyResult.JoyObj<User>>() {}.getType();
    static  public final  Type COMMENT_OBJ_TYPE = new TypeToken<JoyResult.JoyObj<Comment>>() {}.getType();
    /**   评论：
     *
     */
//    查看评论
    static final private String QUERY_COMMENT = " http://"+HOST+"/queryComment";
    static  public void queryComment(int projrctId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(projrctId));
        joyPostHttp(QUERY_COMMENT,null,null,param,joyHttpCallBack);
    }
    //    添加评论
    static final private String ADD_COMMENT = "http://"+HOST+"/addComment";
    static  public void addComment(Comment comment ,JoyHttpCallBack joyHttpCallBack){
        String commentInfo = JsonUtil.ObjToStr(comment);
        joyPostHttp(ADD_COMMENT,commentInfo,null,null,joyHttpCallBack);
    }
    //    删除评论
    static final private String  DELETE_COMMENT = "http://"+HOST+":8080/deleteComment";
    static  public void deleteComment(int commentId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("commentId",String.valueOf(commentId));
        joyPostHttp(DELETE_COMMENT,null,null,param,joyHttpCallBack);
    }
    //    查看我的收藏 ：
    static final private String QUERY_MYDYANAMIC = "http://"+HOST+"/queryMyDynamic";
    static  public void queryMyDynamic(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(QUERY_MYDYANAMIC,null,null,param,joyHttpCallBack);
    }
    //    查看我的收藏 ：
    static final private String QUERY_COLLECT = "http://"+HOST+"/queryDynamicCollect";
    static  public void queryDynamicCollect(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(QUERY_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    添加收藏：
    static final private String ADD_COLLECT = "http://"+HOST+"/addDynamicCollect";
    static  public void addDynamicCollect(int dynamicId,int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(dynamicId));
        param.put("userId",String.valueOf(userId));
        joyPostHttp(ADD_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    删除收藏：
    static  final  private  String DELETE_COLLECT = "http://"+HOST+"/deleteDynamicCollect";
    static  public void deleteDynamicCollect(int dynamicId,int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(dynamicId));
        param.put("userId",String.valueOf(userId));
        joyPostHttp(DELETE_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    相关推荐
    static  final  private  String RELEVANT_RECOM = "http://"+HOST+"/relevantRecom";
    static  public void RelevantRecom(String kind,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("kind",String.valueOf(kind));
        joyPostHttp(RELEVANT_RECOM,null,null,param,joyHttpCallBack);
    }
    //    推荐动态
    static  final  private  String COMMENT_DYNAMIC = "http://"+HOST+"/commendDynamics";
    static  public void commendDynamics(int page,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("page",String.valueOf(page));
        joyPostHttp(COMMENT_DYNAMIC,null,null,param,joyHttpCallBack);
    }
    static  final  private  String QUERY_ATTENT_DYNAMIC = "http://"+HOST+"/queryAttentDynamic";
    static  public void queryAttentDynamic(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(QUERY_ATTENT_DYNAMIC,null,null,param,joyHttpCallBack);
    }

    ///    查看粉丝
    static  final  private  String  MYFANS = "http://"+HOST+"/myFans";
    static  public void myFans(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(MYFANS,null,null,param,joyHttpCallBack);
    }
    //    查看关注
    static  final  private  String MYATTENTION = "http://"+HOST+"/myAttention";
    static  public void myAttention(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(MYATTENTION,null,null,param,joyHttpCallBack);
    }
    //    添加关注
    static  final  private  String     ADD_ATTENTION = "http://"+HOST+"/addAttention";
    static  public void addAttention(int user1Id,int user2Id,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("user1Id",String.valueOf(user1Id));
        param.put("user2Id",String.valueOf(user2Id));
        joyPostHttp(ADD_ATTENTION,null,null,param,joyHttpCallBack);
    }
    //    取消关注
    static  final  private  String DELETE_ATTENTION =  "http://"+HOST+"/deleteAttention";
    static  public void deleteAttention(int user1Id,int user2Id,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("user1Id",String.valueOf(user1Id));
        param.put("user2Id",String.valueOf(user2Id));
        joyPostHttp(DELETE_ATTENTION,null,null,param,joyHttpCallBack);
    }
    //    登录
    static  final  private  String  LOGIN = "http://"+HOST+"/login";
    static  public void login(String tel ,String password,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("tel",String.valueOf(tel));
        param.put("password",String.valueOf(password));
        joyPostHttp(LOGIN,null,null,param,joyHttpCallBack);
    }

    //    注册
    static  final  private  String PREREGISTER = "http://"+HOST+"/preRegister";
    static  public void preRegister(String tel,JoyHttpCallBack joyHttpCallBack){
       Map<String,String> param = new HashMap<>();
       param.put("tel",tel);
        joyPostHttp(PREREGISTER,null,null,param,joyHttpCallBack);
    }

    //    注册
    static  final  private  String REGISTER = "http://"+HOST+"/register";
    static  public void register(RegisterUser registerUser, String verificationCode, JoyHttpCallBack joyHttpCallBack){
        String body = JsonUtil.ObjToStr(registerUser);
        Map<String ,String>  param = new HashMap();
        param.put("verificationCode",verificationCode);
        joyPostHttp(REGISTER,body,null,param,joyHttpCallBack);
    }
    //    查询直播间
    static  final  private  String QUERY_STUDIO = "http://"+HOST+"/queryStudio";
    static  public void queryStudio( JoyHttpCallBack joyHttpCallBack){
        joyPostHttp(QUERY_STUDIO,null,null,null,joyHttpCallBack);
    }


    //    动态作者信息
    static  final  private  String    QUERY_USER_INFO =  "http://"+HOST+"/queryUserInfo";
    static  public void quseryUseInfo(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(QUERY_USER_INFO,null,null,param,joyHttpCallBack);
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

        protected   JoyResultCallBack  joyResultCallBack ;
        protected   JoyListCallBack  joyListCallBack;
        protected   JoyObjCallBack joyObjCallBack;
        protected   Class clazz =null;
        protected   Type type;
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
            int a = jsonStr.indexOf("[");

            try {
                if (a!=-1){// 传入泛型  解析Json
                    Gson gson = new Gson();
                    JoyResult.JoyList joyList =gson.fromJson(jsonStr,type);
    //                Log.d(TAG, "joyList: "+joyList.getData());
                    joyListCallBack.analyticData(joyList);
                }else {
                    Gson gson = new Gson();
                    JoyResult.JoyObj joyObj =gson.fromJson(jsonStr,type);
    //                JoyResult.JoyObj joyObj =  JsonUtil.StrToObj(jsonStr, type);
                    joyObjCallBack.analyticData(joyObj);
                }
            } catch (JsonSyntaxException e) {
                Log.d(TAG, "网络数据解析错误------------------------------------ ");
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }


        }



    }
    static  abstract  public  class JoyResultCallBack extends  JoyHttpCallBack{
        public JoyResultCallBack() {
            joyResultCallBack = this;
        }
        abstract public  void analyticData(JoyResult joyResult);
    }
    static  abstract  public  class JoyListCallBack extends JoyHttpCallBack{
        public JoyListCallBack(Type type) {
            joyListCallBack = this;
            this.type =type;
        }

        abstract public  void analyticData(JoyResult.JoyList joyList);
    }
    static  abstract  public  class JoyObjCallBack extends  JoyHttpCallBack{
        public JoyObjCallBack(Type type) {
            joyObjCallBack = this;
            this.type =type;
        }
        abstract public  void analyticData(JoyResult.JoyObj joyObj);
    }
}
