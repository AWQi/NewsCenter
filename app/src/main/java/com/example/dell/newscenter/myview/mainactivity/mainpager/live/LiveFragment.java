package com.example.dell.newscenter.myview.mainactivity.mainpager.live;

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
import com.example.dell.newscenter.myview.base.project.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {
    private static final String TAG = "LiveFragment";
    private List<Project> projectList = new ArrayList<>();
    private ProjectAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    /**
     * 创建 fragment  控件
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    加载fragment
        View rootView = inflater.inflate(R.layout.livefragment, container, false);
        return rootView;
    }

    public void getDate() {
        String url = "http://b.hiphotos.baidu.com/image/pic/item/eaf81a4c510fd9f9bebab92f292dd42a2934a4c3.jpg";
        Project project = new Project("p1", url);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);

    }

    /**
     * 动态改变  fragment  向layout添加 fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  请求   子控件数据
        projectList.clear();
        getDate();
        RecyclerView recyclerView = getActivity().findViewById(R.id.livefragment_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /**
                                 *
                                 * //  重新获取数据
                                 */
                                getDate();
                                Log.d(TAG, "runOnUiThread：run刷新中: ");
                                adapter.notifyDataSetChanged();///   通知刷新  project
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });


        Log.d(TAG, "onActivityCreated: " + "向fragment添加  view");


    }
}
