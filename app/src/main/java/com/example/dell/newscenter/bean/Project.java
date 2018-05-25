package com.example.dell.newscenter.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable{
    protected int id;
    protected  String  title ;
    protected String imageURL;
    protected String videoURL;
    protected int praiseNum;
    protected int commentsNum;
    protected String kind;
    protected int authorId;
    protected String authorName;
    protected String authorHeadUrl;

    public Project() {
    }

    public Project(String title, String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }

    public Project(int id, String title, String imageURL, String videoURL, int praiseNum, int commentsNum, String kind) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.praiseNum = praiseNum;
        this.commentsNum = commentsNum;
        this.kind = kind;
    }

    public Project(int id, String title, String imageURL, String videoURL, int praiseNum, int commentsNum, String kind, int authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.praiseNum = praiseNum;
        this.commentsNum = commentsNum;
        this.kind = kind;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    protected Project(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imageURL = in.readString();
        videoURL = in.readString();
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
        this.imageURL = imageURL;
        this.videoURL = videoURL;
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
            parcel.writeString(imageURL);
            parcel.writeString(videoURL);
            parcel.writeInt(praiseNum);
            parcel.writeInt(commentsNum);
            parcel.writeString(kind);
            parcel.writeInt(authorId);
            parcel.writeString(authorName);
            parcel.writeString(authorHeadUrl);

    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getVideoURL() {
        return videoURL;
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
                ", title='" + title + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", praiseNum=" + praiseNum +
                ", commentsNum=" + commentsNum +
                ", kind='" + kind + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorHeadUrl='" + authorHeadUrl + '\'' +
                '}';
    }
}
