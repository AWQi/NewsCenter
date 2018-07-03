package com.example.dell.newscenter.myview.mainactivity.mainpager.chase.recylerviewswper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        UniversalViewHolder holder1=holder;
        Glide.with(context).load(mData.get(position).getImageUrl())
                .placeholder(R.drawable.loading)
                .fitCenter()
                .into(holder1.recy_item_im);
//        holder1.recy_item_im.setBackgroundResource(mData.get(position).resoutimage);
        holder1.recy_item_tv.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class UniversalViewHolder extends RecyclerView.ViewHolder {
        public TextView recy_item_tv;
        public ImageView recy_item_im;
        public UniversalViewHolder(View itemView) {
            super(itemView);
            recy_item_im=itemView.findViewById(R.id.recy_item_im);
            recy_item_tv=itemView.findViewById(R.id.recy_item_tv);
        }
    }
}