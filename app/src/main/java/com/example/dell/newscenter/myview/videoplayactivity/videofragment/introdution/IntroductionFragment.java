package com.example.dell.newscenter.myview.videoplayactivity.videofragment.introdution;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownloadProjectDBUtil;
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownloadUtil;
import com.example.dell.newscenter.myview.InfoActivity.qrcode.QrBean;
import com.example.dell.newscenter.myview.InfoActivity.userinfo.UserInfoActivity;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.JsonUtil;
import com.google.zxing.WriterException;
import com.qrcode.zxing.decoding.zxing.encoding.EncodingHandler;

public class IntroductionFragment extends Fragment implements View.OnTouchListener{
    private static final String TAG = "IntroductionFragment";
    private TextView introductionTitleTV;
  private CircleImageView introductionHeadCV;
  private TextView introductionAuthorNameTV;
  private Button introductionAttentBtn;
  private ImageView introductionPraiseIV;
  private ImageView  introductionCollectIV;
  private ImageView introductionDownloadIV;
  private ImageView introductionShareIV;
  private Activity activity;
  private Context context;
  private Project project = null;
  private ImageView shareQrIV;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        context = getContext();
        //  请求   子控件数据
        //    加载fragment
        View rootView  = inflater.inflate(R.layout.introduction_fragment,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDate();
        introductionTitleTV = activity.findViewById(R.id.introductionTitleTV);
        introductionTitleTV.setSelected(true);//文字滚动
        introductionAuthorNameTV = activity.findViewById(R.id.introductionAuthorNameTV);
        introductionHeadCV =activity.findViewById(R.id.introductionHeadCV);
        introductionAttentBtn = activity.findViewById(R.id.introductionAttentBtn);
        introductionPraiseIV = activity.findViewById(R.id.introductionPraiseIV);
        introductionCollectIV = activity.findViewById(R.id.introductionCollectIV);
        introductionDownloadIV = activity.findViewById(R.id.introductionDownloadIV);
        introductionShareIV = activity.findViewById(R.id.introductionShareIV);
        shareQrIV = activity.findViewById(R.id.shareQrIV);

        /*  设置要展示的信息*/
        Glide.with(context).load(project.getAuthorHeadUrl())
                .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                .fitCenter().into(introductionHeadCV);
        introductionAuthorNameTV.setText(project.getAuthorName());
        introductionTitleTV.setText(project.getTitle());

        /* 注册点击事件*/
//        introductionHeadCV.setOnClickListener(this);
//        introductionAttentBtn.setOnClickListener(this);
//        introductionPraiseIV.setOnClickListener(this);
//        introductionCollectIV .setOnClickListener(this);
//        introductionDownloadIV.setOnClickListener(this);
//        introductionShareIV.setOnClickListener(this);

        introductionHeadCV.setOnTouchListener(this);
        introductionAttentBtn.setOnTouchListener(this);
        introductionPraiseIV.setOnTouchListener(this);
        introductionCollectIV .setOnTouchListener(this);
        introductionDownloadIV.setOnTouchListener(this);
        introductionShareIV.setOnTouchListener(this);




    }
    /**
     *
     *   获取  studio_item
     *       由mainactivity ,或其他 videoActivity 传送，  可以由context 获取intent 从而获取参数
     */
    public void getDate(){
        Intent intent = ActivityUtil.scanForActivity(context).getIntent();
        project = (Project)intent.getParcelableExtra("studio_item");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        startOnClickAnimation(v);

        switch (id){
            // 作者
            case R.id.introductionHeadCV:
                authorInfo();break;
            //关注
            case R.id.introductionAttentBtn:
                attent();break;
            // 点赞
            case R.id.introductionPraiseIV:
                praise();break;
            //收藏
            case R.id.introductionCollectIV:
                collect();break;
            //下载
            case R.id.introductionDownloadIV:
                download();break;
            //分享
            case R.id.introductionShareIV:
                share();break;

        }

        return false;
    }
    public  void startOnClickAnimation(View view){
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation s1 = new ScaleAnimation(1f,1.5f,1f,1.5f);
        s1.setDuration(500);

        view.startAnimation(s1);
        final ScaleAnimation s2 = new ScaleAnimation(1.5f,1f,1.5f,1f);
        s2.setDuration(500);
        s1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                    s2.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void authorInfo(){
        /**
         *   实际根据 id 做网络请求获取  先做代替
         *
         */
        User user =  ApplicationUtil.getUser();
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    /**
     *  关注
     */
    public void attent(){
        // 发送网络请求关注
    }

    /**
     *  点赞
     */
    public  void praise(){
        introductionPraiseIV.setImageResource(R.drawable.praised);//点赞后换图标
    }

    /**
     *  收藏
     */
    public  void collect(){
        introductionCollectIV.setImageResource(R.drawable.collectioned);//收藏后换图标
    }
    /**
     *  下载
     */
    public  void download(){
        Toast.makeText(context,"检查下载数据",Toast.LENGTH_SHORT).show();
        //   检查  重复   重复就提示
        if (DownloadProjectDBUtil.queryOne(project.getId())!=null){
            new AlertDialog.Builder(context)
                    .setTitle("提示")  //  ;
                    .setMessage("该视频已下载，是否重新下载？")
                    .setIcon(R.drawable.download)  //  图标
                    .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                DownloadUtil downloadUtil = new DownloadUtil(project,activity);
                            } catch (Exception e) {
                                Toast.makeText(context,"下载出错",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    })
                    .create()
                    .show();
        }else {
            DownloadUtil downloadUtil = new DownloadUtil(project,activity);

        }

    }
    /**
     *  分享
     */
    public void share(){

        try {
            // 填充图片
            String  projectInfo = JsonUtil.ObjToStr(project);
            QrBean qrBean = new QrBean(QrBean.PROJECT,projectInfo);
            String qrStr = JsonUtil.ObjToStr(qrBean);
            Bitmap qrBitmap = EncodingHandler.createQRCode(qrStr,800);
            shareQrIV.setImageBitmap(qrBitmap);
            // 设置动画
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setDuration(500);
            shareQrIV.startAnimation(alphaAnimation);
            // 可见
            shareQrIV.setVisibility(View.VISIBLE);
            // 点击后关闭
            shareQrIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareQrIV.setVisibility(View.GONE);
                }
            });

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
