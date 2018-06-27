package com.example.dell.newscenter.myview.mainactivity.dynamic;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Part;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.HttpUtil;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDynamic  extends RecyclerView {
    private static final String TAG = "RecyclerViewDynamic";
    private Context context;
    private List<Project> projectList = new ArrayList<>();
    public  RecyclerViewDynamic.MyAdapter myAdapter;
    public RecyclerViewDynamic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        this.setLayoutManager(layoutManager);
        myAdapter = new RecyclerViewDynamic.MyAdapter(projectList);
        this.setAdapter(myAdapter);
        getData(myAdapter);
        this.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));// 添加默认分割线
    }

    private void getData(final Adapter adapter) {
//        /**
//         *
//         *   获取数据
//         */
//            String imageUrl = "http://img2.woyaogexing.com/2018/05/19/a7bbc2eebe60b832!400x400_big.jpg";
//            String videoUrl ="http://ips.ifeng.com/video.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
//            String headUrl = ApplicationUtil.getUser().getHeadUrl();
//            Project project = new Project(1,"AASC",imageUrl,videoUrl,0,0,"ADS",1,"AWQI",headUrl);
//            projectList.add(project);
//            projectList.add(project);
//            projectList.add(project);
//            projectList.add(project);


       JoyHttpUtil.queryAttentDynamic(ApplicationUtil.getUser().getId(), new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.PROJECT_LIST_TYPE) {
           public void analyticData(final JoyResult.JoyList joyList) {
//               Log.d(TAG, "analyticData: "+joyResult.getData());
               ((Activity)context).runOnUiThread(new Runnable() { //  开UI 线程
                   @Override
                   public void run() {
                       List list = joyList.getData();
                       projectList.addAll(list);
                       adapter.notifyDataSetChanged();//  刷新
                   }
               });

           }
       });

    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerViewDynamic.MyAdapter.ViewHolder> {
        private List<Project>projectList;
        public MyAdapter(List<Project> projectList) {
            this.projectList=projectList;
        }
        @NonNull
        @Override
        public RecyclerViewDynamic.MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamicitem, parent, false);

//            view.setForegroundGravity(TEXT_ALIGNMENT_CENTER);
            RecyclerViewDynamic.MyAdapter.ViewHolder holder = new RecyclerViewDynamic.MyAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewDynamic.MyAdapter.ViewHolder holder, final int position) {
            final Project project = projectList.get(position);
            Glide.with(context).load(project.getAuthorHeadUrl())
                    .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                    .fitCenter()
                    .into( holder.dynamicItemAuthorHeadCV);
            holder.dynamicItemAuthorNameTV.setText(project.getAuthorName());
            Glide.with(context).load(project.getImageURL())
                    .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                    .fitCenter()
                    .into(  holder.dynamicItemImageIV);
            holder.dynamicItemTitleTV.setText(project.getTitle());
        }
        @Override
        public int getItemCount() {
            return projectList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            View itemView;
            CircleImageView dynamicItemAuthorHeadCV;
            TextView dynamicItemAuthorNameTV;
            ImageView dynamicItemImageIV;
            TextView dynamicItemTitleTV;
            ImageView dynamicItemShareIV;
            ImageView dynamicItemCommenIV;
            ImageView dynamicItemPraiseIV;
            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                dynamicItemAuthorHeadCV = itemView.findViewById(R.id.dynamicItemAuthorHeadCV);
                dynamicItemAuthorNameTV = itemView.findViewById(R.id.dynamicItemAuthorNameTV);
                dynamicItemImageIV = itemView.findViewById(R.id.dynamicItemImageIV);
                dynamicItemTitleTV = itemView.findViewById(R.id.dynamicItemTitleTV);
                dynamicItemShareIV = itemView.findViewById(R.id.dynamicItemShareIV);
                dynamicItemCommenIV = itemView.findViewById(R.id.dynamicItemCommenIV);
                dynamicItemPraiseIV = itemView.findViewById(R.id.dynamicItemPraiseIV);
            }
        }
    }


}

