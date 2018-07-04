package com.example.dell.newscenter.myview.mainactivity.mainpager.chase.play;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Muscovy;

public class MuscovyPlayActivity extends AppCompatActivity {
private VideoView muscovyVV;
private ListView muscovyLV;
private Muscovy muscovy;
private RelativeLayout playRL;
private DrawerLayout muscovyDrawer;
private LinearLayout muscovyProgress;
    private int n = 1; // 当前播放第几集
    private String[] data ;
    private  float srcX = 0;
    private float nowX = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscovy_play);
        muscovy = getIntent().getParcelableExtra("muscovy");
        muscovyVV = findViewById(R.id.muscovyVV);
        muscovyLV = findViewById(R.id.muscovyLV);
        playRL = findViewById(R.id.playRL);
        muscovyDrawer = findViewById(R.id.muscovyDrawer);
        muscovyProgress = findViewById(R.id.muscovyProgress);

        /**
         *  加载  播放器
         */
        loadVideo(n);
        MediaController mediaController  = new MediaController(MuscovyPlayActivity.this);
        muscovyVV.setMediaController(mediaController);
        // 设置 播放控制

         ///    加载播放监听
        muscovyVV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(MuscovyPlayActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                   muscovyVV.setBackground(null);
                   muscovyProgress.setVisibility(View.GONE);
                    muscovyVV.start();
            }
        });
        /**
         *   卡顿监听
         */
        muscovyVV.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:  // 开始卡顿
                        muscovyProgress.setVisibility(View.VISIBLE);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END: // 结束卡顿
                        muscovyProgress.setVisibility(View.GONE);
                        break;
                    default:break;
                }
                return false;
            }
        });
        //  播放完成  监听
        muscovyVV.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                     n++; //循环
                     if (n>muscovy.getNum()){
                         n=1;
                     }
                loadVideo(n);
            }
        });
        /**
         *
         *  加载抽屉
         */
        loadList();
        muscovyLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                n=position+1;
                loadVideo(n);
            }
        });
        // VideoView 添加 controller后  会  拦截 videoview的  监听器事件
        playRL.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                muscovyDrawer.openDrawer(GravityCompat.END);
//                if (event.getAction()==MotionEvent.ACTION_DOWN){
//                    srcX=event.getX();
//
//                    }
//                if (event.getAction()==MotionEvent.ACTION_MOVE){
//                       nowX = event.getX();
//                }
                return false;
            }
        });
    }
    public void loadVideo(int n) {
        Uri uri = Uri.parse(muscovy.getVideoUrl()+n+".mp4");
        muscovyVV.setVideoURI(uri);
        muscovyProgress.setVisibility(View.VISIBLE);
    }
    public  void loadList(){
         data = new String[muscovy.getNum()];
         for (int i = 0;i<muscovy.getNum();i++){
             data[i] = "第"+(i+1)+"集";
         }
         //更改  字体颜色  仿照android.R.layout.simple_item_1
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(
                MuscovyPlayActivity.this,R.layout.muscovy_num_item,data);
        muscovyLV.setAdapter(adapter);

    }
}
