package com.example.dell.newscenter.myview.InfoActivity.download.downloading;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.utils.ActivityUtil;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.JsonUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

public class DownloadUtil implements ProgressResponseBody.ProgressListener{
    public static final int REQUEST_STORAGE_GROUP_CODE = 1;
    private static final String TAG = "DownloadUtil";
    ProgressBar progressBar;
    private ProgressDownloader downloader;
    private File file;
    //这里的Activity  与context 并不对应
    private  Context context;
    private Activity activity;
    private DownloadProject downloadProject;
    public DownloadUtil(Project project, Activity activity) {
        this.context = ApplicationUtil.getContext();
        this.activity = activity;
        this.downloadProject = projectToDownloadProject(project);
        requestPermission();
        beginDownload();
    }
    // 下载获取文件系统权限
    public  void requestPermission(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "没有权限: ");
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_GROUP_CODE);
            return;
        }
    }
    /**
     *  查重，检查是否已经下载过
     */
    public boolean checkDup(){
            if (DownloadProjectDBUtil.queryOne(downloadProject.getId())!=null){
                return  true;
            }else {
                return false;
            }
    }
    /**
     *
     *          开始下载
     */
    public void beginDownload() {
        if (checkDup()){   }
            file = new File(downloadProject.getLocalUrl());
            downloader = new ProgressDownloader(downloadProject.getObjProject().getVideoURL(), file,  this);
            downloader.download(0L);
            downloadProject.setStatus(DownloadProject.DOWNLOAD_ING);
            DownloadProjectDBUtil.addDownloadProject(downloadProject);
            Toast.makeText(context,"开始下载",Toast.LENGTH_SHORT).show();
    }

    /**
     *          暂停下载
     *
     */
     public void pauseDownload() {
         downloader.pause();
         Toast.makeText(context, "下载暂停", Toast.LENGTH_SHORT).show();
         // 存储此时的totalBytes，即断点位置。
         downloadProject.setBreakPoints(downloadProject.getTotalBytes()) ;
         downloadProject.setStatus(DownloadProject.DOWNLOAD_PAUSE);
         DownloadProjectDBUtil.updateDownloadProject(downloadProject);
    }

    /**
     *          继续下载
     */
     public void continueDownload() {
         downloader.download(downloadProject.getBreakPoints());
         downloadProject.setStatus(DownloadProject.DOWNLOAD_ING);
         DownloadProjectDBUtil.updateDownloadProject(downloadProject);
    }

    /**
     *      取消下载
     *
     */
    public void cancelDownload() {

    }

    @Override
    public void onPreExecute(long contentLength) {
        // 文件总长只需记录一次，要注意断点续传后的contentLength只是剩余部分的长度
        if (downloadProject.getContentLength() == 0L) {
            downloadProject.setContentLength(contentLength);
//            progressBar.setMax((int) (contentLength / 1024));
        }
    }

    @Override
    public void update(long totalBytes, boolean done) {
        // 注意加上断点的长度
        downloadProject.setTotalBytes(totalBytes + downloadProject.getBreakPoints()) ;
        //更新 进度条
//        progressBar.setProgress((int) (totalBytes + breakPoints) / 1024);
        if (done) {
            // 切换到主线程
            Observable
                    .empty()
                    .observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
                @Override
                public void run() throws Exception {
                    Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
                    downloadProject.setStatus(DownloadProject.DOWNLOAD_ED);
                }
            }).subscribe();
        }
        DownloadProjectDBUtil.updateDownloadProject(downloadProject);
    }

    private  DownloadProject projectToDownloadProject(Project project){
        String str = JsonUtil.ObjToStr(project);
        DownloadProject downloadProject= new DownloadProject();
        downloadProject.setProject(project);
        downloadProject.setId(project.getId());
        downloadProject.setLocalUrl(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+downloadProject.getObjProject().getTitle()+".mp4");
        downloadProject.setTotalBytes(0);
        downloadProject.setBreakPoints(0);
        return downloadProject;
    }
}
