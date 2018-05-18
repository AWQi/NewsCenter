package com.example.dell.newscenter.myview.videoplayactivity;

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
import com.example.dell.newscenter.myview.fragment.CommenFragment;
import com.example.dell.newscenter.myview.fragment.IntroductionFragment;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class VideoFragmentLayout extends LinearLayout{
    private AppCompatActivity context;
    private TabLayout tabLayout  = null;//  上部放置 tablayout
    private ViewPager viewPager = null;// 下部放置  viewPager
    private Fragment[] fragments = new Fragment[2];
    private String titles[] = {"简介", "评论"};
    private ArrayList<TabLayout.Tab> tabs = new ArrayList<>();
    private VideoFragmentLayout.MyFragmentAdapter myFragmentAdapter ;

    public VideoFragmentLayout(Context context) {
        super(context);
    }

    public VideoFragmentLayout(Context context, @Nullable AttributeSet attrs) {
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
        fragments[0] = new IntroductionFragment();
        fragments[1] = new CommenFragment();
        Log.d(TAG, "context上下文: "+context);
        //  使用适配器将ViewPager与Fragment绑定在一起
        myFragmentAdapter = new VideoFragmentLayout.MyFragmentAdapter(context.getSupportFragmentManager());
        viewPager.setAdapter(myFragmentAdapter);
        //将TabLayout 与viewPager绑定在一起
        tabLayout.setupWithViewPager(viewPager);
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
