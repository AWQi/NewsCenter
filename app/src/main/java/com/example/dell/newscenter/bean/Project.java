package com.example.dell.newscenter.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable{
private int id;
private  String  title ;
private String imageURL;
private String videoURL;
private int praiseNum;
private int commentsNum;
private String kind;

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

    protected Project(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imageURL = in.readString();
        videoURL = in.readString();
        praiseNum = in.readInt();
        commentsNum = in.readInt();
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
                '}';
    }
}
