package com.example.dell.newscenter.myview.videoplayactivity.videofragment.comment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Comment;
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.HttpUtil;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;

import java.util.ArrayList;
import java.util.List;

public class CommentRecyclerView extends RecyclerView {
    private static final String TAG = "MyRecyclerViewCommen";
    private Context context;
    private List<Comment> commentList = new ArrayList<>();
    private MyAdapter myAdapter = null;
    public CommentRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getData(getProjectId());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //消除抖动
        this.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(commentList);
        this.setAdapter(myAdapter);
        this.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));// 添加默认分割线
    }
    private  int getProjectId(){
        return  ((VideoPlayActivity)context).getProject().getId();
    }
    private void getData(int projectId) {
        /**
         *
         *   获取数据
         */
//        String imageURL = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2552174933,2157022207&fm=58&bpow=950&bpoh=1425";
//        String content = "asaAasasqewwwde";
//
//        Comment comment = new Comment(imageURL,content);
//        commentList.add(comment);
//        commentList.add(comment);
//        commentList.add(comment);
//        commentList.add(comment);
//        commentList.add(comment);
//        commentList.add(comment);
//        commentList.add(comment);
//        commentList.add(comment);
        Log.d(TAG, "projectId: ````````````````````````````````"+projectId);
        JoyHttpUtil.queryComment(projectId, new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.COMMENT_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
                ((Activity)context).runOnUiThread(new Runnable() { //  开UI 线程
                    @Override
                    public void run() {
                        List list = joyList.getData();
                        commentList.addAll(list);
                        Log.d(TAG, "commentList   size"+list.size()+"run: -----------------------------------");
                        myAdapter.notifyDataSetChanged();//  刷新
                    }
                });
            }
        });


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
            final Comment comment = commentList.get(position);
            Glide.with(context).load(comment.getAuthorImageUrl()).into(holder.commenAuthorImage);
            holder.commenContent.setText(comment.getContent());

            if (comment.getUserId()==ApplicationUtil.getUser().getId()){
                holder.attentBtn.setText("删除评论");
                holder.attentBtn.setOnClickListener(new OnClickListener() { // 删除评论
                    @Override
                    public void onClick(View v) {
                        final Button view = (Button) v;
                        if (comment.getId()==0)
                            Log.d(TAG, "onClick:--------------------------- 删除请求 ");
                        JoyHttpUtil.deleteComment(comment.getId(), new JoyHttpUtil.JoyObjCallBack() {
                            @Override
                            public void analyticData(final JoyResult.JoyObj joyObj) {
                                ((Activity)context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (joyObj.getStatus()==200){
                                            int i;
                                            for (i = 0;i<commentList.size();i++){
                                                if (commentList.get(i).getId()==comment.getId()){
                                                    commentList.remove(i);
                                                    break;
                                                }
                                            }
                                           myAdapter.notifyItemRemoved(i);
                                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(context,"删除失败",Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                });
                            }
                        });
                    }
                });
            }else {
                holder.attentBtn.setOnClickListener(new OnClickListener() { // 关注
                    @Override
                    public void onClick(View v) {
                        final Button view = (Button) v;
                        if (view.getText().toString().equals(""))
                            JoyHttpUtil.addAttention(ApplicationUtil.getUser().getId(),comment.getUserId(), new JoyHttpUtil.JoyObjCallBack() {

                                @Override
                                public void analyticData(final JoyResult.JoyObj joyObj) {
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        // Toast   必须放在  ui线程  不然会报错
//                    java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()
                                        @Override
                                        public void run() {
                                            if (joyObj.status==200) {
                                                Toast.makeText(context, "关注成功", Toast.LENGTH_SHORT).show();
                                                view.setText("已关注");
                                                view.setClickable(false);
                                            }else {
                                                Toast.makeText(context, "关注失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                            });
                    }
                });
            }

        }
        @Override
        public int getItemCount() {
            return commentList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView commenAuthorImage;
            TextView commenContent;
            Button attentBtn;
            public ViewHolder(View itemView) {
                super(itemView);
                commenAuthorImage = itemView.findViewById(R.id.commen_user);
                commenContent = itemView.findViewById(R.id.commen_tv);
                attentBtn = itemView.findViewById(R.id.commen_attent);
            }
        }
    }

    public MyAdapter getMyAdapter() {
        return myAdapter;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
