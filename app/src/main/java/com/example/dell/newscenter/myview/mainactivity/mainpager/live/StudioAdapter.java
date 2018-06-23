package com.example.dell.newscenter.myview.mainactivity.mainpager.live;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Studio;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.List;

public class StudioAdapter extends RecyclerView.Adapter{
    private static final String TAG = "NewCenter";
    private Context context;
    private Activity activity;
    private List<Studio> studioList;

    public StudioAdapter(Context context , Activity activity, List<Studio> studioList) {
        this.context= context;
        this.activity = activity;
        this.studioList = studioList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.live_item,parent,false);
        StudioHolder holder= new StudioHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final Studio studio = studioList.get(position);
            StudioHolder h = (StudioHolder) holder;
            h.studioItemTitleTV.setText(studio.getTitle());
            h.studioItemKindTV.setText(studio.getKind());
        Glide.with(context).load(studio.getImageUrl())
                .fitCenter()
                .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                .into(h.studioItemImageIV);
        h.studioItemLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PullActivity.class);
                intent.putExtra("studio",studio);
                Log.d(TAG, "跳转: ");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studioList.size();
    }
    static class StudioHolder extends RecyclerView.ViewHolder{
        public  View view = null;
        public LinearLayout studioItemLL = null;
        public ImageView studioItemImageIV = null;
        public TextView studioItemTitleTV = null;
        public TextView studioItemKindTV = null;
        public StudioHolder(View itemView) {
            super(itemView);
            this.view = itemView;
             studioItemLL = (LinearLayout) itemView;
             studioItemImageIV = view.findViewById(R.id.studioItemImageIV);
             studioItemKindTV = view.findViewById(R.id.studioItemKindTV);
             studioItemTitleTV = view.findViewById(R.id.studioItemTitleTV);
        }
    }
}
