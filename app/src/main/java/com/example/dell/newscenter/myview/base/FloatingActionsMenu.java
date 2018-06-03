package com.example.dell.newscenter.myview.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public  class FloatingActionsMenu extends ViewGroup{
    private static final String TAG = "FloatingActionsMenu";
    //  子控件之间的距离
    public   final  static  int DISTANCE = 80;
    // 枚举 菜单栏的状态
    public  enum  MenuStatus{STATUS_OPEN,STATUS_CLOSE}
    // 当前状态
    private  MenuStatus currentStatus = MenuStatus.STATUS_CLOSE;
    // 子菜单点击回调接口
    public interface  OnItemMenuClickListener{
        void  onItemMenuClick(View view, int position);// 此方法内对点击作出响应，需实例化
    }
    //  调用接口封装到方法
    private  OnItemMenuClickListener onItemMenuClickListener;
    public void setOnItemMenuClickListener(OnItemMenuClickListener onItemMenuClickListener){
        this.onItemMenuClickListener = onItemMenuClickListener;
    }
    public FloatingActionsMenu(Context context) {
        super(context);
    }
    public FloatingActionsMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //计算 menu 大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        if (childCount<3)throw  new IllegalStateException("当前控件至少需要三个子控件");
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int tempWidth = getChildAt(0).getMeasuredWidth();
        int tempHeight = getChildAt(0).getMeasuredHeight();

        setMeasuredDimension(tempWidth,(tempHeight+DISTANCE)*childCount);
    }
    // 计算控件位置
    //     changed   是否可以改变
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int measureWidth  = getMeasuredWidth();
            int measureHeidth  = getMeasuredHeight();
            int childCount = getChildCount();
            for(int i = 0 ;i<childCount;i++){
                final  View childAt = getChildAt(i);
                int childWidth = childAt.getMeasuredWidth();
                int childHeight = childAt.getMeasuredHeight();
                // 第一个子控件是菜单栏开关
                if (i==0){
                    int left = 0;
                    int top = (childCount-1-i)*(DISTANCE+childHeight);
                    int right = left+childWidth;
                    int bottom = top+childHeight;
                    childAt.layout(left,top,right,bottom);
                    childAt.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            changeStatuAnim(300);
                        }
                    });
                }else {
                    // 其余的为 子控件按钮
                    final  int temp = i;
                    int left = 0;
                    int top = (childCount-1-i)*(DISTANCE+childHeight);
                    int right = left+childWidth;
                    int bottom = top+childHeight;
                    //  根据坐标点在父布局中的位置 构造子控件，
                    //  left  right  是相对于 父布局  left的margin
                    //  top bottom  是相对于 父布局 top 的margin
                    childAt.layout(left,top,right,bottom);
                    childAt.setVisibility(View.GONE);
                    childAt.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (onItemMenuClickListener!=null){
                                onItemMenuClickListener.onItemMenuClick(childAt,temp);
                            }
                            clickItemAnim(temp);

                        }
                    });
                }
            }
        }
    }

    /**
     *    点击子菜单时的动画效果
     */
    private void clickItemAnim(int position){
        for (int i=1;i<getChildCount();i++){
            View childAt = getChildAt(i);
            if (i==position){
//                childAt.startAnimation(toBig());
                childAt.startAnimation(rotateAroundY());// Y轴旋转
            }
            childAt.setVisibility(GONE);
        }
        changeStatu();
    }

    /**
     *   变小，变透明
     * @return
     */
    private Animation toSmall(){
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);// 透明
        animationSet.setDuration(200);// 200 毫秒
        animationSet.addAnimation(alphaAnimation);
        return  animationSet;
    }
    /**
     *    变大,变透明
     */
    private  Animation toBig(){
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);// 透明
        animationSet.addAnimation(alphaAnimation);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,3,1,3,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);// 变大
        animationSet.setDuration(200);// 200 毫秒
        return  animationSet;
    }
    /**
     *  绕Y 轴旋转
     */
    private  Animation rotateAroundY(){
        RotateToYAnimation rotateToYAnimation = new RotateToYAnimation();
        rotateToYAnimation.setRepeatCount(1);
        return  rotateToYAnimation;
    }
    /**
     *
     *    子菜单状态改变动画效果
     */
    private  void changeStatuAnim(int durationMillis){
        int childCount = getChildCount();
        for (int i = 0;i<childCount;i++){
            final View view = getChildAt(i);
            int toX = 0;
            int toY = (i-1)*view.getHeight()+DISTANCE*i;
            if (i==0){
                //  do something
            }else {

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setDuration(300);
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                TranslateAnimation translateAnimation;
                AlphaAnimation alphaAnimation;
                // 根据不同的子菜单状态实现不同的效果
                if (!isopen()){
                    translateAnimation = new TranslateAnimation(toX,0,toY,0);
                    alphaAnimation = new AlphaAnimation(0,1);
//                    currentStatus = MenuStatus.STATUS_OPEN;
                }else {
                    translateAnimation = new TranslateAnimation(0,toX,0,toY);
                    alphaAnimation = new AlphaAnimation(1,0);
//                    currentStatus = MenuStatus.STATUS_CLOSE;

                }
                //  先添加旋转 在添加 平移
                animationSet.addAnimation(rotateAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(alphaAnimation);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (currentStatus == MenuStatus.STATUS_OPEN){
                            view.setVisibility(VISIBLE);
                        }else {

                            view.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(animationSet);
            }
        }
        changeStatu();
    }
    /**
     *   改变状态
     */
    private  void  changeStatu(){
        currentStatus = (currentStatus== MenuStatus.STATUS_OPEN)? MenuStatus.STATUS_CLOSE: MenuStatus.STATUS_OPEN;
    }

    /**
     *   对外暴露的方法  关闭子菜单
     */

    public  void  closeMenu(){changeStatuAnim(300);}
    /**
     * 判断是否打开  子菜单
     */
    public  boolean isopen(){return  currentStatus== MenuStatus.STATUS_OPEN;}








}