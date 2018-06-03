package com.example.dell.newscenter.myview.videoplayactivity.videoplayer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.InfoActivity.history.HistoryUtil;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.Random;
import java.util.Timer;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class  VideoLayout extends LinearLayout {
    private static final String TAG = "VideoLayout";
    private VideoView videoview;
    private MediaController mMediaController;
    private Context context;
    private ProgressBar progressBar ;
    private  Project project;
    private Boolean isPrepared = false;
    private boolean isStart = false;

    private EditText sendDanMuET = null;
    private ImageView fullScreenPlayIV = null;
    private  boolean showDanmaku;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;



    public VideoLayout(@NonNull final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
         LayoutInflater.from(context).inflate(R.layout.videolayout,this);
        getDate();
        initVideo();
        initDanMuView();

        sendDanMuET = findViewById(R.id.sendDanMuET);
        sendDanMuET.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){ // 失去焦点
                    String content = sendDanMuET.getText().toString();
                    if (!TextUtils.isEmpty(content)){ //内容不为空
                        addDanmaku(content,true);
                        sendDanMuET.setText("");
                    }
                }
            }
        });

//        Log.d(TAG, "VideoLayout: width"+this.getMeasuredWidth());
//        Log.d(TAG, "VideoLayout: height"+this.getMeasuredHeight());
        fullScreenPlayIV = findViewById(R.id.fullScreenPlayIV);
        Log.d(TAG, "fullScreenPlayIB: "+fullScreenPlayIV);
        fullScreenPlayIV.setOnClickListener(new OnClickListener() {// 进入全屏播放
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FullScreenPlayActivity.class);
                intent.putExtra("url",project.getVideoURL());
                ActivityUtil.scanForActivity(context).startActivity(intent);
            }
        });




    }

    /**
     *
     *   获取  url
     *       由mainactivity ,或其他 videoActivity 传送，  可以由context 获取intent 从而获取参数
     */
    public void getDate(){
        Intent intent = ActivityUtil.scanForActivity(context).getIntent();
        project = (Project)intent.getParcelableExtra("project");
    }

    /**
     *   初始化 播放器
     */
   public  void  initVideo(){

       videoview =  findViewById(R.id.player); //
//       mMediaController = findViewById(R.id.mymediacontroller);///
       {
            /*  设置  播放器的背景图*/
           Glide.with(context)
                   .load(project.getImageURL())
                   .asBitmap()
                   .into(new SimpleTarget<Bitmap>(
                     ActivityUtil.getWidth(context),ActivityUtil.getWidth(context)) {
                       @Override
                       public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                           Drawable drawable = new BitmapDrawable(resource);
                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                               videoview.setForeground(drawable);
                           }
                       }
                   });
       }
       loadView(project.getVideoURL());
       videoview.setOnTouchListener(new OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               Log.d(TAG, "VideoView被点击: ");
               isStart = true;
               if (!isPrepared){//  如果没加载完成就显示进度条
                   progressBar.setVisibility(View.VISIBLE);
               }
               HistoryUtil.putHistory(context,project);// 记录历史播放
               videoview.setForeground(null);
               videoview.start();
               /* 开始播放，再加载controller 要与onClickListener冲突*/
               videoview.setMediaController(new MediaController(context));

               return false;
           }
       });
   }
    public void loadView(String path) {
        progressBar = this.findViewById(R.id.progressbar); //
        Uri uri = Uri.parse(path);
        videoview.setVideoURI(uri);
//        videoview.setVideoPath(path);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);// 循环
                isPrepared = true;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "加载完成！", Toast.LENGTH_SHORT).show();
                if (isStart){
                    videoview.start();
                    videoview.setMediaController(new MediaController(context));// 控制栏
                }
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(context, "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 初始化弹幕
     */
    public  void initDanMuView(){
        danmakuView = findViewById(R.id.danmakuView);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }
            @Override
            public void updateTimer(DanmakuTimer timer) { }
            @Override
            public void danmakuShown(BaseDanmaku danmaku) { }
            @Override
            public void drawingFinished() { }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser,danmakuContext);


    }

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    /**
     *   添加弹幕
     * @param content
     * @param withBorder
     */
    private  void  addDanmaku(String content,boolean withBorder){
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_LR);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = 50;//DensityUtil.px2dip(MainActivity.this,100);
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
                    int time = new Random().nextInt(1000);
                    String content = ""+time+time;
                    addDanmaku(content,true);

                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
