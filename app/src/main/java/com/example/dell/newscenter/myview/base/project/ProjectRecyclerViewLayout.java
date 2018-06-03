package com.example.dell.newscenter.myview.base.project;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class ProjectRecyclerViewLayout extends LinearLayout{
    private static final String TAG = "ProjectRecyclerViewLayout";
    private List<Project> projectList = new ArrayList<>();
    private ProjectAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private  Context context = null;
    public ProjectRecyclerViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.project_recyclerview_layout,this);
        onCreate();
    }
    public void getDate(List list) {
        if (list!=null) {
            /**
             * projectList = list;
             *   不能直接使用等于，而要使用addAll，
             *   因为Adapter绑定的是projectList中存在的地址指向，也就是原来的list对象，而不是projectList这个变量，
             *   如果只是等于，就是换了 projectList内的地址 ， Adapter绑定的对象并没有变
             *   如果是先clear 在 addAll 就直接操作原来的list对象
             */
            projectList.clear();
            projectList.addAll(list);
            adapter.notifyDataSetChanged();
        }
// else {
//            String imageUrl = "https://i04picsos.sogoucdn.com/3c28af542f2d49f7-8437bbc8e07dde51-03547c8c564c14bb2bd98c8798ce94d7_qq";
//            String videoUrl ="http://ips.ifeng.com/video.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
//            Project studio_item = new Project(1,"ADWS",imageUrl,videoUrl,0,0,"ACSS");
//            projectList.add(studio_item);
//            projectList.add(studio_item);
//            projectList.add(studio_item);
//            projectList.add(studio_item);
//        }
    }

    public void onCreate( ) {
        //  请求   子控件数据
        recyclerView = this.findViewById(R.id.projectRecyclerLayoutRecycler);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProjectAdapter(projectList);
        recyclerView.setAdapter(adapter);

        //  声明 下拉刷新框
        swipeRefresh = this.findViewById(R.id.projectRecyclerLayoutSwiperedresh);
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
                        ActivityUtil.scanForActivity(context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /**
                                 *
                                 * //  重新获取数据
                                 */
                                getDate(null);
                                Log.d(TAG, "runOnUiThread：run刷新中: ");
                                adapter.notifyDataSetChanged();///   通知刷新  studio_item
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
