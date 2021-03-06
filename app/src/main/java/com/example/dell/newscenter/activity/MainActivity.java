package com.example.dell.newscenter.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;

import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.myview.InfoActivity.attention.MyAttentionActivity;
import com.example.dell.newscenter.myview.InfoActivity.collection.MyCollectionActivity;
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownLoadProjectActivity;
import com.example.dell.newscenter.myview.InfoActivity.dynamic.MyDynamicActivity;
import com.example.dell.newscenter.myview.InfoActivity.fans.MyFansActivity;
import com.example.dell.newscenter.myview.InfoActivity.qrcode.QrCodeActivity;
import com.example.dell.newscenter.myview.InfoActivity.userinfo.UserInfoActivity;
import com.example.dell.newscenter.myview.InfoActivity.history.MyHistoryActivity;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.myview.base.FloatingActionsMenu;
import com.example.dell.newscenter.myview.mainactivity.PublicationDynamicsActivity;
import com.example.dell.newscenter.myview.mainactivity.dynamic.DynamicLayout;
import com.example.dell.newscenter.myview.mainactivity.mainpager.FragmentLayout;
import com.example.dell.newscenter.myview.mainactivity.partitions.PartitionsLayout;

import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.ApplicationUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "NewCenter";
    static  final String  SERVRE_LIVE_APK = "http://140.143.16.51/live/apk/live.apk";
    static  final String LOCAL_LIVE_APK = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))+"/live.apk";//本地插件地址
    private User user = ApplicationUtil.getUser();
      /*  顶部的 布局*/
    private CircleImageView main_toolbar_head = null;
    private  Toolbar toolbar = null;

    /*  中央  的  布局*/
    private FrameLayout main_center_layout = null;
    private FragmentLayout fragmentLayout = null;
    private PartitionsLayout partitionsLayout = null;
    private DynamicLayout dynamicLayout = null;

    /*  底部的  布局*/
    private BottomNavigationView main_bottom = null;
    /* 侧滑栏 布局*/
    private DrawerLayout drawer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /* 顶部的布局*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        main_toolbar_head = findViewById(R.id.main_toolbar_head);

        /* 中央的布局*/
        main_center_layout = findViewById(R.id.center_LinearLayout);
        fragmentLayout = findViewById(R.id.myfragmentlayout);
        partitionsLayout = findViewById(R.id.partitionslayout);
        dynamicLayout = findViewById(R.id.dynamiclayout);
        /*  底部的布局*/
        main_bottom = findViewById(R.id.main_bottom);
        /* 侧滑栏布局*/
        drawer = findViewById(R.id.drawer_layout);
//
//        /**
//         * 悬浮菜单
//         *
//         */
//        FloatingActionsMenu fam = findViewById(R.id.fam);
//        fam.setOnItemMenuClickListener(new FloatingActionsMenu.OnItemMenuClickListener() {
//            @Override
//            public void onItemMenuClick(View view, int position) {
//
//            }
//        });
        /**
         *
         *  菜单栏
         */
         FloatingActionsMenu  fam = findViewById(R.id.fam);
        fam.setOnItemMenuClickListener(new FloatingActionsMenu.OnItemMenuClickListener() {
            @Override
            public void onItemMenuClick(View view, int position) {
                int id = view.getId();
                switch (id){
                    case  R.id.startLiveIV:  // 直播
                        /**
                         *
                         *  //启动另外一个App
                          */

                        /**
                         *  获取已安装应用的列表
                         */
                        List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(0);
                        boolean isInstall = false;
                        for (PackageInfo p :packageInfos){// 遍历 list  能找到  live 就  跳转
                            if (p.packageName.equals("com.example.live")){
                                //  l1.
                                String packageName = "com.example.live";
                                String activity = "com.example.live.LiveActivity";
                                ComponentName cn = new ComponentName(packageName,activity);
                                Intent t1 = new Intent();
                                t1.setComponent(cn);
                                t1.putExtra("StudioNum",ApplicationUtil.getUser().getId());
                                Log.d(TAG, "onItemMenuClick: "+"开直播");
                                startActivity(t1);
                                isInstall=true;
                                break;
                            }
                        }
                        // 找不到 list  就   发送  url 下载
                        /**
                         *  调用  系统的 download 下载live插件
                         */
                        /**
                         *
                         * l1、使用 浏览器下载
                         */
                        if (!isInstall) {
                            Intent t1 = new Intent(Intent.ACTION_VIEW, Uri.parse(SERVRE_LIVE_APK));
                            startActivity(t1);
                        }
                        /**
                         *  l2、使用系统自带下载中心下载
                          */
//                        //创建下载任务,downloadUrl就是下载链接
//                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(SERVRE_LIVE_APK));
//                        //获取下载管理器
//                        DownloadManager downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                        // 通知栏
//                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                        // 设置通知的标题和描述
////                        request.setTitle("直播插件下载");
//                        //指定下载路径和下载文件名
//                        File liveApk = new File(LOCAL_LIVE_APK);
//                        request.setDestinationUri(Uri.fromFile(liveApk));
//                        //将下载任务加入下载队列，否则不会进行下载
//                        downloadManager.enqueue(request);

                        break;
                    case  R.id.startDynamicIV:  // 发表动态
                        Intent t2 = new Intent(MainActivity.this,PublicationDynamicsActivity.class);
                        startActivity(t2);
                        break;
                    default:break;
                }
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


//        drawer = findViewById(R.id.drawer_layout);
//        //   注释掉则默认的三杠点击进入侧滑 变为  向右划屏幕 打开侧滑栏
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /**
         *   侧滑栏中的 head 部分 头像 钱包 二维码 关注，动态，粉丝
         *
         */
       /*   头部的layout    */
        View  navigationHeadView  = navigationView.getHeaderView(0);
        // 头像
        ImageView headImage = navigationHeadView.findViewById(R.id.headImage);
        Glide.with(MainActivity.this).load(ApplicationUtil.getUser().getHeadUrl()).fitCenter().into(headImage);
        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserInfoActivity.class);
                intent.putExtra("user",ApplicationUtil.getUser());
                intent.putExtra("isEditAble",true);
                startActivity(intent);

            }
        });
        // 昵称
        TextView nameTV = navigationHeadView.findViewById(R.id.nameTV);
        nameTV.setText(ApplicationUtil.getUser().getName());
        // 等级
        TextView gradeTV = navigationHeadView.findViewById(R.id.gradeTV);
        gradeTV.setText("LV3");
        // 钱包
        navigationHeadView.findViewById(R.id.wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 二维码
        navigationHeadView.findViewById(R.id.qrcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, QrCodeActivity.class);
            startActivity(intent);
            }
        });

        // 动态
        TextView myDynamicEntrance = navigationHeadView.findViewById(R.id.myDynamicEntrance);
        myDynamicEntrance .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyDynamicActivity.class);
                startActivity(intent);
                }
        });
        myDynamicEntrance.setText(user.getMyDynamicNum()+"");
        navigationHeadView.findViewById(R.id.myDynamicEntrance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyDynamicActivity.class);
                startActivity(intent);
            }
        });
        // 关注
        TextView myAttentionEntrance = navigationHeadView.findViewById(R.id.myAttentionEntrance);
        myAttentionEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyAttentionActivity.class);
                startActivity(intent);
            }
        });
        myAttentionEntrance.setText(user.getMyAttentNum()+"");
        // 粉丝
        TextView myFansEntrance = navigationHeadView.findViewById(R.id.myFansEntrance);
        myFansEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyFansActivity.class);
                startActivity(intent);
            }
        });
        myFansEntrance.setText(user.getMyFanNum()+"");
        /**
         *  标题栏
         */
//        toolbar.setLogo(R.drawable.ic_menu_send);
        setSupportActionBar(toolbar);//
        Glide.with(MainActivity.this)
                .load(ApplicationUtil.getUser().getHeadUrl())
                .override(ActivityUtil.getWidth(MainActivity.this),ActivityUtil.getHeight(MainActivity.this))
                .fitCenter()
                .into(main_toolbar_head);
        main_toolbar_head.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                toggle.onDrawerStateChanged(DrawerLayout.SCREEN_STATE_ON);
//                toggle setPosition(l1);
                drawer.openDrawer(GravityCompat.START);
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
        Intent intent = new Intent();
        int id = item.getItemId();

        if (id == R.id.mainpage) {}
            else if (id==R.id.share){
                shareText("推荐给朋友","subject","content");
           } else if (id == R.id.night) {    // 夜间模式   动作不是跳转   特殊对待
                 ApplicationUtil.exchangeNightMode();
                 recreate();
            }else {
                if (id == R.id.history) {            //  历史记录
                    intent.setClass(MainActivity.this, MyHistoryActivity.class);
                } else if (id == R.id.offlinecache) { //离线缓存
    //                intent.setClass(MainActivity.this, DownloadActivity.class);
                    intent.setClass(MainActivity.this, DownLoadProjectActivity.class);
                } else if (id == R.id.collection) {   //我的收藏
                    intent.setClass(MainActivity.this, MyCollectionActivity.class);
                } else if (id == R.id.exitLogin) {   // 退出登录
                    intent.setClass(MainActivity.this,LoginActivity.class);
                }
                startActivity(intent);
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



    private void shareText(String dlgTitle, String subject, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TEXT, content);

        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) { // 自定义标题
            startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题
            startActivity(intent);
        }
    }

}
