package com.example.dell.newscenter.myview.mainactivity.mainpager.chase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Muscovy;
import com.example.dell.newscenter.myview.mainactivity.mainpager.chase.recylerviewswper.CardConfig;
import com.example.dell.newscenter.myview.mainactivity.mainpager.chase.recylerviewswper.SwipeCardCallBack;
import com.example.dell.newscenter.myview.mainactivity.mainpager.chase.recylerviewswper.SwipeCardLayoutManager;
import com.example.dell.newscenter.myview.mainactivity.mainpager.chase.recylerviewswper.UniversalAdapter;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;


import java.util.ArrayList;
import java.util.List;


public class ChaseFragment extends Fragment{
    private RecyclerView mActivity_review;
    private UniversalAdapter mAdatper;
    private ArrayList<Muscovy> mList;
    private LinearLayout linearLayout = null;
    public  int page = 1;
    private TextView muscovyRefreshTV ;
    private  TextView muscovyMoreTV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        linearLayout = (LinearLayout) inflater.inflate(R.layout.chase_fragment,container,false);
     return linearLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setData();
    }

    private void initView() {
        mList = new ArrayList<>();
        mActivity_review =  linearLayout.findViewById(R.id.activity_review);

        muscovyRefreshTV=getActivity().findViewById(R.id.muscovyRefreshTV);
        muscovyMoreTV = getActivity().findViewById(R.id.muscovyMoreTV);

        muscovyRefreshTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  发送请求  刷新

            }
        });
        muscovyMoreTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  跳转

            }
        });
    }


    private void initData() {

        JoyHttpUtil.queryFiveMuscovy(page, new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.MUSCOVY_LIST_TYPE) {
            @Override
            public void analyticData(JoyResult.JoyList joyList) {
                List list = joyList.getData();
                mList.clear();
                mList.addAll(list);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdatper.notifyDataSetChanged();
                    }
                });


            }
        });
    }

    private void setData() {


        SwipeCardLayoutManager swmanamger = new SwipeCardLayoutManager(getContext());
        mActivity_review.setLayoutManager(swmanamger);
        mAdatper = new UniversalAdapter(mList, getContext());
        mActivity_review.setAdapter(mAdatper);
        CardConfig.initConfig(getContext());
        ItemTouchHelper.Callback callback=new SwipeCardCallBack(mList,mAdatper,mActivity_review);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mActivity_review);

    }


}
