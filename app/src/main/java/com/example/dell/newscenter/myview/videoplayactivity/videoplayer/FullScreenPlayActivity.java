package com.example.dell.newscenter.myview.videoplayactivity.videoplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dell.newscenter.R;

public class FullScreenPlayActivity extends AppCompatActivity {
    private VideoView fullScreenPlayVV = null;
    private  String url = null;
    private Intent intent;
    private ProgressBar progressBar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_play);
        intent = getIntent();
        url = intent.getStringExtra("url");

        fullScreenPlayVV = findViewById(R.id.fullScreenPlayVV);
        MediaController controller = new MediaController(FullScreenPlayActivity.this);
//        MyMediaController controller  = new MyMediaController(FullScreenPlayActivity.this);
        fullScreenPlayVV.setMediaController(controller);
        fullScreenPlayVV.setVideoPath(url);
        fullScreenPlayVV.start();
        progressBar = findViewById(R.id.fullScreenPlayPB);
        fullScreenPlayVV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(FullScreenPlayActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
