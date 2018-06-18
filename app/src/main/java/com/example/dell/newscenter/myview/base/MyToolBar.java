package com.example.dell.newscenter.myview.base;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.dell.newscenter.R;

public class MyToolBar extends Toolbar{

    public MyToolBar(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLogo(R.drawable.return_icon);
        this.setBackground(context.getResources().getDrawable(R.color.colorAccent));
        this.getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
    }
}
