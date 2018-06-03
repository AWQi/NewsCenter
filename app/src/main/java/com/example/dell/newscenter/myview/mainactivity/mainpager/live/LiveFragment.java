package com.example.dell.newscenter.myview.mainactivity.mainpager.live;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Studio;
import com.example.dell.newscenter.myview.base.project.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {
    private static final String TAG = "LiveFragment";
    private List<Studio> studioList = new ArrayList<>();
    private StudioAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    /**
     * 创建 fragment  控件
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    加载fragment
        View rootView = inflater.inflate(R.layout.live_fragment, container, false);
        return rootView;
    }

    public void getDate() {
        String imageUrl = "http://b.hiphotos.baidu.com/image/pic/item/eaf81a4c510fd9f9bebab92f292dd42a2934a4c3.jpg";
        String cctv1 = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
        String cctv2 = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
        String cctv5 = "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8";
        String cctv6 = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
        String aa = "rtmp://140.143.16.51/hls/aa";
        Studio s1 = new Studio(1,imageUrl,cctv1,"cctv1","电视");
        Studio s2 = new Studio(2,imageUrl,cctv2,"cctv3","电视");
        Studio s3 = new Studio(3,imageUrl,cctv5,"cctv5","电视");
        Studio s4 = new Studio(4,imageUrl,cctv6,"cctv6","电视");
        Studio s5 = new Studio(4,imageUrl,aa,"直播","电视");

        studioList.add(s1);
        studioList.add(s2);
        studioList.add(s3);
        studioList.add(s4);
        studioList.add(s5);

    }

    /**
     * 动态改变  fragment  向layout添加 fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  请求   子控件数据
        studioList.clear();
        getDate();
        RecyclerView recyclerView = getActivity().findViewById(R.id.livefragment_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StudioAdapter(getContext(),getActivity(),studioList);
        recyclerView.setAdapter(adapter);
        //  声明 下拉刷新框
        swipeRefresh = getActivity().findViewById(R.id.livefragment_swiperedresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             *
             *   重新获取数据  进行展示
             */
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: 下拉刷新中");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /**
                                 *
                                 * //  重新获取数据
                                 */
                                getDate();
                                Log.d(TAG, "runOnUiThread：run刷新中: ");
                                adapter.notifyDataSetChanged();///   通知刷新  studio_item
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });

    }


}
