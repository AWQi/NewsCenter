package com.example.dell.newscenter.myview.videoplayactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Commen;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewCommen extends RecyclerView {
    private Context context;
    private List<Commen> commenList = new ArrayList<>();

    public MyRecyclerViewCommen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initCommen();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        //消除抖动
        this.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(commenList);
        this.setAdapter(myAdapter);
    }

    private void initCommen() {
        /**
         *
         *   获取数据
         */
        String imageURL = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2552174933,2157022207&fm=58&bpow=950&bpoh=1425";
        String content = "asaAasasqewwwde";

        Commen commen = new Commen(imageURL,content);
        commenList.add(commen);
        commenList.add(commen);
        commenList.add(commen);
        commenList.add(commen);
        commenList.add(commen);
        commenList.add(commen);
        commenList.add(commen);
        commenList.add(commen);




    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Commen>commenList;
        public MyAdapter(List<Commen> commenList) {
            this.commenList = commenList;
        }
        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commenitem, parent, false);
            MyAdapter.ViewHolder holder = new MyAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Commen commen = commenList.get(position);
            Glide.with(context).load(commen.getAuthorImageURL()).into(holder.commenAuthorImage);
            holder.commenContent.setText(commen.getContent());
        }
        @Override
        public int getItemCount() {
            return commenList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView commenAuthorImage;
            TextView commenContent;

            public ViewHolder(View itemView) {
                super(itemView);
                commenAuthorImage = itemView.findViewById(R.id.commen_user);
                commenContent = itemView.findViewById(R.id.commen_tv);
            }
        }
    }


}
