package com.example.dell.newscenter.myview.mainactivity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Part;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v7.widget.GridLayoutManager.*;

public class MyRecyclerViewPart extends RecyclerView {
    private static final String TAG = "MyRecyclerViewPart";
    private Context context;
    private List<Part> partList = new ArrayList<>();
    private int[] logo = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,
                                    R.drawable.d,R.drawable.e,R.drawable.f,
                                    R.drawable.g,R.drawable.h,R.drawable.i,
                                    R.drawable.j,R.drawable.k,R.drawable.l,};
    private String[] name = new String[]{
        "直播","番剧","动画","国创","音乐","舞蹈","游戏","科技","生活","时尚","娱乐","影视"};
    public MyRecyclerViewPart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPart();
        StaggeredGridLayoutManager.LayoutParams param = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.setFullSpan(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        //消除抖动
        this.setLayoutManager(layoutManager);

        MyRecyclerViewPart.MyAdapter myAdapter = new MyRecyclerViewPart.MyAdapter(partList);
        this.setAdapter(myAdapter);
    }

    private void initPart() {
        /**
         *
         *   获取数据
         */
        String content = "asaAasasqewwwde";
        for (int i = 0;i<12;i++){
            partList.add(new Part(1,logo[i],name[i]));
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyRecyclerViewPart.MyAdapter.ViewHolder> {
        private List<Part>partList;
        public MyAdapter(List<Part> partList) {
            this.partList = partList;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partitem, parent, false);

//            //设置大小随机
//            Random random = new  Random();
//            int MIN = 200;int MAX = 400;
//            int size  =  (int)(Math.random()*(MAX-MIN)+MIN);
//            view.setLayoutParams(new LayoutParams(size,size));
            view.setForegroundGravity(TEXT_ALIGNMENT_CENTER);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewPart.MyAdapter.ViewHolder holder, final int position) {
            final Part part = partList.get(position);
            Glide.with(context).load(part.getImageId()).into(holder.PartImage);
            holder.PartContent.setText(part.getName());
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public int getItemCount() {
            return partList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView PartImage;
            TextView PartContent;

            public ViewHolder(View itemView) {
                super(itemView);
                PartImage = itemView.findViewById(R.id.part_iv);
                PartContent = itemView.findViewById(R.id.part_tv);
            }
        }
    }


}
