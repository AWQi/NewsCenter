package com.example.dell.newscenter.myview.videoplayactivity.videoplayer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
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

public class  VideoLayout extends FrameLayout {
    private static final String TAG = "VideoLayout";
    private VideoView videoview;
    private MediaController mMediaController;
    private Context context;
    private ProgressBar progressBar ;
    private  Project project;
    private Boolean isPrepared = false;
    private boolean isStart = false;
    // 设置 播放开始的回调 监听器  函数
    private PlayStartListener startListener ;
    public interface  PlayStartListener{
        public void start();
    }
    public  void setPlayStartListener(PlayStartListener startListener){
        this.startListener = startListener;
    }
    public VideoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
         LayoutInflater.from(context).inflate(R.layout.videolayout,this);
        getDate();
        init();
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
   public  void  init(){

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
               HistoryUtil.putHistory(context,project);
               videoview.setForeground(null);
               videoview.start();
               /* 开始播放，再加载controller 要与onClickListener冲突*/
               videoview.setMediaController(new MediaController(context));

               return false;
           }
       });
//       videoview.setOnClickListener(new View.OnClickListener(){
//           @Override
//           public void onClick(View v) {
//
//           }
//       });
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


}
