package com.example.dell.newscenter.myview.InfoActivity.download.downloading;

import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.utils.JsonUtil;

import org.litepal.crud.DataSupport;

public class DownloadProject extends   DataSupport{

    static final public int DOWNLOAD_ING = 1;
    static final public int DOWNLOAD_ED = 2;
    static final public int  DOWNLOAD_PAUSE = 3;
    static final public  String PROJECT_DATEBASE_NAME = "DownloadProject";
    private String  localUrl = null;
    private long breakPoints = 0L;
    private long contentLength = 0L;
    private int status = DOWNLOAD_ING;
    private int id ;
    private long totalBytes = 0L;
    /**
     *
     * //  特定  重写  studio_item  的get  set 方法 实际只存储  projec  的json 数据
     * 主要用于兼容  存储数据库  这样外部调用时无其他 影响
     */
    private  String project = null;

    public void setProject(Project project) {
        this.id  = project.getId();
        this.project = JsonUtil.ObjToStr(project);
    }

    public Project getObjProject() {
        return JsonUtil.StrToObj(project,Project.class);
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
    public void setBreakPoints(long breakPoints) {
        this.breakPoints = breakPoints;
    }
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getLocalUrl() {
        return localUrl;
    }
    public long getBreakPoints() {
        return breakPoints;
    }
    public long getContentLength() {
        return contentLength;
    }
    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    @Override
    public String toString() {
        return "DownloadProject{" +
                "localUrl='" + localUrl + '\'' +
                ", breakPoints=" + breakPoints +
                ", contentLength=" + contentLength +
                ", status=" + status +
                ", studio_item='" + project + '\'' +
                '}';
    }
}
