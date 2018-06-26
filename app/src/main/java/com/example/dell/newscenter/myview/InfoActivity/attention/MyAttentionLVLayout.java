package com.example.dell.newscenter.myview.InfoActivity.attention;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;

import java.util.ArrayList;
import java.util.List;

public class MyAttentionLVLayout extends ListView{
    private static final String TAG = "MyAttentionLVLayout";
    private List<User> userList = new ArrayList<>();
    private MyAdapter myAdapter ;
    private  Context context;
    public MyAttentionLVLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        getDate();
        myAdapter = new MyAdapter(context,R.layout.myattention_item,userList);
        this.setAdapter(myAdapter);
    }
    private  void  getDate(){
//        final User user = new User("AWQI","https://i03piccdn.sogoucdn.com/3c28af542f2d49f7-fe9c78d2ff4ac332-fb4a758922eafb9eb4eb9f41b34581da_qq");
//        userList.add(user);
//        userList.add(user);
//        userList.add(user);
//        userList.add(user);
//        userList.add(user);
//        userList.add(user);
        JoyHttpUtil.myAttention(ApplicationUtil.getUser().getId(), new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.USER_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
                Log.d(TAG, "analyticData: ");
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List list = joyList.getData();
                        Log.d(TAG, "list.siez: ```````````````````"+list.size());
                        userList.addAll(list);
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }
    class  MyAdapter extends ArrayAdapter{
        private  Context context ;
        private  int resoureceId;
        public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, resource, objects);
            this.context = context;
            resoureceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            User user = (User) getItem(position);
            View view;
            ViewHolder viewHolder;
            if (convertView==null){
                view = LayoutInflater.from(context).inflate(resoureceId,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.myAttentionHeadCV = view.findViewById(R.id.myAttentionHeadCV);
                viewHolder.myAttentionTV = view.findViewById(R.id.myAttentionTV);
                viewHolder.myAttentionBtn = view.findViewById(R.id.myAttentionBtn);
                view.setTag(viewHolder);
            }else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            Glide.with(context).load(user.getHeadUrl())
                    .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                    .fitCenter().into(viewHolder.myAttentionHeadCV);
//            ActivityUtil.loadNetImage(context,user.getHeadUrl(),viewHolder.myAttentionHeadCV);
            viewHolder.myAttentionTV.setText(user.getName());
//            viewHolder.myAttentionBtn.setText();
            return view;

        }

        class  ViewHolder {
            CircleImageView myAttentionHeadCV;
            TextView myAttentionTV;
            Button myAttentionBtn;
        }
    }
}
