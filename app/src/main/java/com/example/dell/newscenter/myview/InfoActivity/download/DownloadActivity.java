package com.example.dell.newscenter.myview.InfoActivity.download;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownloadProject;
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownloadProjectDBUtil;
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends AppCompatActivity{
    private static final String TAG = "ProjectRecyclerViewLayout";
    private List<DownloadProject> projectList = new ArrayList<>();
//    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private Context context = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
//        getDate();
//        //  请求   子控件数据
//        recyclerView = this.findViewById(R.id.downloadRV);
//        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new MyAdapter(projectList);
//        recyclerView.setAdapter(adapter);
    }

//    /**
//     *   从数据库获取  已下载文件信息
//     */
//    public void getDate() {
//        projectList = DownloadProjectDBUtil.queryDownloadProjectED();
//    }
//
//    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
//        private Context context;
//        private List<DownloadProject> projectList;
//
//        public MyAdapter(List<DownloadProject> projectList) {
//            this.projectList = projectList;
//        }
//
//        @NonNull
//        @Override
//        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            if (context==null){
//                context = parent.getContext();
//            }
//            View view = LayoutInflater.from(context).inflate(R.layout.downloaded_project_item,parent,false);
//            return_icon new MyAdapter.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
//            final DownloadProject  downloadProject = projectList.get(position);
//            final Project studio_item = downloadProject.getObjProject();
//            Glide.with(context).load(studio_item.getImageURL())
//                    .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
//                    .fitCenter()
//                    .into(holder.downloadProjectItemImageIV);
//
//            holder.downloadProjectItemTitleTV.setText(studio_item.getTitle());
//            holder.downloadProjectItemAuthorTV.setText(studio_item.getAuthorName());
////            holder.downloadProjectItemSizeTV.setText(downloadProject.get);
//            holder.downloadProjectItemDetailsTV.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, VideoPlayActivity.class);
//                    intent.putExtra("studio_item",studio_item);
//                    ActivityUtil.scanForActivity(context).startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return_icon projectList.size();
//        }
//
//        class  ViewHolder extends  RecyclerView.ViewHolder{
//            View view;
//            ImageView downloadProjectItemImageIV;
//            TextView downloadProjectItemTitleTV;
//            TextView downloadProjectItemAuthorTV;
//            TextView downloadProjectItemSizeTV;
//            TextView downloadProjectItemDetailsTV;
//            public ViewHolder(View view) {
//                super(view);
//                this.view = view;
//                downloadProjectItemImageIV = view.findViewById(R.id.downloadProjectItemImageIV);
//                downloadProjectItemTitleTV = view.findViewById(R.id.downloadProjectItemTitleTV);
//                downloadProjectItemAuthorTV = view.findViewById(R.id.downloadProjectItemAuthorTV);
//                downloadProjectItemSizeTV = view.findViewById(R.id.downloadProjectItemSizeTV);
//                downloadProjectItemDetailsTV = view.findViewById(R.id.downloadProjectItemDetailsTV);
//            }
//        }
//
//    }

}
