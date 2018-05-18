package com.example.dell.news;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProjectLayout extends LinearLayout{
 private ImageView icon = null;
 private TextView  title = null;
 private TextView kind = null;

    public ProjectLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.project,this);
        icon = findViewById(R.id.project_icon);
        title = findViewById(R.id.project_title);
        kind = findViewById(R.id.project_kind);
    }

}
