//package com.example.dell.newscenter.myview.videoplayactivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.graphics.Color;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import android.widget.VideoView;
//
//import com.example.dell.newscenter.R;
//import com.example.dell.newscenter.utils.ActivityUtil;
//
//import java.util.Random;
//
//import master.flame.danmaku.controller.DrawHandler;
//import master.flame.danmaku.danmaku.model.BaseDanmaku;
//import master.flame.danmaku.danmaku.model.DanmakuTimer;
//import master.flame.danmaku.danmaku.model.IDanmakus;
//import master.flame.danmaku.danmaku.model.android.DanmakuContext;
//import master.flame.danmaku.danmaku.model.android.Danmakus;
//import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
//import master.flame.danmaku.ui.widget.DanmakuView;
//
//public class DanMuUtil  {
//
//    private  boolean showDanmaku;
//    private DanmakuView danmakuView;
//    private DanmakuContext danmakuContext;
//    private Context context;
//    private  VideoView videoView;
//    //  弹幕绑定  videoView
//    public  void setDanMuView(Context context,VideoView videoView){
//            this.context = context;
//            this.videoView = videoView;
//            danmakuView= ActivityUtil.scanForActivity(context).findViewById(R.id.DanmakuView);
//            danmakuView.enableDanmakuDrawingCache(true);
//            danmakuView.setCallback(new DrawHandler.Callback() {
//                @Override
//                public void prepared() {
//                    showDanmaku = true;
//                    danmakuView.start();
//                    generateSomeDanmaku();
//                }
//                @Override
//                public void updateTimer(DanmakuTimer timer) { }
//                @Override
//                public void danmakuShown(BaseDanmaku danmaku) { }
//                @Override
//                public void drawingFinished() { }
//            });
//            danmakuContext = DanmakuContext.create();
//            danmakuView.prepare(parser,danmakuContext);
//
//
//        }
//
//
//    private BaseDanmakuParser parser = new BaseDanmakuParser() {
//        @Override
//        protected IDanmakus parse() {
//            return_icon new Danmakus();
//        }
//    };
//    /**
//     *   添加弹幕
//     * @param content
//     * @param withBorder
//     */
//    private  void  addDanmaku(String content,boolean withBorder){
//        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_LR);
//        danmaku.text = content;
//        danmaku.padding = 5;
//        danmaku.textSize = 50;//DensityUtil.px2dip(MainActivity.this,100);
//        danmaku.textColor = Color.BLACK;
//        danmaku.setTime(danmakuView.getCurrentTime());
//        if (withBorder){
//            danmaku.borderColor = Color.GREEN;
//        }
//        danmakuView.addDanmaku(danmaku);
//    }
//    /**
//     *   随机生成弹幕
//     */
//    private void generateSomeDanmaku() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(showDanmaku){
//                    int time = new Random().nextInt(300);
//                    String content = ""+time+time;
//                    addDanmaku(content,false);
//
//                    try {
//                        Thread.sleep(time);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }
//    /**
//     *暂停时 弹幕跟着暂停
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (danmakuView!=null&&danmakuView.isPrepared()){
//            danmakuView.resume();
//        }
//    }
//    /**
//     *   退出时销毁弹幕
//     */
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        showDanmaku = false;
//        if (danmakuView!=null){
//            danmakuView.release();// 释放
//            danmakuView = null;
//        }
//    }
//
//}
