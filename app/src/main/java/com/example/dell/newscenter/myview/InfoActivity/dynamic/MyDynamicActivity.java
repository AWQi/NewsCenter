package com.example.dell.newscenter.myview.InfoActivity.dynamic;

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

public class MyDynamicActivity extends AppCompatActivity{
    private static final String TAG = "MyDynamicActivity";
    private ProjectRecyclerViewLayout myDynameicCV;
    private List<Project> projectList = new ArrayList<>();
    private Activity activity = this;
    public void getDate() {
//        String imageUrl = "http://img2.woyaogexing.com/2018/05/19/a7bbc2eebe60b832!400x400_big.jpg";
//        String videoUrl ="http://ips.ifeng.com/video.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
//        Project project = new Project(1,"ADWS",imageUrl,videoUrl,0,0,"ACSS");
//        projectList.add(project);
//        projectList.add(project);
//        projectList.add(project);
//        projectList.add(project);
//         activity = this;
        JoyHttpUtil.queryMyDynamic(ApplicationUtil.getUser().getId(), new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.PROJECT_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List list = joyList.getData();
                        myDynameicCV.getDate(list);
                        Log.d(TAG, "projectList:------------------ "+projectList.size());
                        myDynameicCV.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydynamic);
        getDate();
        myDynameicCV = findViewById(R.id.myDynameicCV);
        myDynameicCV.getDate(projectList);
    }
}
