package com.example.dell.newscenter.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.utils.CacheUtils;
import com.example.dell.newscenter.utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private static final String TAG = "GuideActivity";
private ViewPager viewPager = null;
private Button btn_start = null;
private LinearLayout ll_point_group = null;
private  ImageView red_point = null;
private ArrayList<ImageView> imageViews ;
private int leftMAX; //  两点的间距
private  int widthdpi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = findViewById(R.id.viewPager);
        btn_start = findViewById(R.id.btn_start);
        ll_point_group = findViewById(R.id.ll_point_group);
        red_point = findViewById(R.id.red_point);

        //  准备数据
        int[] ids = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};



        imageViews = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(ids[i]);
            //设置中央 填充图片
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageViews.add(imageView);

            // 创建 点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             *  单位是像素，LayoutParams用于配置java代码构建出来的元素属性，大小
             */
             widthdpi = DensityUtil.dip2px(this,10); // 做适配 ，像素px在不同的屏幕里大小不同而dp在不同的屏幕里相同
            LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if (i!=0){// 除第一个点  剩下所有距左边 20像素
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            //  添加到线性布局
            ll_point_group.addView(point);

        }
        // 设置viewPager  的适配器
        viewPager.setAdapter(new MyPagerAdapter());
        /**
         *
         *
         *  红点滑动效果
         */
        //   根据View生命周期  当视图执行到 onLayout 或者onDraw 的时候，视图的边距优质
        //  当一个视图树中的全局布局发生改变或者视图中的某个视图的可视状态发生改变时调用
        //  l1、得到间距
        red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //  l2、 得到屏幕滑动百分比  在监听器内计算margin
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  l1、  保存曾经进入过主页面
                CacheUtils.setBoolean(GuideActivity.this, WelcomeActivity.START_MAIN,true);
                // l2、   跳转主页面
                Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                //  3、 关闭引导页面
                 finish();
            }
        });
    }

    /**
     *
     *  视图树监听类
     */
    class  MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {
                //  理论上执行不止一次，而我们只需要一次，那么就 执行后移除
            red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            // 灰点间距
             leftMAX  = ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();

        }
    }
    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         *
         *      作用类似于 getView
         *      instantiateItem  实例化
         * @param container    ViewPager
         * @param position     要创建页面的位置
         * @return   返回和创建当前有关系的值
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // 创建
            ImageView imageView = imageViews.get(position);
            // 添加
            container.addView(imageView);

//            return_icon position;   //  l1
            return  imageView; // l2
//            return_icon super.instantiateItem(container, position);
        }

        /**
         *
         *   判断
         * @param view  当前视图
         * @param object   上面 instantianItem 返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return_icon view ==imageViews.get(Integer.parseInt((String) object));
       return  view == object;
        }

        /**
         *
         *    销毁
         * @param container   viewPager
         * @param position   要销毁页面的位置
         * @param object   要销毁的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         *
         *    当页面滑动  回调
         * @param position    当前滑动位置
         * @param positionOffset   页面滑动百分比
         * @param positionOffsetPixels    滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            //  两点间的移动距离 = 屏幕滑动百分比 *间距
            int leftMargin = (int)(positionOffset*leftMAX);
            //  起始位置
            int startMargin = position*leftMAX;
            // 两点间滑动对应的坐标 = 原来的起始位置+两点间移动的距离
            RelativeLayout.LayoutParams param =(RelativeLayout.LayoutParams) red_point.getLayoutParams();
            param.leftMargin = startMargin+leftMargin;
            red_point.setLayoutParams(param);


            //   设置开始按钮显示
            if (position==imageViews.size()-1){
                btn_start.setVisibility(View.VISIBLE);
            }else {
                btn_start.setVisibility(View.INVISIBLE);
            }

//            Log.d(TAG, "position: "+position);
//            Log.d(TAG, "leftMAX: "+leftMAX);
//            Log.d(TAG, "onPageScrolled: ");
//            Log.d(TAG, "positionOffset: "+positionOffset);
//            Log.d(TAG, "leftMargin: "+leftMargin);
        }

        /**
         *
         *   当页面被选中时  回调
         * @param position  被选中的位置
         */
        @Override
        public void onPageSelected(int position) {
        }
        /**
         *   当页面滑动状态发生变化时
         * @param state  状态  三个
         */
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
