package com.example.dell.newscenter.myview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.FlowLayout;

import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends Fragment{
    private static final String TAG = "LiveFragment";
    private List<Project> projectList = new ArrayList();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  请求   子控件数据


        //    加载fragment
        View rootView  = inflater.inflate(R.layout.recommendfragment,container,false);
        return rootView;
    }

    /**
     *
     *  动态改变  fragment  向layout添加 fragment
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         *
         *  获取数据
         */
        FlowLayout flowLayout =getActivity().findViewById(R.id.recommendfragment);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String imageURL = "http://f.hiphotos.baidu.com/image/pic/item/35a85edf8db1cb13f423dfa0d154564e92584b3f.jpg";
        String videoURL = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
        Project project = new Project(1,"AA",imageURL,videoURL,0,0,"BB");

        ProjectFragment fragment = new ProjectFragment(project);
        transaction.add(R.id.recommendfragment, fragment);

        transaction.commit();


//        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
//        Button bt= new Button(getContext());
//        bt.setText("dscsc");
//        flowLayout.addView(bt);
        Log.d(TAG, "onActivityCreated: "+ "向fragment添加  view");



    }
}


