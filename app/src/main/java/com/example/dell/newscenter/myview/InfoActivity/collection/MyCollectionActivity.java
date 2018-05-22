package com.example.dell.newscenter.myview.InfoActivity.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.project.ProjectRecyclerViewLayout;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity{
    private static final String TAG = "LiveFragment";
    private List<Project> projectList = new ArrayList<>();
    public void getDate() {
        String imageUrl = "http://img2.woyaogexing.com/2018/05/22/0b4a2b81c09ea5a2!400x400_big.jpg";
        String videoUrl ="http://ips.ifeng.com/video.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
        Project project = new Project(1,"ADWS",imageUrl,videoUrl,0,0,"ACSS");
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
    }
    private ProjectRecyclerViewLayout  myCollectionRV =null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        myCollectionRV = findViewById(R.id.myCollectionRV);
            getDate();
        myCollectionRV.getDate(projectList);




//        //  请求   子控件数据
//        projectList.clear();
//        getDate();
//        RecyclerView recyclerView = MyCollectionActivity.this.findViewById(R.id.myCollectionRecycler);
//        GridLayoutManager layoutManager = new GridLayoutManager(MyCollectionActivity.this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new ProjectAdapter(projectList);
//        recyclerView.setAdapter(adapter);
//        //  声明 下拉刷新框
//        swipeRefresh = MyCollectionActivity.this.findViewById(R.id.myCollectionSwiperedresh);
//        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            /**
//             *
//             *   重新获取数据  进行展示
//             */
//            @Override
//            public void onRefresh() {
//                Log.d(TAG, "onRefresh: 下拉刷新中");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MyCollectionActivity.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                /**
//                                 *
//                                 * //  重新获取数据
//                                 */
//                                getDate();
//                                Log.d(TAG, "runOnUiThread：run刷新中: ");
//                                adapter.notifyDataSetChanged();///   通知刷新  project
//                                swipeRefresh.setRefreshing(false);
//                            }
//                        });
//                    }
//                }).start();
//            }
//        });

    }
}
