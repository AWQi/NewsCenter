package com.example.dell.newscenter.myview.videoplayactivity.videofragment.comment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.newscenter.R;

public class CommenFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  请求   子控件数据
        //    加载fragment
        View rootView  = inflater.inflate(R.layout.commentfragment,container,false);
        return rootView;
    }

}
