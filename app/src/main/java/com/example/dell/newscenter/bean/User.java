package com.example.dell.newscenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int id;
    private String name ;
    private String pwd;
    private String tel;
    private String headUrl;
    private int gender ;
    private Integer myAttentNum;
    private Integer myFanNum;
    private Integer myDynamicNum;

    public void setMyAttentNum(Integer myAttentNum) {
        this.myAttentNum = myAttentNum;
    }

    public void setMyFanNum(Integer myFanNum) {
        this.myFanNum = myFanNum;
    }

    public void setMyDynamicNum(Integer myDynamicNum) {
        this.myDynamicNum = myDynamicNum;
    }

    public Integer getMyAttentNum() {
        return myAttentNum;
    }

    public Integer getMyFanNum() {
        return myFanNum;
    }

    public Integer getMyDynamicNum() {
        return myDynamicNum;
    }

    public User(int id, String name, String tel, String headUrl, int gender) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.headUrl = headUrl;
        this.gender = gender;
    }

    public User(String name, String pwd, String tel, int gender) {
        this.name = name;
        this.pwd = pwd;
        this.tel = tel;
        this.gender = gender;
    }
    // fans  处调试使用
    public User(String name, String headUrl) {
        this.name = name;
        this.headUrl = headUrl;
    }

    public User(int id, String name, String tel, String headUrl, int gender, Integer myAttentNum, Integer myFanNum, Integer myDynamicNum) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.headUrl = headUrl;
        this.gender = gender;
        this.myAttentNum = myAttentNum;
        this.myFanNum = myFanNum;
        this.myDynamicNum = myDynamicNum;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPwd() {
        return pwd;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public int getGender() {
        return gender;
    }
    public String getTel() {
        return tel;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tel=" + tel +
                ", headUrl='" + headUrl + '\'' +
                ", secrecy='" + gender + '\'' +
                '}';
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        pwd = in.readString();
        tel = in.readString();
        headUrl = in.readString();
        gender = in.readInt();
    }
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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
        parcel.writeString(name);
        parcel.writeString(pwd);
        parcel.writeString(tel);
        parcel.writeString(headUrl);
        parcel.writeInt(gender);

    }
}
