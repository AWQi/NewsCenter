//package com.example.dell.newscenter.myview.InfoActivity.download.downloading;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.dell.newscenter.R;
//
//import java.io.File;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Action;
//
//public class VideoDownLoadActivity extends AppCompatActivity implements ProgressResponseBody.ProgressListener,View.OnClickListener{
//    public static final  int  REQUEST_STORAGE_GROUP_CODE = 1;
//    public static final String TAG = "QrCodeActivity";
//    public static final String PACKAGE_URL = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";
//    ProgressBar progressBar;
//    private Button downloadButton;
//    private Button pauseButton;
//    private Button continueButton;
//    private long breakPoints;
//    private ProgressDownloader downloader;
//    private File file;
//    private long totalBytes;
//    private long contentLength;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_down_load);
////        ButterKnife.bind(this);
//
//        progressBar = findViewById(R.id.progressBar);
//        downloadButton = findViewById(R.id.downloadButton);
//        pauseButton= findViewById(R.id.pauseButton);
//        continueButton = findViewById(R.id.continueButton);
//
//        downloadButton.setOnClickListener(this);
//        pauseButton.setOnClickListener(this);
//        continueButton.setOnClickListener(this);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case REQUEST_STORAGE_GROUP_CODE :
//                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    Log.d(TAG, "获取到权限: ");
//                }
//        }
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
////        ||ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
//                ){
//            Log.d(TAG, "没有权限: ");
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE_GROUP_CODE);
//            return;
//        }
//        switch (view.getId()) {
//            case R.id.downloadButton:
//                // 新下载前清空断点信息
//                breakPoints = 0L;
//                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sample.apk");
//                downloader = new ProgressDownloader(PACKAGE_URL, file,  this);
//                downloader.download(0L);
//                break;
//            case R.id.pauseButton:
//                downloader.pause();
//                Toast.makeText(this, "下载暂停", Toast.LENGTH_SHORT).show();
//                // 存储此时的totalBytes，即断点位置。
//                breakPoints = totalBytes;
//                break;
//            case R.id.continueButton:
//                downloader.download(breakPoints);
//                break;
//        }
//    }
//
//    @Override
//    public void onPreExecute(long contentLength) {
//        // 文件总长只需记录一次，要注意断点续传后的contentLength只是剩余部分的长度
//        if (this.contentLength == 0L) {
//            this.contentLength = contentLength;
//            progressBar.setMax((int) (contentLength / 1024));
//        }
//    }
//
//    @Override
//    public void update(long totalBytes, boolean done) {
//        // 注意加上断点的长度
//        this.totalBytes = totalBytes + breakPoints;
//        progressBar.setProgress((int) (totalBytes + breakPoints) / 1024);
//        if (done) {
//            // 切换到主线程
//            Observable
//                    .empty()
//                    .observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
//                @Override
//                public void run() throws Exception {
//                    Toast.makeText(VideoDownLoadActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
//
//                }
//            }).subscribe();
//        }
//    }
//}
//
