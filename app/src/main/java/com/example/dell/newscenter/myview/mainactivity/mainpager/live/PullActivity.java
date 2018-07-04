package com.example.dell.newscenter.myview.mainactivity.mainpager.live;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.activity.MainActivity;
import com.example.dell.newscenter.bean.Studio;
import com.example.dell.newscenter.utils.ApplicationUtil;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_BUFFERING_END;
import static tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_BUFFERING_START;
import static tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;


public class PullActivity extends AppCompatActivity {
    private static final String TAG = "NewCenter";
private Intent intent = null;
private Studio studio = null;
public TelephonyManager mTelephonyManager;
public PhoneStatListener mListener;
private LinearLayout progressLayout = null;
private Toolbar liveToolBar = null;
private ImageView liveToolBarIV = null;
private TextView liveToolBarTV=null;
private  TextView bufferSpeedTV;
private  boolean  toolBarVisibility = true;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            intent = getIntent();
            studio = intent.getParcelableExtra("studio");
            //设置   横屏
            Log.d(TAG, "onCreate: ----------------------------------"+studio.getPremiere());
            int n  = studio.getPremiere();
            if (studio.getPremiere()==0){
                if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
            //   全屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            // 去标题栏
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_live_pull);

//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 屏幕常亮
            progressLayout = findViewById(R.id.progressLayout);
            liveToolBar = findViewById(R.id.liveToolBar);
            liveToolBarIV = findViewById(R.id.liveToolBarIV);
            liveToolBarTV = findViewById(R.id.liveToolBarTV);
            bufferSpeedTV = findViewById(R.id.bufferSpeedTV);





            Log.d(TAG, "studioURL"+studio.getStudioUrl());
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
            final  VideoPlayerIJK ijkPlayer =  findViewById(R.id.studioPalyer);
            ijkPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        toolBarVisibility=!toolBarVisibility;
                        if (toolBarVisibility){
                            liveToolBar.setVisibility(View.VISIBLE);
                        }else {
                            liveToolBar.setVisibility(View.INVISIBLE);
                        }
                }
            });
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
                    progressLayout.setAlpha(0);//使用GONE  去除后无法再显示
//                    progressLayout.setVisibility(View.GONE);
                }

                @Override
                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                    Log.d(TAG, "onInfo: ----------------------------------------------------------");
                    switch (i){
                        case MEDIA_INFO_BUFFERING_START :  // 开始卡顿
                            Log.d(TAG, "i:--------------------------- start");
//                            progressLayout.setVisibility(View.VISIBLE);
                            progressLayout.setAlpha(1);

                        case MEDIA_INFO_BUFFERING_END : // 卡顿结束
                            Log.d(TAG, "i:--------------------------- stop");
                            progressLayout.setAlpha(0);
//                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH: //   下载速度
//                            bufferSpeedTV.setText(i1);
                            //显示下载速度
//                      Toast.show("download rate:" + extra);
                            break;
//                            progressLayout.setVisibility(View.GONE);
                        default:break;
                    }

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

            liveToolBarIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            liveToolBarTV.setText(studio.getTitle());





            //获取telephonyManager
            mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            //开始监听
            mListener = new PhoneStatListener();
            //监听信号强度
            mTelephonyManager.listen(mListener, PhoneStatListener.LISTEN_SIGNAL_STRENGTHS);


        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            IjkMediaPlayer.native_profileEnd();
        }





    private static final int NETWORKTYPE_WIFI = 0;
    private static final int NETWORKTYPE_4G = 1;
    private static final int NETWORKTYPE_2G = 2;
    private static final int NETWORKTYPE_NONE = 3;

    private class PhoneStatListener extends PhoneStateListener {
        //获取信号强度

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            //获取网络信号强度
            //获取0-4的5种信号级别，越大信号越好,但是api23开始才能用
//            int level = signalStrength.getLevel();
            int gsmSignalStrength = signalStrength.getGsmSignalStrength();
//获取网络类型
            if (gsmSignalStrength<3){
                Toast.makeText(PullActivity.this,"网络状态不佳",Toast.LENGTH_SHORT).show();
            }
//            int netWorkType = getNetWorkType(PullActivity.this);
//            switch (netWorkType) {
//                case NETWORKTYPE_WIFI:
//                    mTextView.setText("当前网络为wifi,信号强度为：" + gsmSignalStrength);
//                    break;
//                case NETWORKTYPE_2G:
//                    mTextView.setText("当前网络为2G移动网络,信号强度为：" + gsmSignalStrength);
//                    break;
//                case NETWORKTYPE_4G:
//                    mTextView.setText("当前网络为4G移动网络,信号强度为：" + gsmSignalStrength);
//                    break;
//                case NETWORKTYPE_NONE:
//                    mTextView.setText("当前没有网络,信号强度为：" + gsmSignalStrength);
//                    break;
//                case -1:
//                    mTextView.setText("当前网络错误,信号强度为：" + gsmSignalStrength);
//                    break;
//            }
        }
    }



}