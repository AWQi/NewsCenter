package com.example.dell.newscenter.myview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.activity.VideoPlayActivity;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class MyListView extends ListView {
    private Context context = null;
    private List<Project> projectList = new ArrayList<>();
    private MyAdapter myAdapter = null;

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getDate();
        initListView();
    }
    protected void getDate(){
        /**
         *
         *   获取数据
         */
        String imageURL = "http://f.hiphotos.baidu.com/image/pic/item/35a85edf8db1cb13f423dfa0d154564e92584b3f.jpg";
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
    protected void initListView() {
        myAdapter = new MyAdapter(context, R.layout.relevantrecommendationsitem, projectList);
        this.setAdapter(myAdapter);
        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Project project = projectList.get(i);
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("project", project);
                ActivityUtil.scanForActivity(context).startActivity(intent);
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public class MyAdapter extends ArrayAdapter {
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
            ImageView projectImage = view.findViewById(R.id.relevantrecommendations_iv);
            TextView projectTitle = view.findViewById(R.id.relevantrecommendations_tv);
            Glide.with(context).load(project.getImageURL()).into(projectImage);// 图片加载 会对图片进行压缩，不会有内存溢出
            projectTitle.setText(project.getTitle());
            return view;

        }
    }
}
