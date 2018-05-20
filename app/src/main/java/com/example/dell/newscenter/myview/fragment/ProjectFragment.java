package com.example.dell.newscenter.myview.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.bean.Project;

public class ProjectFragment extends Fragment{
    private ImageView icon = null;
    private TextView title = null;
    private TextView kind = null;
    private Project project;
    @SuppressLint("ValidFragment")
    public  ProjectFragment(){}
    @SuppressLint("ValidFragment")
    public ProjectFragment( Project project){
        this.project = project;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.project,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        icon = getActivity().findViewById(R.id.project_icon);
        title = getActivity().findViewById(R.id.project_title);
        kind = getActivity().findViewById(R.id.project_kind);

        Glide.with(getContext()).load(project.getImageURL()).into(icon);
        title.setText(project.getTitle());
        kind.setText(project.getKind());

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  activity  跳转
                Intent intent = new Intent(getContext(), VideoPlayActivity.class);
                intent.putExtra("project",project);
                getContext().startActivity(intent);

            }
        });

    }
}
