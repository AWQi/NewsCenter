package com.example.dell.newscenter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.activity.MainActivity;

public class ActivityUtil {
    /**
     *
     *      context   转  activity
     * @param context
     * @return
     */
    public static Activity scanForActivity(Context context) {
        if (context == null)
            return null;
        else if (context instanceof Activity)
            return (Activity) context;
        else if (context instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) context).getBaseContext());

        return null;
    }
   public static int  getWidth(Context context){
       return ActivityUtil.scanForActivity(context).getWindowManager().getDefaultDisplay().getWidth();

   }
   public static  int getHeight(Context context){
       return ActivityUtil.scanForActivity(context).getWindowManager().getDefaultDisplay().getHeight();
   }

}
