package com.example.dell.newscenter.myview.InfoActivity.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.project.ProjectRecyclerViewLayout;

import java.util.List;

public class MyHistoryActivity extends AppCompatActivity{
    private static final String TAG = "HistoryActivity";
private List<Project> projectList = null;
private ProjectRecyclerViewLayout myHistoryCV = null;
    private void getDate(){
            projectList = HistoryUtil.getHistory(MyHistoryActivity.this);
//        Log.d(TAG, "projectList: "+projectList);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhistory);
        getDate();
        myHistoryCV = findViewById(R.id.myHistoryCV);
        myHistoryCV.getDate(projectList);
    }
}
