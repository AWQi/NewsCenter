package com.example.dell.newscenter.myview.mainactivity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.dell.newscenter.R;

public class ProjectLayout extends LinearLayout{

  public ProjectLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.project,this);
    }

}
