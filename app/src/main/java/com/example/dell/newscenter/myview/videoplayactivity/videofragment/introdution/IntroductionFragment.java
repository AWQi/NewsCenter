package com.example.dell.newscenter.myview.videoplayactivity.videofragment.introdution;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.utils.ActivityUtil;

public class IntroductionFragment extends Fragment implements View.OnClickListener{
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        context = getContext();
        //  请求   子控件数据
        //    加载fragment
        View rootView  = inflater.inflate(R.layout.introductionfragment,container,false);
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

        Glide.with(context).load(project.getAuthorHeadUrl())
                .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                .fitCenter().into(introductionHeadCV);
        introductionAuthorNameTV.setText(project.getAuthorName());
        introductionTitleTV.setText(project.getTitle());




    }
    /**
     *
     *   获取  project
     *       由mainactivity ,或其他 videoActivity 传送，  可以由context 获取intent 从而获取参数
     */
    public void getDate(){
        Intent intent = ActivityUtil.scanForActivity(context).getIntent();
        project = (Project)intent.getParcelableExtra("project");
        Log.d(TAG, "getDate: "+project.toString());
    }
    @Override
    public void onClick(View v) {

    }
}
