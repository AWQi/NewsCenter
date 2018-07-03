package com.example.dell.newscenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Muscovy implements Parcelable {
    private Integer id;

    private String name;

    private String imageUrl;

    private String videoUrl;

    private Integer num;

    protected Muscovy(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        imageUrl = in.readString();
        videoUrl = in.readString();
        if (in.readByte() == 0) {
            num = null;
        } else {
            num = in.readInt();
        }
    }

    public static final Creator<Muscovy> CREATOR = new Creator<Muscovy>() {
        @Override
        public Muscovy createFromParcel(Parcel in) {
            return new Muscovy(in);
        }

        @Override
        public Muscovy[] newArray(int size) {
            return new Muscovy[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(videoUrl);
        if (num == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(num);
        }
    }
}