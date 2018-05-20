package com.example.dell.newscenter.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.myview.mainactivity.DynamicLayout;
import com.example.dell.newscenter.myview.mainactivity.FragmentLayout;
import com.example.dell.newscenter.myview.mainactivity.PartitionsLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    /*  顶部的 布局*/
    private CircleImageView main_head_portrait = null;
    private  Toolbar toolbar = null;

    /*  中央  的  布局*/
    private FrameLayout main_center_layout = null;
    private FragmentLayout fragmentLayout = null;
    private PartitionsLayout partitionsLayout = null;
    private DynamicLayout dynamicLayout = null;

    /*  底部的  布局*/
    private BottomNavigationView main_bottom = null;
    private DrawerLayout drawer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /* 顶部的布局*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        main_head_portrait = findViewById(R.id.main_head_portrait);

        /* 中央的布局*/
        main_center_layout = findViewById(R.id.center_LinearLayout);
        fragmentLayout = findViewById(R.id.myfragmentlayout);
        partitionsLayout = findViewById(R.id.partitionslayout);
        dynamicLayout = findViewById(R.id.dynamiclayout);
        /*  底部的布局*/
        main_bottom = findViewById(R.id.main_bottom);



        /**
         *
         *  悬浮框
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /**
         *
         *   侧滑栏布局
         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
         this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.addDrawerListener(toggle);
         toggle.syncState();
         */
          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //   注释掉则默认的三杠点击进入侧滑 变为  向右划屏幕 打开侧滑栏
         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /**
         *   上边  那一栏
         */
//        toolbar.setLogo(R.drawable.ic_menu_send);
        setSupportActionBar(toolbar);//
        main_head_portrait.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                toggle.onDrawerStateChanged(DrawerLayout.SCREEN_STATE_ON);
//                toggle setPosition(1);
                return true;
            }
        });
        /**
         *
         *      中部  fragment
         *
         */



        /**
         *    底部 菜单栏
         *
         */
        main_bottom.setOnNavigationItemSelectedListener(new MyBottomNavigationViewOnNavigationItemSelectedListener());// 选择监听
        main_bottom.setSelectedItemId(R.id.main_bottom_mainpage);//  设置默认选择
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     *
     *   加载右上角的  menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     *   右上角 menu监听器
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     *      侧滑栏  选择监听器
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mainpage) {

        } else if (id == R.id.history) {

        } else if (id == R.id.offlinecache) {

        } else if (id == R.id.collection) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     *
     *
     * //  底部  选择栏  监听器   切换中部视图
     */
    class  MyBottomNavigationViewOnNavigationItemSelectedListener  implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.main_bottom_mainpage://  主页
                    Log.d(TAG, "onNavigationItemSelected: 主页");
                        fragmentLayout.setElevation(Float.MAX_VALUE);
                        partitionsLayout.setElevation(0);
                        dynamicLayout.setElevation(0);
                    break;
                case R.id.main_bottom_partitions:// 分区
                    Log.d(TAG, "onNavigationItemSelected: 分区");
                    partitionsLayout.setElevation(Float.MAX_VALUE);
                    fragmentLayout.setElevation(0);
                    dynamicLayout.setElevation(0);
                    break;
                case R.id.main_bottom_dynamic://  动态
                    Log.d(TAG, "onNavigationItemSelected: 动态");
                    dynamicLayout.setElevation(Float.MAX_VALUE);
                    fragmentLayout.setElevation(0);
                    partitionsLayout.setElevation(0);
                    break;
                default:break;
            }
            return false;
        }
    }
    /**
     *
     * 上方滑动栏
     */
}
