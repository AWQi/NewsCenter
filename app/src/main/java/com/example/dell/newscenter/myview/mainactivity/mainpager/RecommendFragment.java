package com.example.dell.newscenter.myview.mainactivity.mainpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.project.ProjectRecyclerViewLayout;

import java.util.ArrayList;
import java.util.List;
public class RecommendFragment extends Fragment{
    private static final String TAG = "RecommendFragment";
    private List<Project> projectList = new ArrayList<>();
    private ProjectRecyclerViewLayout reCommendCV;
    private  View rootView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    加载fragment
        rootView  = inflater.inflate(R.layout.recommend_fragment,container,false);
        return rootView;
    }
    /**
     * 获取数据
     *
     */
    public void getDate() {
        String imageUrl = "http://img2.woyaogexing.com/2018/05/20/9cf1a7391ce95f13!400x400_big.jpg";
        String videoUrl ="http://140.143.16.51/live/test.m3u8";
        String authorHeadUrl = "http://img2.woyaogexing.com/2018/05/24/d29f005920079fc3!400x400_big.jpg";
        Project project = new Project(1,"ADWS",imageUrl,videoUrl,0,0,"ACSS",1,"AWQI",authorHeadUrl);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
    }
    /**
     *
     *  动态改变  fragment  recycler  添加  live_item
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        projectList.clear();
        getDate();
        reCommendCV = rootView.findViewById(R.id.reCommendCV);
        reCommendCV.getDate(projectList);
    }

}




