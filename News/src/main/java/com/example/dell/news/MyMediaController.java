//package com.example.dell.news;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.ActivityInfo;
//import android.content.res.Configuration;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.Build;
//import android.util.AttributeSet;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.MediaController;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//public class MyMediaController extends MediaController {
//private VideoView videoView;
//private SeekBar sb_play;
//private  SeekBar sb_volume;
//private ImageView  iv_playControl;
//private  ImageView iv_screenSwitch;
//private ImageView iv_volume;
//private  TextView tv_currentTime;
//private  TextView tv_totalTime;
//private LinearLayout ll_volumeControl;
//private  LinearLayout ll_control;
//private  RelativeLayout rl_video;
//private  AudioManager audioManager;
//private Context context;
//    public MyMediaController(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//    }
//
//    private void initUI() {
//        videoView = (VideoView) findViewById(R.id.vv_player);
//        sb_play = (SeekBar) findViewById(R.id.sb_play);
//        sb_volume = (SeekBar) findViewById(R.id.sb_volume);
//        iv_playControl = (ImageView) findViewById(R.id.iv_playControl);
//        iv_screenSwitch = (ImageView) findViewById(R.id.iv_screenSwitch);
//        iv_volume = (ImageView) findViewById(R.id.iv_volume);
//        tv_currentTime = (TextView) findViewById(R.id.tv_currentTime);
//        tv_totalTime = (TextView) findViewById(R.id.tv_totalTime);
//        ll_volumeControl = (LinearLayout) findViewById(R.id.ll_volumeControl);
//        ll_control = (LinearLayout) findViewById(R.id.ll_control);
//        rl_video = (RelativeLayout) findViewById(R.id.rl_video);
//        sb_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
//        sb_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
//    }
//    private void initEvent() {
//        iv_playControl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (videoView.isPlaying()) {
//                    setPauseStatus();
//                    videoView.pause();
//                    uiHandler.removeMessages(UPDATE_TIME);
//                } else {
//                    setPlayStatus();
//                    videoView.start();
//                    uiHandler.sendEmptyMessage(UPDATE_TIME);
//                }
//            }
//        });
//        sb_play.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser) {
//                    videoView.seekTo(progress);
//                    Utils.updateTimeFormat(tv_currentTime, progress);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                uiHandler.removeMessages(UPDATE_TIME);
//                if (!videoView.isPlaying()) {
//                    setPlayStatus();
//                    videoView.start();
//                }
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                uiHandler.sendEmptyMessage(UPDATE_TIME);
//            }
//        });
//        sb_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                iv_playControl.setImageResource(R.drawable.);
//                videoView.seekTo(0);
//                sb_play.setProgress(0);
//                Utils.updateTimeFormat(tv_currentTime, 0);
//                videoView.pause();
//                uiHandler.removeMessages(UPDATE_TIME);
//            }
//        });
//        iv_screenSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    iv_screenSwitch.setImageResource(R.drawable.exit_full_screen);
//                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    iv_screenSwitch.setImageResource(R.drawable.full_screen);
//                }
//            }
//        });
//        videoView.setOnTouchListener(this);
//    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        screenWidth = getResources().getDisplayMetrics().widthPixels;
//        screenHeight = getResources().getDisplayMetrics().heightPixels;
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            setSystemUiHide();
//            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            iv_screenSwitch.setImageResource(R.drawable.exit_full_screen);
//            ll_volumeControl.setVisibility(View.VISIBLE);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(MainActivity.this, 240f));
//            iv_screenSwitch.setImageResource(R.drawable.full_screen);
//            ll_volumeControl.setVisibility(View.GONE);
//            setSystemUiVisible();
//        }
//    }
//    /**
//     * 设置布局大小
//     *
//     * @param width  宽度
//     * @param height 高度
//     */
//    private void setVideoViewScale(int width, int height) {
//        ViewGroup.LayoutParams params = rl_video.getLayoutParams();
//        params.width = width;
//        params.height = height;
//        rl_video.setLayoutParams(params);
//        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
//        layoutParams.width = width;
//        layoutParams.height = height;
//        videoView.setLayoutParams(layoutParams);
//    }
//    private void setSystemUiHide() {
//        if (Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
//
//    private void setSystemUiVisible() {
//        if (Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        }
//    }
//    private void changeVolume(float offset) {
//        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        int index = (int) (offset / screenHeight * maxVolume);
//        int volume = Math.max(currentVolume + index, 0);
//        volume = Math.min(volume, maxVolume);
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
//        sb_volume.setProgress(volume);
//    }
//
//    private void changeBrightness(float offset) {
//        WindowManager.LayoutParams attributes = getWindow().getAttributes();
//        float brightness = attributes.screenBrightness;
//        float index = offset / screenHeight / 2;
//        brightness = Math.max(brightness + index, WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF);
//        brightness = Math.min(WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL, brightness);
//        attributes.screenBrightness = brightness;
//        getWindow().setAttributes(attributes);
//    }
//}