package com.example.dell.newscenter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>{
private Context context;
private List<Project> projectList;

    public ProjectAdapter(List<Project> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.cardviewproject,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project  project = projectList.get(position);
        holder.title.setText(project.getTitle());
        Glide.with(context).load(project.getImageURL()).into(holder.icon); // 图片加载 会对图片进行压缩，不会有内存溢出
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    static  class  ViewHolder extends  RecyclerView.ViewHolder{
       CardView cardView;
       ImageView icon;
       TextView title;
       TextView kind;
       public ViewHolder(View view) {
           super(view);
           cardView = (CardView) view;
           icon = view.findViewById(R.id.cardview_project_icon);
           title = view.findViewById(R.id.cardview_project_title);
           kind = view.findViewById(R.id.cardview_project_kind);
       }
   }

}
