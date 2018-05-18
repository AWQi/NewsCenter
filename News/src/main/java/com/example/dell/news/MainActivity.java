package com.example.dell.news;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button go;
    private Button button;
    private VideoView videoview;
    private MediaController mMediaController;
    private String url1 = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    private String url2 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private String url3 = "http://42.96.249.166/live/388.m3u8";
    private String url4 = "http://61.129.89.191/ThroughTrain/download.html?id=4035&flag=-org-"; //音频url

    Button networkbtn;
    TextView returntv;

    private  boolean showDanmaku;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };



    private  void  addDanmaku(String content,boolean withBorder){
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_LR);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = DensityUtil.px2dip(MainActivity.this,100);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder){
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
}
/**
 *   随机生成弹幕
 */
private void generateSomeDanmaku() {
     new Thread(new Runnable() {
         @Override
         public void run() {
             while(showDanmaku){
                 int time = new Random().nextInt(300);
                 String content = ""+time+time;
                 addDanmaku(content,false);

                 try {
                     Thread.sleep(time);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }).start();
}

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView!=null&&danmakuView.isPrepared()){
            danmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmakuView!=null){
            danmakuView.release();// 释放
            danmakuView = null;
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button go =findViewById(R.id.go);

            VideoView videoView = findViewById(R.id.video);
            videoView.setVideoURI(Uri.parse("http://ips.ifeng.com/video.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4"));
            videoView.start();
            danmakuView=findViewById(R.id.DanmakuView);
            danmakuView.enableDanmakuDrawingCache(true);
            danmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    showDanmaku = true;
                    danmakuView.start();
                    generateSomeDanmaku();
                }

                @Override
                public void updateTimer(DanmakuTimer timer) {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {

                }

                @Override
                public void drawingFinished() {

                }
            });
            danmakuContext = DanmakuContext.create();
            danmakuView.prepare(parser,danmakuContext);
            final LinearLayout operationLayout = findViewById(R.id.operation_layout);
            ImageView send = findViewById(R.id.send);
            final EditText editText = findViewById(R.id.et);
            danmakuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (operationLayout.getVisibility()==View.GONE){
                        operationLayout.setVisibility(View.VISIBLE);
                    }else {
                        operationLayout.setVisibility(View.GONE);
                    }
                }
            });
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String content = editText.getText().toString();
                    if (!TextUtils.isEmpty(content)){
                        addDanmaku(content,true);
                        editText.setText("");
                    }
                }
            });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout ll = findViewById(R.id.activity_main);
        TextView t = new TextView(MainActivity.this);
        t.setText("dsfs");

        ll.addView(t);


        go = (Button) findViewById(R.id.go);
        button = (Button) findViewById(R.id.play);
        videoview = (VideoView) findViewById(R.id.video);

        mMediaController = findViewById(R.id.mymediocontroller);
        mMediaController = new MediaController(MainActivity.this);
        videoview.setMediaController(mMediaController);


        videoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setForeground(null);
                loadView(url2);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, BottomActivity.class);
                Intent intent = new Intent(MainActivity.this, ShootingActivity.class);
                startActivity(intent);
            }
        });
    }


    public void loadView(String path) {
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        Uri uri = Uri.parse(path);
        videoview.setVideoURI(uri);

        videoview.setMediaController(new MediaController(MainActivity.this));// 控制栏
        progressBar.setVisibility(View.VISIBLE);
        videoview.start();

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                progressBar.setVisibility(View.VISIBLE);
                mp.start();// 播放
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "开始播放！", Toast.LENGTH_LONG).show();
            }
        });

        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });


        networkbtn = findViewById(R.id.networkbtn);
        returntv = findViewById(R.id.returntv);

        networkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "点击: ");
                if (view.getId() == R.id.networkbtn) {
                    sendRequestWithHttpURLConnect();
                }
            }
        });
        List<Student> studentsList  = new ArrayList<>();
        studentsList.add(new Student("json",12,"com.123") );
        studentsList.add(new Student("json",12,"com.123") );
        studentsList.add(new Student("json",12,"com.123") );
        studentsList.add(new Student("json",12,"com.123") );


        TextView textView = findViewById(R.id.json);
        String jsonDate = JSONUtil.ListToStr(studentsList);
//        textView.setText(JSONUtil.ListToStr(studentsList));

//        String jsonDate = "\"{\\\"name\\\":\\\"JSON\\\",\\\"age\\\":\\\"24\\\",\\\"address\\\":\\\"北京市西城区\\\"}\"";
        studentsList = JSONUtil.StrToList(jsonDate);
        textView.setText(studentsList.toString());

        ImageView imageView  = findViewById(R.id.iv);
        HttpImageDownloadUtil.setImage(imageView,"http://ww4.sinaimg.cn/large/6592c2e0jw1eqnjtyr98ej20f00qot9t.jpg");
//        imageView.setImageBitmap();
    }

    private void sendRequestWithHttpURLConnect() {
    /**
     *      1、
     */
        // 开启线程 发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection conn = null;
                StringBuilder response1;
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
                    response1 = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response1.append(line);
                    }
                    Log.d(TAG, "run: 发送请求");


//                    showResponse(response1.toString());
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
                /**
                 *      2、okhttp
                 *
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                                        .url("https://www.baidu.com")
                                                        .build();
                            Response response = client.newCall(request).execute();
                            String  responseDate = response.body().toString();
                            showResponse(responseDate);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        }
                }).start();

}
            private void showResponse(final String respose) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "respose: " + respose);
                        returntv.setText(respose);
                    }
                });
            }




 }
