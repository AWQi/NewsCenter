package com.example.dell.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpImageDownloadUtil  {

    static private ImageView mImageView;
    static private String url;
    static  private ImageAsyncTask imageAsyncTask;
    static public void setImage(ImageView imageView, String url) {
        mImageView = imageView;
        HttpImageDownloadUtil.url = url;
        imageAsyncTask = new ImageAsyncTask();
        imageAsyncTask.execute();
    }

    static class ImageAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                // 创建一个URL
                URL url = new URL(HttpImageDownloadUtil.url);
                // 从URL获取对应资源的 InputStream
                InputStream inputStream = url.openStream();
                // 用inputStream来初始化一个Bitmap 虽然此处是Bitmap，但是URL不一定非得是Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // 关闭 InputStream
                inputStream.close();

                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            // 此处的形参o，是doInBackground的返回值
            mImageView.setImageBitmap((Bitmap) o);
        }
    }
}