package com.qrcode.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.qrcode.zxing.encoding.EncodingHandler;

public class QrCodeUtil {
    private static final String TAG = "QrCodeUtil";
static  public Bitmap enCodeQr(String str){
    if (str==null||str.trim().equals("")){
        Log.d(TAG, "二维码信息导入为空");
    }
    //根据输入的文本生成对应的二维码并且显示出来
    Bitmap mBitmap = null;
    try {
        mBitmap = EncodingHandler.createQRCode(str, 500);

    } catch (WriterException e) {
        e.printStackTrace();
    }
    return mBitmap;
}
//static  public  String deCodeQr(){
//
//}
}
