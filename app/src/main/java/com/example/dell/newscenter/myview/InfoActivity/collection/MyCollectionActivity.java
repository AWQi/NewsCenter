package com.example.dell.newscenter.myview.InfoActivity.collection;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.project.ProjectRecyclerViewLayout;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity {
    private static final String TAG = "LiveFragment";
    private List<Project> projectList = new ArrayList<>();
    private Activity activity;

    public void getDate() {
//        String imageUrl = "http://img2.woyaogexing.com/2018/05/22/0b4a2b81c09ea5a2!400x400_big.jpg";
//        String videoUrl ="http://ips.ifeng.com/video.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
//        Project project = new Project(10,"ADWS",imageUrl,videoUrl,0,0,"ACSS");
//        projectList.add(project);
//        projectList.add(project);
//        projectList.add(project);
//        projectList.add(project);


        JoyHttpUtil.queryDynamicCollect(ApplicationUtil.getUser().getId(), new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.PROJECT_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List list = joyList.getData();
                        myCollectionRV.getDate(list);
                        myCollectionRV.getAdapter().notifyDataSetChanged();
                    }
                });


            }
        });
    }

    private ProjectRecyclerViewLayout myCollectionRV = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        activity = this;
        myCollectionRV = findViewById(R.id.myCollectionRV);
        getDate();
        myCollectionRV.getDate(projectList);


    }
}
