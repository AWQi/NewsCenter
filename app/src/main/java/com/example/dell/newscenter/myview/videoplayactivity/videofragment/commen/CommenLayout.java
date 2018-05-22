package com.example.dell.newscenter.myview.videoplayactivity.videofragment.commen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Commen;

import java.util.ArrayList;
import java.util.List;

public class CommenLayout extends LinearLayout{

        private Context context = null;
        private List<Commen> commenList = new ArrayList<>();
        private ListView listView = null;
        private MyAdapter myAdapter = null;
        public CommenLayout(final Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            this.context = context;
            LayoutInflater.from(context).inflate(R.layout.commenlayout,this);// 获取layout
            listView = findViewById(R.id.commen_lv);
            initProject();
            myAdapter = new MyAdapter(context,R.layout.commenitem,commenList);// 获取item
            listView.setAdapter(myAdapter);
            //  设置点击监听器
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Commen commen =commenList.get(i);
//                    Intent intent = new Intent(context, VideoPlayActivity.class);
//                    intent.putExtra("common",commen);
//                    ActivityUtil.scanForActivity(context).startActivity(intent);
//                }
//            });
        }

        private void initProject() {
            /**
             *
             *   获取数据
             *         根据  project 查询  commen
             */
            String imageURL = "http://f.hiphotos.baidu.com/image/pic/item/35a85edf8db1cb13f423dfa0d154564e92584b3f.jpg";
            String videoURL = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
            Commen commen = new Commen();
            commenList.add(commen);
            commenList.add(commen);
            commenList.add(commen);
            commenList.add(commen);
            commenList.add(commen);
            commenList.add(commen);
            commenList.add(commen);


            commenList.add(commen);

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
                Commen commen = (Commen) getItem(position);
                //  false  表示只让我们在父布局中声明 的layout属性生效，但不会为这个view添加父布局，
                // 因为一旦有父布局  view 就不能再添加到ListView中了
                View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//                ImageView projectImage = view.findViewById(R.id.relevantrecommendations_iv);
//                TextView projectTitle = view.findViewById(R.id.relevantrecommendations_tv);
//                Glide.with(context).load(commen.getImageURL()).into(projectImage);// 图片加载 会对图片进行压缩，不会有内存溢出
//                projectTitle.setText(commen.getTitle());
                return view;

            }
        }
    }

