package com.example.dell.newscenter.myview.InfoActivity.download.downloading;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class DownloadProjectDBUtil {
static final public  String PROJECT_DATEBASE_NAME = "DownloadProject";

    static  public void createDB(){
     LitePal.getDatabase();
 }
 //  添加 记录
 static  public  void addDownloadProject(DownloadProject downloadProject){
     downloadProject.save();
 }
 // 删除记录
 static  public  void deleteDownloadProject(DownloadProject downloadProject){
//     downloadProject.delete();
     DataSupport.deleteAll(PROJECT_DATEBASE_NAME,"id=?",downloadProject.getId()+"");
 }
 // 更新记录
 static public  void updateDownloadProject(DownloadProject downloadProject){
     downloadProject.update((long)downloadProject.getId());
 }
 // 查询所有
static  public List<DownloadProject> queryAllDownloadProject(){
        List<DownloadProject> allDownloadProjectList = DataSupport.findAll(DownloadProject.class);
        return allDownloadProjectList;
}
//  查询已经下载好的
static  public List<DownloadProject> queryDownloadProjectED(){
        List<DownloadProject> downloadProjectEDList = DataSupport.where("breakPoints=contentLength").find(DownloadProject.class);
        return  downloadProjectEDList;
}
//  查询 正在下载的
 static  public  List<DownloadProject> queryDownloadProjectING(){
     List<DownloadProject> downloadProjectINGList = DataSupport.where("breakPoints<contentLength").find(DownloadProject.class);
     return downloadProjectINGList;
 }
}
