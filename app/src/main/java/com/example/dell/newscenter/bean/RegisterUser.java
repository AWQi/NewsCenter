package com.example.dell.newscenter.bean;

public class RegisterUser {
    private String name;
    private String password;
    private String tel;
    private Integer gender;

    public RegisterUser(String name, String password, String tel, Integer gender) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTel() {
        return tel;
    }

    public Integer getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
