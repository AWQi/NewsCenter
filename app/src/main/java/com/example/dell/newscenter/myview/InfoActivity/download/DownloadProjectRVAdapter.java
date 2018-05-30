package com.example.dell.newscenter.myview.InfoActivity.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.myview.InfoActivity.download.downloading.DownloadProject;
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.utils.ActivityUtil;

import java.util.List;

public class DownloadProjectRVAdapter extends RecyclerView.Adapter{
    static  final  public  int DOWNLOAD_TITLE_VIEW_TYPE = 1;
    static  final  public  int DOWNLOADED_VIEW_TYPE = 2;
    static  final  public  int DOWNLOADING_VIEW_TYPE = 3;

    private Context context;
    private AppCompatActivity activity;
    private List<DownloadProject> downloadingList;
    private List<DownloadProject> downloadedList;

    public DownloadProjectRVAdapter(Context context) {
        this.context = context;
    }
    public DownloadProjectRVAdapter(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity  =activity;
    }

    public DownloadProjectRVAdapter(Context context, List<DownloadProject> downloadingList, List<DownloadProject> downloadedList) {
        this.context = context;
        this.downloadingList = downloadingList;
        this.downloadedList = downloadedList;
    }

    /**
     *   重写类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position==0||position==(downloadedList.size()+1)){//
            return DOWNLOAD_TITLE_VIEW_TYPE;
        }else if(position>0&&position<=downloadedList.size()) {
            return  DOWNLOADED_VIEW_TYPE;
        }else if (position>(downloadedList.size()+1)){
            return DOWNLOADING_VIEW_TYPE;
        }else {
            return  0;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context = parent.getContext();
        }
      switch (viewType){
          case DOWNLOAD_TITLE_VIEW_TYPE: //标题
              View v1 = LayoutInflater.from(context).inflate(R.layout.download_item_title,parent,false);
              return new DownloadTitleHolder(v1);

          case DOWNLOADED_VIEW_TYPE:// 已下载
              View v2 = LayoutInflater.from(context).inflate(R.layout.downloaded_project_item,parent,false);
              return  new DownloadedHolder(v2);

          case  DOWNLOADING_VIEW_TYPE://正在下载
              View v3 = LayoutInflater.from(context).inflate(R.layout.downloading_project_item,parent,false);
              return new DownloadingHolder(v3);

          default: break;

      }
      return  null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DownloadTitleHolder){//  标题
            DownloadTitleHolder h = (DownloadTitleHolder) holder;
            if (position ==0){
                h.downloadedItemTieleTV.setText("已下载：");
            }else {
                h.downloadedItemTieleTV.setText("正在下载：");
            }
        }else if(holder instanceof DownloadedHolder) {
            DownloadedHolder h = (DownloadedHolder) holder;
            DownloadProject downloadProject =  downloadedList.get(position-1);
            final Project project = downloadProject.getObjProject();
            Glide.with(context).load(project.getImageURL())
                    .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                    .fitCenter().into(h.downloadedItemImageIV);
            h.downloadedItemTitleTV.setText(project.getTitle());
            h.downloadedItemAuthorTV.setText(project.getAuthorName());
//            h.downloadedItemSizeTV.setText(downloadedList.get(position-1));
            h.downloadedItemDetailsTV.setOnClickListener(new View.OnClickListener() {//点击详情跳转到播放页
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    intent.putExtra("project",project);
                    context.startActivity(intent);
                }
            });
            h.view.setOnClickListener(new View.OnClickListener() { //  点击整个布局，直接播放
                 @Override
                public void onClick(View v) {

                }
            });
        }else if(holder instanceof DownloadingHolder){ //正在下载页
            DownloadingHolder h = (DownloadingHolder) holder;
            DownloadProject downloadProject =  downloadedList.get(position-2-downloadedList.size());
            final Project project = downloadProject.getObjProject();
            Glide.with(context).load(project.getImageURL())
                    .override(ActivityUtil.getWidth(context),ActivityUtil.getHeight(context))
                    .fitCenter().into(  h.downloadingItemImageIV);
            h.downloadingItemTitleTV.setText(project.getTitle());
            h.downloadingItemPB.setMax((int)downloadProject.getContentLength()/1024);
            h.downloadingItemPB.setProgress((int)downloadProject.getBreakPoints()/1024);
            h.downloadingItemControllIB.setOnClickListener(new View.OnClickListener() {// 下载控制 ，下载或暂停
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return downloadedList.size()+downloadingList.size()+2;
    }



    /**
     *   标题
     */
    class DownloadTitleHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView downloadedItemTieleTV;
        public DownloadTitleHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            downloadedItemTieleTV = view.findViewById(R.id.downloadedItemTieleTV);
        }
    }
    /**
     *    已下载完成项
     */
    class DownloadedHolder extends RecyclerView.ViewHolder{
        public  View view;
        public ImageView downloadedItemImageIV;
        public TextView downloadedItemTitleTV;
        public TextView downloadedItemAuthorTV;
        public TextView  downloadedItemSizeTV;
        public TextView downloadedItemDetailsTV;
        public DownloadedHolder(View itemView) {
            super(itemView);
            this.view = itemView;
               downloadedItemImageIV = view.findViewById(R.id.downloadedItemImageIV);
                downloadedItemTitleTV = view.findViewById(R.id.downloadedItemTitleTV);
                downloadedItemAuthorTV = view.findViewById(R.id.downloadedItemAuthorTV);
                downloadedItemSizeTV = view.findViewById(R.id.downloadedItemSizeTV);
                downloadedItemDetailsTV = view.findViewById(R.id.downloadedItemDetailsTV);
        }
    }
    /**
     *   正在下载项
     */
    class DownloadingHolder extends RecyclerView.ViewHolder{
        public Boolean isDownloading =false;
        public View view;
        public ImageView downloadingItemImageIV;
        public TextView downloadingItemTitleTV;
        public ProgressBar downloadingItemPB;
        public ImageButton downloadingItemControllIB;
        public DownloadingHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            downloadingItemImageIV = view.findViewById(R.id.downloadingItemImageIV);
            downloadingItemTitleTV = view.findViewById(R.id.downloadingItemTitleTV);
            downloadingItemPB = view.findViewById(R.id.downloadingItemPB);
            downloadingItemControllIB = view.findViewById(R.id.downloadingItemControllIB);
        }
    }
}
