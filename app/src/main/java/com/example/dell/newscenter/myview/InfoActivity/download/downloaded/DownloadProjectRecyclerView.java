package com.example.dell.newscenter.myview.InfoActivity.download.downloaded;

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
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownloadProject;
import com.example.dell.newscenter.myview.base.project.ProjectAdapter;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class DownloadProjectRecyclerView extends LinearLayout {

    public DownloadProjectRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}