package com.example.dell.newscenter.bean;

public class User {
    private int id;
    private String name ;
    private String pwd;
    private int tel;
    private String headUrl;
    private String gender ;

    //   手机号  密码登录
    public User(String name, String headUrl){
        this.name = name;
        this.headUrl = headUrl;
    }
    public User(String pwd, int tel) {
        this.pwd = pwd;
        this.tel = tel;
    }
    public void setTel(int tel) {
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
    public int getTel() {
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
