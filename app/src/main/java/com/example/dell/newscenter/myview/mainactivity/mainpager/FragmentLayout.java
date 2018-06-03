package com.example.dell.newscenter.myview.mainactivity.mainpager;



import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.myview.mainactivity.mainpager.live.LiveFragment;


import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FragmentLayout extends LinearLayout {
    private AppCompatActivity context;
    private TabLayout tabLayout  = null;//  上部放置 tablayout
    private ViewPager viewPager = null;// 下部放置  viewPager
    private Fragment[] fragments = {new Fragment(), new Fragment(), new Fragment()};
    private String titles[] = {"直播", "推荐", "追番"};
    private ArrayList<TabLayout.Tab> tabs = new ArrayList<>();
    private MyFragmentAdapter myFragmentAdapter ;

    public FragmentLayout(Context context) {
        super(context);
    }

    public FragmentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = (AppCompatActivity) context;
        LayoutInflater.from(context).inflate(R.layout.fragmentlayout, this);
        tabLayout = findViewById(R.id.fragment_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);// 设置选项卡均分填充tablayout
        viewPager = findViewById(R.id.fragment_viewpager);
        setParam();
    }

public void setParam() {
        //替换一个查看效果
        fragments[0] = new LiveFragment();
        fragments[1] = new RecommendFragment();
        fragments[2] = new ChaseFragment();
        Log.d(TAG, "context上下文: "+context);
        //  使用适配器将ViewPager与Fragment绑定在一起
        myFragmentAdapter = new MyFragmentAdapter(context.getSupportFragmentManager());
        viewPager.setAdapter(myFragmentAdapter);
        //将TabLayout 与viewPager绑定在一起
        tabLayout.setupWithViewPager(viewPager);
//        // 指定tab  的位置
//        int count = tabLayout.getTabCount();
//        Log.d(TAG, "count: " + count);
    }

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setTabs(ArrayList<TabLayout.Tab> tabs) {
        this.tabs = tabs;
    }

    public Fragment[] getFragments() {
        return fragments;
    }

    public String[] getTitles() {
        return titles;
    }

    public ArrayList<TabLayout.Tab> getTabs() {
        return tabs;
    }



    /**
     * 适配器
     */
    public class MyFragmentAdapter extends FragmentPagerAdapter {
        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }
        @Override
        public int getCount() {
            return titles.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}

