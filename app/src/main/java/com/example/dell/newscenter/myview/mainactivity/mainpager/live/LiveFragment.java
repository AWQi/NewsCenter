package com.example.dell.newscenter.myview.mainactivity.mainpager.live;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Studio;
import com.example.dell.newscenter.myview.base.project.ProjectAdapter;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;

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
//        String c1 = "http://pic167.nipic.com/pic/20180605/22301941_005737873088_4.jpg";
//        String c2 = "http://pic167.nipic.com/pic/20180605/22301941_005746238082_4.jpg";
//        String c3 = "http://pic167.nipic.com/pic/20180605/22301941_010042807088_4.jpg";
//        String c5 = "http://pic167.nipic.com/pic/20180605/22301941_010055143081_4.jpg";
//        String c6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529583399383&di=5b51fe2919acf9854dd10de48d4b305f&imgtype=0&src=http%3A%2F%2Fwww.haoqu.net%2Fuploadfile%2F2015%2F0417%2F20150417121648885.jpg";
//
//        String imageUrl = "https://pic.qqtn.com/up/2018-6/15293746617306806.jpg";
//        String cctv1 = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
//        String cctv3 = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
//        String cctv5 = "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8";
//        String cctv6 = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
//        String aa = "rtmp://140.143.16.51/hls/aa";
//        Studio s1 = new Studio(1,c1,cctv1,"cctv1","电视");
//        Studio s2 = new Studio(2,c3,cctv3,"cctv3","电视");
//        Studio s3 = new Studio(3,c5,cctv5,"cctv5","电视");
//        Studio s4 = new Studio(4,c6,cctv6,"cctv6","电视");
//        Studio s5 = new Studio(4,imageUrl,aa,"直播","电视");
//        studioList.add(s1);
//        studioList.add(s2);
//        studioList.add(s3);
//        studioList.add(s4);
//        studioList.add(s5);
        JoyHttpUtil.queryStudio(new JoyHttpUtil.JoyListCallBack(JoyHttpUtil.STUDIO_LIST_TYPE) {
            @Override
            public void analyticData(final JoyResult.JoyList joyList) {
                //               Log.d(TAG, "analyticData: "+joyResult.getData());
                getActivity().runOnUiThread(new Runnable() { //  开UI 线程
                    @Override
                    public void run() {
                        List list = joyList.getData();

                        studioList.addAll(list);
                        adapter.notifyDataSetChanged();//  刷新
                    }
                });
            }
        });


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
        StaggeredGridLayoutManager.LayoutParams param = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.setFullSpan(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        param.setFullSpan(true);
        RecyclerView recyclerView = getActivity().findViewById(R.id.livefragment_recycler);
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
                                studioList.clear();
                                getDate();
                                Log.d(TAG, "runOnUiThread：run刷新中: ");
                                adapter.notifyDataSetChanged();///   通知刷新  live_item
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });

    }


}
