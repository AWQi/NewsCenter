package com.example.dell.news;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestLayout extends LinearLayout{
private TextView textView = null;
    public TestLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.test,this);
        textView = findViewById(R.id.test_textview);
    }
}
