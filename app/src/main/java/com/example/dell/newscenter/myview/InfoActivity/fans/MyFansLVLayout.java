package com.example.dell.newscenter.myview.InfoActivity.fans;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class MyFansLVLayout extends ListView {
    private static final String TAG = "MyFansLVLayout";
    private List<User> userList = new ArrayList();
    private  MyAdapter myAdapter ;
    private  Context context;
    public MyFansLVLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getDate();
        myAdapter = new MyAdapter(context,R.layout.myfans_item,userList);
        this.setAdapter(myAdapter);
    }
    private  void getDate(){
        User user = new User("AWQI","https://i01piccdn.sogoucdn.com/3c28af542f2d49f7-9e7c5d699eaea93e-e247d97701e0b8dc2dd8024d3adb26c0_qq");
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);


    }
    class  MyAdapter extends ArrayAdapter{
    private  int resourceId;
    private Context context;
        public MyAdapter(@NonNull Context context, int resourceId) {
            super(context, resourceId);
            this.context = context;
            this.resourceId = resourceId;
        }

        public MyAdapter(@NonNull Context context, int resource, @NonNull List objects ) {
            super(context, resource, objects);
            this.resourceId = resource;// 每一个项的视图ID
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            User user = (User) getItem(position);
            View view;
             ViewHolder viewHolder;
            if (convertView==null){
                view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.fansImage = view.findViewById(R.id.myFansItemIV);
                viewHolder.fansTextView= view.findViewById(R.id.myFansItemTV);
                view.setTag(viewHolder);  //将viewHilder 存储在  view中
            }else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag(); // 从view中取出
            }
            Log.d(TAG, "HeadUrl "+user.getHeadUrl());
            ActivityUtil.loadNetImage(context,user.getHeadUrl(),viewHolder.fansImage);
            viewHolder.fansTextView.setText(user.getName());
            return view;
        }

        class  ViewHolder{
            ImageView fansImage;
            TextView fansTextView;
        }
    }
}
