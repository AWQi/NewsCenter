package com.example.dell.newscenter.myview.base;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

/**

 调用代码
 RotateToYAnimation  rotateToYAnimation = new RotateToYAnimation();
 rotateToYAnimation.setRepeatCount(Animation.INFINITE); //翻转无数次
 view.startAnimation(rotateToYAnimation);

 */

public class RotateToYAnimation extends Animation{
private Camera camera = new Camera();
private int centerX ;
private int centerY;

    /**
     *   获取坐标  定义动画时间
     * @param width
     * @param height
     * @param parentWidth
     * @param parentHeight
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 获取中心点坐标
        centerX = width/2;
        centerY = width/2;
        //  动画执行时间
        setDuration(200);
        // 内插器
        setInterpolator(new DecelerateInterpolator());
    }

    /**
     *
     *    旋转角度
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
            final Matrix matrix = t.getMatrix();
            camera.save();
            //  旋转中心是Y轴  可自行设置
            camera.rotateY(360*interpolatedTime);
            //  把 摄像头  加在变换 矩阵上
        camera.getMatrix(matrix);
        //  设置翻转  中心点
        matrix.preTranslate(-centerX,centerY);
        matrix.postTranslate(centerX,centerY);
        camera.restore();
    }
}
