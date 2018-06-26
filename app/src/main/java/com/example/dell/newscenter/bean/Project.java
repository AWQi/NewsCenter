package com.example.dell.newscenter.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable{
    protected int id;
    protected int authorid;
    protected  String  title ;
    protected String imageUrl;
    protected String videoUrl;
    protected int praiseNum;
    protected int commentsNum;
    protected String kind;
    protected int authorId;
    protected String authorName;
    protected String authorHeadUrl;


    protected String introduction;
    protected int praisesNum;
    protected String date;
    protected int viewNum;
    protected int collectNum;

    public void setId(int id) {
        this.id = id;
    }
    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }
    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public void setAuthorHeadUrl(String authorHeadUrl) {
        this.authorHeadUrl = authorHeadUrl;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public void setPraisesNum(Integer praisesNum) {
        this.praisesNum = praisesNum;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }
    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Integer getPraisesNum() {
        return praisesNum;
    }

    public String getDate() {
        return date;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public Project() {
    }

    public Project(String title, String imageURL) {
        this.title = title;
        this.imageUrl = imageURL;
    }

    public Project(int id, String title, String imageURL, String videoURL, int praiseNum, int commentsNum, String kind) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageURL;
        this.videoUrl = videoURL;
        this.praiseNum = praiseNum;
        this.commentsNum = commentsNum;
        this.kind = kind;
    }

    public Project(int id, String title, String imageURL, String videoURL, int praiseNum, int commentsNum, String kind, int authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageURL;
        this.videoUrl = videoURL;
        this.praiseNum = praiseNum;
        this.commentsNum = commentsNum;
        this.kind = kind;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    protected Project(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imageUrl = in.readString();
        videoUrl = in.readString();
        praiseNum = in.readInt();
        commentsNum = in.readInt();
        kind = in.readString();
        authorId = in.readInt();
        authorName = in.readString();
        authorHeadUrl = in.readString();
    }

    public Project(int id, String title, String imageURL, String videoURL, int praiseNum, int commentsNum, String kind, int authorId, String authorName, String authorHeadUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageURL;
        this.videoUrl = videoURL;
        this.praiseNum = praiseNum;
        this.commentsNum = commentsNum;
        this.kind = kind;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorHeadUrl = authorHeadUrl;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    /**
     *  读取数据
     */

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     *          写入数据
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(title);
            parcel.writeString(imageUrl);
            parcel.writeString(videoUrl);
            parcel.writeInt(praiseNum);
            parcel.writeInt(commentsNum);
            parcel.writeString(kind);
            parcel.writeInt(authorId);
            parcel.writeString(authorName);
            parcel.writeString(authorHeadUrl);

            parcel.writeString(introduction);
            parcel.writeInt(praisesNum);
            parcel.writeString(date);
            parcel.writeInt(viewNum);
            parcel.writeInt(collectNum);

    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImageURL() {
        return imageUrl;
    }
    public String getVideoURL() {
        return videoUrl;
    }
    public int getPraiseNum() {
        return praiseNum;
    }
    public int getCommentsNum() {
        return commentsNum;
    }
    public String getKind() {
        return kind;
    }
    public int getAuthorId() {
        return authorId;
    }
    public String getAuthorName() {
        return authorName;
    }
    public String getAuthorHeadUrl() {
        return authorHeadUrl;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", authorid=" + authorid +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", praiseNum=" + praiseNum +
                ", commentsNum=" + commentsNum +
                ", kind='" + kind + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorHeadUrl='" + authorHeadUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                ", praisesNum=" + praisesNum +
                ", date='" + date + '\'' +
                ", viewNum=" + viewNum +
                ", collectNum=" + collectNum +
                '}';
    }
}
