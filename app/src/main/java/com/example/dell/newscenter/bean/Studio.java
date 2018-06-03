package com.example.dell.newscenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 *   直播间
 */
public class Studio implements Parcelable {
    //直播间id可以与用户id相同
    private  int id ;
    // 封面 图片
    private  String imageUrl;
    //直播间 url
    private  String studioUrl;
    //标题
    private String title;
    // 分类
    private String kind;

    public Studio(int id, String imageUrl, String studioUrl, String title, String kind) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.studioUrl = studioUrl;
        this.title = title;
        this.kind = kind;
    }

    public static final Creator<Studio> CREATOR = new Creator<Studio>() {
        @Override
        public Studio createFromParcel(Parcel in) {
            return new Studio(in);
        }

        @Override
        public Studio[] newArray(int size) {
            return new Studio[size];
        }
    };

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", studioUrl='" + studioUrl + '\'' +
                ", title='" + title + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
    public int getId() {
        return id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getStudioUrl() {
        return studioUrl;
    }
    public String getTitle() {
        return title;
    }
    public String getKind() {
        return kind;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setStudioUrl(String studioUrl) {
        this.studioUrl = studioUrl;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    protected Studio(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imageUrl = in.readString();
        studioUrl = in.readString();
        kind = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(title);
                dest.writeString(imageUrl);
                dest.writeString(studioUrl);
                dest.writeString(kind);
    }
}
