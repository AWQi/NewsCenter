package com.example.dell.newscenter.bean;

public class User {
    private int id;
    private String name ;
    private String pwd;
    private String tel;
    private String headUrl;
    private String gender ;

    public User(int id, String name, String tel, String headUrl, String gender) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.headUrl = headUrl;
        this.gender = gender;
    }

    public User(String name, String pwd, String tel, String gender) {
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
    public void setGender(String gender) {
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
    public String getGender() {
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
                ", gender='" + gender + '\'' +
                '}';
    }
}
