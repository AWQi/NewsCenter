package com.example.dell.newscenter.myview.InfoActivity.download.downloading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.activity.MainActivity;
import com.example.dell.newscenter.myview.InfoActivity.download.DownloadActivity;
import com.example.dell.newscenter.myview.InfoActivity.download.DownloadProjectRVAdapter;

import java.util.List;


public class DownLoadProjectActivity extends AppCompatActivity {
    private RecyclerView downloadRV;
    private List<DownloadProject> downloadingList;
    private List<DownloadProject> downloadedList;
    private DownloadProjectRVAdapter adapter = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        getData();
        downloadRV = findViewById(R.id.downloadRV);
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(DownLoadProjectActivity.this);
        downloadRV.setLayoutManager(layoutManager);
        adapter = new DownloadProjectRVAdapter(DownLoadProjectActivity.this,downloadingList,downloadedList);
        downloadRV.setAdapter(adapter);
    }
    public void getData(){
        downloadedList = DownloadProjectDBUtil.queryDownloadProjectED();
        downloadingList = DownloadProjectDBUtil.queryDownloadProjectING();
    }
}
