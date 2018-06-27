package com.example.dell.newscenter.myview.videoplayactivity.videofragment.comment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Comment;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;

import java.util.List;

public class CommenFragment extends Fragment {
private EditText addCommentET = null;
private Button addCommentBtn= null;
private  Context context ;
private Activity activity;
private CommentRecyclerView commentRV = null;
private Project project = null;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  请求   子控件数据
        //    加载fragment
        View rootView  = inflater.inflate(R.layout.commentfragment,container,false);
        context = getContext();
        activity = getActivity();
        project = getProject();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addCommentBtn= getView().findViewById(R.id.addCommentBtn);
        addCommentET = getView().findViewById(R.id.addCommentET);
        commentRV = getView().findViewById(R.id.commentRV);
        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user =  ApplicationUtil.getUser();
                final int userId =user.getId();
                final String userHeadUrl = user.getHeadUrl();
                final String userName = user.getName();
                final int dynammicId = project.getId();
                final String content = addCommentET.getText().toString();
                final Comment comment = new Comment(userId,content,dynammicId);
                JoyHttpUtil.addComment(comment, new JoyHttpUtil.JoyObjCallBack() {
                    @Override
                    public void analyticData(final JoyResult.JoyObj joyObj) {

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (joyObj.getStatus()==200){

                                        //  id  直接强转 会得到  double
                                        int id= new Double((double) joyObj.getData()).intValue();
                                        comment.setId(id);  //  获取返回的评论id

                                        comment.setAuthorImageUrl(userHeadUrl);
                                        comment.setAuthorName(userName);
                                        CommentRecyclerView.MyAdapter myAdapter = commentRV.getMyAdapter();
                                        List<Comment>  list = commentRV.getCommentList();
                                        list.add(comment);
                                        myAdapter.notifyItemInserted(list.size());
                                        addCommentET.setText("");
                                        Toast.makeText(context,"评论成功",Toast.LENGTH_SHORT).show();
                                    }else {
                                    Toast.makeText(context,"评论失败",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    }
                });
            }
        });

    }

    public Project getProject(){
       return activity.getIntent().getParcelableExtra("live_item");
    }

}
