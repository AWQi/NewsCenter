package com.example.dell.newscenter.myview.videoplayactivity.videofragment.introdution.relevantrecom;

import android.app.Activity;
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
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;

import java.util.ArrayList;
import java.util.List;

public class RelevantRecomRecyclerView extends RecyclerView{
    private  Context context;
    private  List<Project> projectList = new ArrayList<>();
    private  MyAdapter myAdapter;
    public RelevantRecomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getDate(getKind());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        //消除抖动
        this.setLayoutManager(layoutManager);

         myAdapter = new MyAdapter(projectList);
        this.setAdapter(myAdapter);
    }
    private  String getKind(){
        return  ((VideoPlayActivity)context).getProject().getKind();
    }
    private void getDate(String kind ) {
        /**
         *
         *   获取数据
         */
//        String imageURL1 = "https://i04picsos.sogoucdn.com/3c28af542f2d49f7-fe9c78d2ff4ac332-12634f97e4a417cb6125ff8173671d0b_qq";
//        String imageURL2 = "http://img2.woyaogexing.com/2018/05/22/57be9001e13d4eca!400x400_big.jpg";
//        String videoURL = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
//        Project project1 = new Project(1,"AA",imageURL1,videoURL,0,0,"BB");
//        projectList.add(project1);
//
//        Project project2 = new Project(2,"AA",imageURL2,videoURL,0,0,"BB");
//
//        projectList.add(project2);

        JoyHttpUtil.RelevantRecom(kind, new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.PROJECT_LIST_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
//               Log.d(TAG, "analyticData: "+joyResult.getData());
                ((Activity)context).runOnUiThread(new Runnable() { //  开UI 线程
                    @Override
                    public void run() {
                        List list = joyList.getData();
                        projectList.addAll(list);
                        myAdapter.notifyDataSetChanged();//  刷新
                    }
                });
            }
        });

    }
  class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Project> projectList;

      public MyAdapter(List<Project> projectList) {
          this.projectList = projectList;
      }

      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relevant_recom_item,parent,false);
          ViewHolder holder = new ViewHolder(view);
          return holder;
      }

      @Override
      public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            Project project = projectList.get(position);
          Glide.with(context).load(project.getImageURL()).into(holder.projectImageTV);
          holder.projectTitleTV.setText(project.getTitle());
          holder.itemView.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                  Project project = projectList.get(holder.getAdapterPosition());
                  Intent intent = new Intent(context, VideoPlayActivity.class);
                  intent.putExtra("project_item",project);
                  ActivityUtil.scanForActivity(context).startActivity(intent);
              }
          });
      }
      @Override
      public int getItemCount() {
          return projectList.size();
      }

      public class ViewHolder extends RecyclerView.ViewHolder {
          ImageView projectImageTV;
          TextView projectTitleTV;
          public ViewHolder(View itemView) {
              super(itemView);
              projectImageTV = itemView.findViewById(R.id.relevantRecomImageIV);
              projectTitleTV  = itemView.findViewById(R.id.relevantRecomTitleTV);
          }
      }
  }



}
