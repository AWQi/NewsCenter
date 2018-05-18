package com.example.dell.newscenter.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;

import java.util.List;

public class RelevantreCommendationsAdapter extends ArrayAdapter{
    private  int resourceId;
    private  Context context;
    public RelevantreCommendationsAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context  =context;
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Project project = (Project) getItem(position);
        //  false  表示只让我们在父布局中声明 的layout属性生效，但不会为这个view添加父布局，
        // 因为一旦有父布局  view 就不能再添加到ListView中了
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView projectImage = view.findViewById(R.id.relevantrecommendations_iv);
        TextView projectTitle = view.findViewById(R.id.relevantrecommendations_tv);
        Glide.with(context).load(project.getImageURL()).into(projectImage);
        projectTitle.setText(project.getTitle());
        return  view;
    }
}
