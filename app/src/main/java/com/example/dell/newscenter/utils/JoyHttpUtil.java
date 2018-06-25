package com.example.dell.newscenter.utils;

import com.example.dell.newscenter.bean.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.HEAD;

public class JoyHttpUtil {
static  final  public String HOST = "10.0.2.2";
    /**   评论：
     *
     */
//    查看评论
    static final private String QUERY_COMMENT = " http://"+HOST+":8080/queryComment";
    static  public void queryComment(int projrctId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(projrctId));
        joyPostHttp(QUERY_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    添加评论
    static final private String ADD_COMMENT = "http://"+HOST+":8080/addComment";
    static  public void addComment(int projrctId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(projrctId));
        joyPostHttp(ADD_COMMENT,null,null,param,joyHttpCallBack);
    }
    //    删除评论
    static final private String  DELETE_COMMENT = "http://"+HOST+":8080/deleteComment";
    static  public void deleteComment(int commentId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("commentId",String.valueOf(commentId));
        joyPostHttp(DELETE_COMMENT,null,null,param,joyHttpCallBack);
    }
    //    查看我的收藏 ：
    static final private String QUERY_COLLECT = "http://"+HOST+":8080/queryDynamicCollect";
    static  public void queryDynamicCollect(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(QUERY_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    添加收藏：
    static final private String ADD_COLLECT = "http://"+HOST+":8080/addDynamicCollect";
    static  public void addDynamicCollect(int dynamicId,int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(dynamicId));
        param.put("userId",String.valueOf(userId));
        joyPostHttp(ADD_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    删除收藏：
    static  final  private  String DELETE_COLLECT = "http://"+HOST+":8080/deleteDynamicCollect";
    static  public void deleteDynamicCollect(int dynamicId,int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("dynamicId",String.valueOf(dynamicId));
        param.put("userId",String.valueOf(userId));
        joyPostHttp(DELETE_COLLECT,null,null,param,joyHttpCallBack);
    }
    //    相关推荐
    static  final  private  String RELEVANT_RECOM = "http://"+HOST+":8080/relevantRecom";
    static  public void RelevantRecom(String kind,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("kind",String.valueOf(kind));
        joyPostHttp(RELEVANT_RECOM,null,null,param,joyHttpCallBack);
    }
    //    推荐动态
    static  final  private  String COMMENT_DYNAMIC = "http://"+HOST+":8080/commendDynamics";
    static  public void CommendDynamics(int page,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("kind",String.valueOf(page));
        joyPostHttp(COMMENT_DYNAMIC,null,null,param,joyHttpCallBack);
    }
    static  final  private  String QUERY_ATTENT_DYNAMIC = "http://"+HOST+":8080/queryAttentDynamic";
    static  public void queryAttentDynamic(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("kind",String.valueOf(userId));
        joyPostHttp(QUERY_ATTENT_DYNAMIC,null,null,param,joyHttpCallBack);
    }

    ///    查看粉丝
    static  final  private  String  MYFANS = "http://"+HOST+":8080/myFans";
    static  public void myFans(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(MYFANS,null,null,param,joyHttpCallBack);
    }
    //    查看关注
    static  final  private  String MYATTENTION = "http://"+HOST+":8080/myAttention";
    static  public void myAttention(int userId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("userId",String.valueOf(userId));
        joyPostHttp(MYATTENTION,null,null,param,joyHttpCallBack);
    }
    //    添加关注
    static  final  private  String     ADD_ATTENTION = "http://"+HOST+":8080/addAttention";
    static  public void addAttention(int user1Id,int user2Id,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("user1Id",String.valueOf(user1Id));
        param.put("user2Id",String.valueOf(user2Id));
        joyPostHttp(ADD_ATTENTION,null,null,param,joyHttpCallBack);
    }
    //    取消关注
    static  final  private  String DELETE_ATTENTION =  "http://"+HOST+":8080deleteAttention";
    static  public void deleteAttention(int user1Id,int user2Id,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("user1Id",String.valueOf(user1Id));
        param.put("user2Id",String.valueOf(user2Id));
        joyPostHttp(DELETE_ATTENTION,null,null,param,joyHttpCallBack);
    }
    //    登录
    static  final  private  String  LOGIN = "http://"+HOST+":8080/login";
    static  public void login(String tel ,String password,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("tel",String.valueOf(tel));
        param.put("password",String.valueOf(password));
        joyPostHttp(LOGIN,null,null,param,joyHttpCallBack);
    }
    //    注册
    static  final  private  String REGISTER = "http://"+HOST+":8080/register";
    static  public void login(User user,JoyHttpCallBack joyHttpCallBack){
       String body = JsonUtil.ObjToStr(user);
        joyPostHttp(REGISTER,body,null,null,joyHttpCallBack);
    }


    //    动态作者信息
    static  final  private  String    QUERY_DYNAMIC =  "http://"+HOST+":8080/queryDynamicAuthor";
    static  public void QUERY_DYNAMIC(int authorId,JoyHttpCallBack joyHttpCallBack){
        Map param = new HashMap<String,String>();
        param.put("authorId",String.valueOf(authorId));
        joyPostHttp(QUERY_DYNAMIC,null,null,param,joyHttpCallBack);
    }



    /**
     *     Joy   发送http 并解析,返回数据 到  JoyResult  可以使用回调函数处理
     *
     */
    static  public  void joyPostHttp(final String url,final String body,final Map<String,String>  head  ,final Map<String,String> params, final JoyHttpCallBack joyHttpCallBack){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                // post  请求  用于添加参数
                FormBody.Builder builder = new FormBody.Builder();
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
                call.enqueue(joyHttpCallBack);

            }
        }).start();
    }


   static public  abstract class    JoyHttpCallBack implements Callback {
        private  JoyResult  joyResult  = null;
        @Override
        public void onFailure(Call call, IOException e) {
            joyResult = new JoyResult();
            analyticData(joyResult);
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String jsonStr = response.body().string();
            joyResult = JsonUtil.StrToObj(jsonStr,JoyResult.class);
            analyticData(joyResult);
        }
        abstract public  void analyticData(JoyResult joyResult);
    }
}
