package com.example.dell.newscenter.activity;

import android.app.LocalActivityManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.VideoView;

import com.example.dell.newscenter.R;

public class VideoPlayActivity  extends AppCompatActivity{
    private VideoView videoView = null;
    private MediaController mediaController = null;
    private String url = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private TabHost videotabhost = null;
    private TabWidget videotabwidget = null;
    private FrameLayout videotabcontent = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置状态栏透明!!!!记得在setContentView之前

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_videoplay);
        setActionBar(null);
        /**
         *  播放器
         *
         */
        videoView = findViewById(R.id.player);
        /**
         *   评论区及相关推荐
         *
         */




//        LinearLayout l = null;
//         l = (LinearLayout) LayoutInflater.from(VideoPlayActivity.this).inflate(R.layout.videotab,null);
//        videotabhost = l.findViewById(android.R.id.tabhost);
////        videotabwidget = l.findViewById(android.R.id.tabs);
////        videotabcontent = l.findViewById(android.R.id.tabcontent);
////        videotabwidget.setBackgroundColor(Color.RED);
////        LocalActivityManager manager = new LocalActivityManager(VideoPlayActivity.this,false);
//
//        videotabhost.setup();// 初始化
//
//        LayoutInflater inflater = LayoutInflater.from(this);//声明并实例化一个LayoutInflater对象
//        inflater.inflate(R.layout.videotabcontent1,videotabhost.getTabContentView());
//        inflater.inflate(R.layout.videotabcontent2,videotabhost.getTabContentView());
//        videotabhost.addTab(videotabhost.newTabSpec("tab1").setIndicator("1").setContent(R.id.videotabcontent1));
//        videotabhost.addTab(videotabhost.newTabSpec("tab2").setIndicator("2").setContent(R.id.videotabcontent1));


    }
}
