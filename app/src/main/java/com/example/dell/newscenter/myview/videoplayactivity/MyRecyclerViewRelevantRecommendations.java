package com.example.dell.newscenter.myview.videoplayactivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewRelevantRecommendations extends RecyclerView{
    private  Context context;
    private  List<Project> projectList = new ArrayList<>();
    public MyRecyclerViewRelevantRecommendations(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initProject();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        //消除抖动
        this.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(projectList);
        this.setAdapter(myAdapter);
    }
    private void initProject() {
        /**
         *
         *   获取数据
         */
        String imageURL = "http://f.hiphotos.baidu.com/image/pic/item/35a85edf8db1cb13f423dfa0d154564e92584b3f.jpg";
        String videoURL = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
        Project project = new Project(1,"AA",imageURL,videoURL,0,0,"BB");
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
    }
  class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Project> projectList;

      public MyAdapter(List<Project> projectList) {
          this.projectList = projectList;
      }

      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relevantrecommendationsitem,parent,false);
          ViewHolder holder = new ViewHolder(view);
          return holder;
      }

      @Override
      public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            Project project = projectList.get(position);
          Glide.with(context).load(project.getImageURL()).into(holder.projectImage);
          holder.projectText.setText(project.getTitle());
          holder.itemView.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                  Project project = projectList.get(holder.getAdapterPosition());
                  Intent intent = new Intent(context, VideoPlayActivity.class);
                  intent.putExtra("project",project);
                  ActivityUtil.scanForActivity(context).startActivity(intent);
              }
          });
      }
      @Override
      public int getItemCount() {
          return projectList.size();
      }

      public class ViewHolder extends RecyclerView.ViewHolder {
          ImageView projectImage;
          TextView projectText;
          public ViewHolder(View itemView) {
              super(itemView);
              projectImage = itemView.findViewById(R.id.relevantrecommendations_iv);
              projectText  = itemView.findViewById(R.id.relevantrecommendations_tv);
          }
      }
  }



}
