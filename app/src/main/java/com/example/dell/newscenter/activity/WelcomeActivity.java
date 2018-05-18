package com.example.dell.newscenter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.utils.CacheUtils;

public class WelcomeActivity extends AppCompatActivity {
    static  public final String START_MAIN = "START_MAIN";
    private RelativeLayout rl_welcome_root = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        rl_welcome_root = findViewById(R.id.rl_welcome_root);


        // 渐变动画 缩放动画 旋转动画
        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(1000);// 时长
        aa.setFillAfter(true);//  播放完停留

        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(1000);
        aa.setFillAfter(true);

        RotateAnimation ra = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(1000);
        ra.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        //添加动画没有先后
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);

        rl_welcome_root.startAnimation(set);
        // 动画监听
        set.setAnimationListener(new MyAnimationListener());
    }

    class  MyAnimationListener implements Animation.AnimationListener{

        @Override//  开始播放
        public void onAnimationStart(Animation animation) {

        }

        @Override//  播放结束
        public void onAnimationEnd(Animation animation) {
              Toast.makeText(WelcomeActivity.this,"启动完成",Toast.LENGTH_SHORT).show();
                // 判断是否进入过主页面 ，如果没有，进入引导页面，否则直接进入主页面
                boolean isStartMain   = CacheUtils.getBoolean(WelcomeActivity.this, "START_MAIN");
                Intent intent;
                if (isStartMain){
                    //  如果进入过主页面 ，直接进入主页面
                     intent = new Intent(WelcomeActivity.this,MainActivity.class);
                }else {
                    //  如果没有进入过主页面，进入引导页面
                     intent = new Intent(WelcomeActivity.this,GuideActivity.class);
                }
                startActivity(intent);
                finish();
        }

        @Override  //   重新播放
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
