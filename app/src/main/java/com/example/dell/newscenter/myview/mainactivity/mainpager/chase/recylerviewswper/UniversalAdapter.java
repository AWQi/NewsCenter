package com.example.dell.newscenter.myview.mainactivity.mainpager.chase.recylerviewswper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.myview.mainactivity.mainpager.chase.MuscovyPlayActivity;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Muscovy;

import java.util.ArrayList;

/**
 */

public class UniversalAdapter extends RecyclerView.Adapter<UniversalAdapter.UniversalViewHolder> {
    public ArrayList<Muscovy> mData;
    public Context context;

    public Context getContext() {
        return context;
    }

    public UniversalAdapter(ArrayList<Muscovy> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recylerview_item, null);
        UniversalViewHolder holder = new UniversalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder holder, int position) {

        final Muscovy muscovy = mData.get(position);
        UniversalViewHolder holder1=holder;
        Glide.with(context).load(muscovy.getImageUrl())
                .placeholder(R.drawable.loading)
                .fitCenter()
                .into(holder1.recy_item_im);
//        holder1.recy_item_im.setBackgroundResource(mData.get(position).resoutimage);
        holder1.recy_item_tv.setText(muscovy.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MuscovyPlayActivity.class);
                intent.putExtra("muscovy",muscovy);
                ((Activity)context).startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class UniversalViewHolder extends RecyclerView.ViewHolder {
        public TextView recy_item_tv;
        public ImageView recy_item_im;
        public  View view = null;
        public UniversalViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            recy_item_im=itemView.findViewById(R.id.recy_item_im);
            recy_item_tv=itemView.findViewById(R.id.recy_item_tv);
        }
    }
}