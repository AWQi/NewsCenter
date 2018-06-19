package com.example.dell.newscenter.myview.videoplayactivity.videofragment.introdution.relevantrecommendation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class RelevantreCommendationsLayout extends LinearLayout{
private Context context = null;
private List<Project> projectList = new ArrayList<>();
private ListView listView = null;
private MyAdapter myAdapter = null;
    public RelevantreCommendationsLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.relevant_recommendations_layout,this);// 获取layout
        listView = findViewById(R.id.relevantrecommendations_lv);
        initProject();
        myAdapter = new MyAdapter(context,R.layout.relevant_recommendations_item,projectList);// 获取item
        listView.setAdapter(myAdapter);
        //  设置点击监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Project project = projectList.get(i);
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("live_item",project);
                ActivityUtil.scanForActivity(context).startActivity(intent);
            }
        });
    }

    private void initProject() {
        /**
         *
         *   获取数据
         */
        String imageURL = "https://i04picsos.sogoucdn.com/3c28af542f2d49f7-fe9c78d2ff4ac332-12634f97e4a417cb6125ff8173671d0b_qq";
        String videoURL = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
        Project project = new Project(1,"AA",imageURL,videoURL,0,0,"BB");
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);




    }

    public  class MyAdapter extends ArrayAdapter {
        private int resourceId;

        public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Project project = (Project) getItem(position);
            //  false  表示只让我们在父布局中声明 的layout属性生效，但不会为这个view添加父布局，
            // 因为一旦有父布局  view 就不能再添加到ListView中了
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ImageView projectImage = view.findViewById(R.id.relevantrecommendationsImageIV);
            TextView projectTitle = view.findViewById(R.id.relevantrecommendationsTitleTV);
            ActivityUtil.loadNetImage(context,project.getImageURL(),projectImage);
            projectTitle.setText(project.getTitle());
            return view;

        }
    }

}