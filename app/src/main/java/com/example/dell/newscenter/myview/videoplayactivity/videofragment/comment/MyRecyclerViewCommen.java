package com.example.dell.newscenter.myview.videoplayactivity.videofragment.comment;

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
import com.example.dell.newscenter.bean.Comment;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewCommen extends RecyclerView {
    private Context context;
    private List<Comment> commentList = new ArrayList<>();

    public MyRecyclerViewCommen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initCommen();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        //消除抖动
        this.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(commentList);
        this.setAdapter(myAdapter);
    }

    private void initCommen() {
        /**
         *
         *   获取数据
         */
        String imageURL = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2552174933,2157022207&fm=58&bpow=950&bpoh=1425";
        String content = "asaAasasqewwwde";

        Comment comment = new Comment(imageURL,content);
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);
        commentList.add(comment);




    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Comment> commentList;
        public MyAdapter(List<Comment> commentList) {
            this.commentList = commentList;
        }
        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentitem, parent, false);
            MyAdapter.ViewHolder holder = new MyAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Comment comment = commentList.get(position);
            Glide.with(context).load(comment.getAuthorImageURL()).into(holder.commenAuthorImage);
            holder.commenContent.setText(comment.getContent());
        }
        @Override
        public int getItemCount() {
            return commentList.size();
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
