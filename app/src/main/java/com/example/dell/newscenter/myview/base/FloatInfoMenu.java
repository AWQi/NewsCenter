package com.example.dell.newscenter.myview.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;



public class FloatInfoMenu extends ViewGroup{
    private Context context;
    private static final String TAG = "FloatInfoMenu";
    //  子控件之间的距离
    public   final  static  int RADIUS = 300;
    // 子菜单点击回调接口
    public interface  OnItemMenuClickListener{
        void  onItemMenuClick(View view, int position);// 此方法内对点击作出响应，需实例化
    }
    //  调用接口封装到方法
    public FloatInfoMenu.OnItemMenuClickListener onItemMenuClickListener;
    public void setOnItemMenuClickListener(FloatInfoMenu.OnItemMenuClickListener onItemMenuClickListener){
        this.onItemMenuClickListener = onItemMenuClickListener;
    }
    public FloatInfoMenu(Context context) {
        super(context);
        this.context =context;
    }
    public FloatInfoMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    //计算 menu 大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        if (childCount<3)throw  new IllegalStateException("当前控件至少需要三个子控件");
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int tempWidth = getChildAt(0).getMeasuredWidth();
        int tempHeight = getChildAt(0).getMeasuredHeight();
        setMeasuredDimension((tempWidth+RADIUS)*2,(tempHeight+RADIUS)*2);
        Log.d(TAG, "onMeasure: "+tempWidth+ "   " +tempHeight);
    }
    //
    //     changed

    /**
     *
     *     计算控件位置及大小   控件是从  左上角 为原点   建立坐标轴绘制
     * @param changed   是否可以改变
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
//            CircleImageView userInfoCV =   ActivityUtil.scanForActivity(context).findViewById(R.id.userInfoHeadCV);
            int measureWidth  = getMeasuredWidth();
            int measureHeidth  = getMeasuredHeight();
            int childCount = getChildCount();
            //  角度间隔 换算 弧度
            double rad = (360.0/childCount)*2*Math.PI/360;
            for(int i = 0 ;i<childCount;i++){
                final  int temp = i;
                final  View childAt = getChildAt(i);
                // 旋转弧度
                double rr = rad*i;
                int childWidth = childAt.getMeasuredWidth();
                int childHeight = childAt.getMeasuredHeight();
                // 第一个子控件是菜单栏开关
                //  layout中心与圆心的  x  y  差 确定每个圆心的 坐标
                int x = (int) ((measureWidth/2.0)+RADIUS*Math.cos(rr));
                int y =   (int) ((measureHeidth/2.0)+RADIUS*Math.sin(rr));
                int bottom = y+childHeight/2;
                int top = y-childHeight/2;
                int left = x-childWidth/2;
                int right  = x+childWidth/2;
                childAt.layout(left,top,right,bottom);
                childAt.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemMenuClickListener!=null){
                            onItemMenuClickListener.onItemMenuClick(childAt,temp);
                        }
                    }
                });
                startChildRotationAnimation(childAt);
            }
            startMenuRotationAnimation(this);

        }
    }

    public void startMenuRotationAnimation(final View view){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotateAnimatior = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimatior.setDuration(20000);
                rotateAnimatior.setRepeatCount(Animation.INFINITE);
                rotateAnimatior.setInterpolator(new LinearInterpolator());
                view.startAnimation(rotateAnimatior);

            }
        }).start();

    }
    public  void startChildRotationAnimation(final View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotateAnimatior = new RotateAnimation(0,-360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimatior.setDuration(20000);
                rotateAnimatior.setRepeatCount(Animation.INFINITE);
                rotateAnimatior.setInterpolator(new LinearInterpolator());
                view.startAnimation(rotateAnimatior);
            }
        }).start();

    }
}
