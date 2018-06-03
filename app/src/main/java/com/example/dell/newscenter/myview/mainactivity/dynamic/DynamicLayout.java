package com.example.dell.newscenter.myview.mainactivity.dynamic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.dell.newscenter.R;

public class DynamicLayout extends LinearLayout{

    public DynamicLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.dynamic_layout,this);
    }
}
