package com.example.dell.newscenter.myview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.adapter.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;


public class LiveFragment extends Fragment{

    private static final String TAG = "LiveFragment";
    String url = "http://b.hiphotos.baidu.com/image/pic/item/eaf81a4c510fd9f9bebab92f292dd42a2934a4c3.jpg";

    Project[] projects = {new Project("p1",url)
            ,new Project("p1",url)
            ,new Project("p1",url)
            ,new Project("p1",url),
            new Project("p1",url),
            new Project("p1",url),
            new Project("p1",url)};

    private List<Project> projectList = new ArrayList<>();
    private ProjectAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    /**
     *
     *      创建 fragment  控件
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //    加载fragment
                View rootView  = inflater.inflate(R.layout.livefragment,container,false);
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


                //  请求   子控件数据
        projectList.clear();
        for (int i = 0;i<projects.length;i++){
            int index = i;
            projectList.add(projects[i]);
        }


/**
 *             swipRefreshLayout
 *
  */

        RecyclerView recyclerView = getActivity().findViewById(R.id.livefragment_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProjectAdapter(projectList);
        recyclerView.setAdapter(adapter);
        //  声明 下拉刷新框
        swipeRefresh = getActivity().findViewById(R.id.livefragment_swiperedresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             *
             *   重新获取数据  进行展示
             */
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: 下拉刷新中");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                      getActivity().runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                    /**
                    *
                    * //  重新获取数据
                    */

//                              initproject();
                              Log.d(TAG, "runOnUiThread：run刷新中: ");
                              adapter.notifyDataSetChanged();///   通知刷新  project
                              swipeRefresh.setRefreshing(false);
                          }
                      });
                    }
                }).start();
            }
        });
/**
 *
 *      MyswipRefreshLayout
 *
 *
 */
//        final BaseAdapter adapter = new ArrayAdapter<Project>(getContext(),android.R.layout.simple_expandable_list_item_1,projectList);
//        final RecyclerView recyclerView = getActivity().findViewById(R.id.livefragment_recycler);
//        final MySwipRefreshLayout swipeRefreshView = getActivity().findViewById(R.id.livefragment_swiperedresh);
//        recyclerView.setAdapter(adapter);
//        swipeRefreshView.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light);
//
//        // 设置下拉刷新监听器
//        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(getContext(), "refresh", Toast.LENGTH_SHORT).show();
//
//                swipeRefreshView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Log.d(TAG, "下拉刷新中: ");
//                        adapter.notifyDataSetChanged();
//                        // 更新完后调用该方法结束刷新
//                        swipeRefreshView.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
//
//        // 加载监听器
//        swipeRefreshView.setOnLoadListener(new MySwipRefreshLayout.OnLoadListener() {
//            @Override
//            public void onLoad() {
//                Toast.makeText(getContext(), "load", Toast.LENGTH_SHORT).show();
//
//                swipeRefreshView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d(TAG, "上拉加载中: ");
//                        adapter.notifyDataSetChanged();
//                        // 加载完后调用该方法
//                        swipeRefreshView.setLoading(false);
//                    }
//                }, 1500);
//            }
//        });



        Log.d(TAG, "onActivityCreated: "+ "向fragment添加  view");



    }
}
