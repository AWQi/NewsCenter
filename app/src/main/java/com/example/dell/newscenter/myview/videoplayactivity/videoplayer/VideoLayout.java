package com.example.dell.newscenter.myview.videoplayactivity.videoplayer;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

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
    private String url1 = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    private String url2 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private String url3 = "http://42.96.249.166/live/388.m3u8";
    private String url4 = "http://61.129.89.191/ThroughTrain/download.html?id=4035&flag=-org-"; //音频url

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
       mMediaController = findViewById(R.id.mymediacontroller);///
       mMediaController = new MediaController(context);
       videoview.setMediaController(mMediaController);
//       Glide.with(context).load(project.getImageURL()).into();
       loadView(project.getVideoURL());
       videoview.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               if (!isPrepared){//  如果没加载完成就显示进度条
                   progressBar.setVisibility(View.VISIBLE);
               }
               videoview.setForeground(null);
               videoview.start();
               HistoryUtil.putHistory(context,project);
           }
       });
   }
    public void loadView(String path) {
        progressBar = this.findViewById(R.id.progressbar); //
        Uri uri = Uri.parse(path);
        videoview.setVideoURI(uri);
        videoview.setMediaController(new MediaController(context));// 控制栏
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);// 循环
                isPrepared = true;
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(context, "加载完成！", Toast.LENGTH_LONG).show();
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
