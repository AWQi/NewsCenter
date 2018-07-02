package com.example.dell.newscenter.myview.mainactivity.mainpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.project.ProjectRecyclerViewLayout;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class RecommendFragment extends Fragment{
    static  public  int page = 1;
    private static final String TAG = "RecommendFragment";
    private List<Project> projectList = new ArrayList<>();
    private ProjectRecyclerViewLayout reCommendCV;
    private  View recommendFragment;
    private SwipeRefreshLayout swipeRefreshLayout = null;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    加载fragment
        recommendFragment  = inflater.inflate(R.layout.recommend_fragment,container,false);
        recommendFragment.findViewById(R.id.reCommendCV);
        return recommendFragment;
    }
    /**
     * 获取数据
     *
     */
    public void getDate(final Activity activity) {
//        String imageUrl = "http://img2.woyaogexing.com/2018/05/20/9cf1a7391ce95f13!400x400_big.jpg";
//        String videoUrl ="http://140.143.16.51/live/test.m3u8";
//        String authorHeadUrl = "http://img2.woyaogexing.com/2018/05/24/d29f005920079fc3!400x400_big.jpg";
//        Project project = new Project(1,"ADWS",imageUrl,videoUrl,0,0,"ACSS",1,"AWQI",authorHeadUrl);
//        projectList.add(project);
//        projectList.add(project);
//        projectList.add(project);
//        projectList.add(project);


        JoyHttpUtil.commendDynamics(page, new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.PROJECT_LIST_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
                //               Log.d(TAG, "analyticData: "+joyResult.getData());
                activity.runOnUiThread(new Runnable() { //  开UI 线程
                    @Override
                    public void run() {
                        List<Project> list = joyList.getData();
//                        projectList.addAll(list);
                        //  要  适配  刷新  不能 使用addAll
                        for (Project p:list){
                            projectList.add(0,p);
                        }
                        reCommendCV.getDate(projectList);
                        page++;
                    }
                });
            }
        });
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
        getDate(getActivity());
        reCommendCV = recommendFragment.findViewById(R.id.reCommendCV);
        reCommendCV.getDate(projectList);
        swipeRefreshLayout = getActivity().findViewById(R.id.reCommendRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             *
             *   重新获取数据  进行展示
             */
            @Override
            public void onRefresh() {
                JoyHttpUtil.commendDynamics(page, new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.PROJECT_LIST_TYPE) {
                    @Override
                    public void analyticData(final JoyResult.JoyList joyList) {
                        //               Log.d(TAG, "analyticData: "+joyResult.getData());
                        getActivity().runOnUiThread(new Runnable() { //  开UI 线程
                            @Override
                            public void run() {
                                List<Project> list = joyList.getData();
//                        projectList.addAll(list);
                                //  要  适配 刷新 不能 使用addAll  必须往前插
                                for (Project p:list){
                                    projectList.add(0,p);
                                }
                                reCommendCV.getDate(projectList);
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                });
            }
        });
    }

}




