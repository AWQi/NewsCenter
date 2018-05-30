package com.example.dell.newscenter.myview.videoplayactivity.videoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dell.newscenter.R;

public class FullScreenPlayActivity extends AppCompatActivity {
    private VideoView fullScreenPlayVV = null;
    private  String url = null;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_play);
        intent = getIntent();
        url = intent.getStringExtra("url");

        fullScreenPlayVV = findViewById(R.id.fullScreenPlayVV);
        MediaController controller = new MediaController(FullScreenPlayActivity.this);
        fullScreenPlayVV.setMediaController(controller);
        fullScreenPlayVV.setVideoPath(url);
        fullScreenPlayVV.start();

    }
}
