package com.example.dell.newscenter.myview.mainactivity.mainpager.chase.play;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Muscovy;

public class MuscovyPlayActivity extends AppCompatActivity {
private VideoView muscovyVV;
private ListView muscovyLV;
private Muscovy muscovy;
private  int n = 1; // 当前播放第几集

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscovy_play);
        muscovy = getIntent().getParcelableExtra("muscovy");
        muscovyVV = findViewById(R.id.muscovyVV);
        muscovyLV = findViewById(R.id.muscovyLV);
        loadView();
        MediaController mediaController  = new MediaController(MuscovyPlayActivity.this);
        muscovyVV.setMediaController(mediaController);
        // 设置 播放控制

         ///    加载监听
        muscovyVV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(MuscovyPlayActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                   muscovyVV.setBackground(null);
                    muscovyVV.start();
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
                loadView();
            }
        });
    }
    public void loadView() {
        Uri uri = Uri.parse(muscovy.getVideoUrl()+n+".mp4");
        muscovyVV.setVideoURI(uri);
    }

}
