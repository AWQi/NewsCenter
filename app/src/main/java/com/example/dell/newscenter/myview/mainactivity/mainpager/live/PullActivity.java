package com.example.dell.newscenter.myview.mainactivity.mainpager.live;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Studio;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class PullActivity extends AppCompatActivity {
    private static final String TAG = "NewCenter";
private Intent intent = null;
private Studio studio = null;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_live_pull);
            intent = getIntent();
            studio = intent.getParcelableExtra("studio");
            Log.d(TAG, "studioURL"+studio.getStudioUrl());
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
            //这里使用的是Demo中提供的AndroidMediaController类控制播放相关操作
            final  VideoPlayerIJK ijkPlayer =  findViewById(R.id.studioPalyer);
            ijkPlayer.setVideoPath(studio.getStudioUrl());
            ijkPlayer.start();
            ijkPlayer.setListener(new VideoPlayerIJK.VideoPlayerListener(){
                @Override
                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

                }

                @Override
                public void onSeekComplete(IMediaPlayer iMediaPlayer) {

                }

                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    ijkPlayer.start();
                }

                @Override
                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                    return false;
                }

                @Override
                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                    return false;
                }

                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {

                }

                @Override
                public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

                }
            });


        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            IjkMediaPlayer.native_profileEnd();

        }
    }