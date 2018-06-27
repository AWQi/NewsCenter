package com.example.dell.newscenter.bean;

public class Comment {
private  int id ;
private  int userId;
private  String authorImageUrl;
private  String authorName;
private  String content;
private  String Date;
private  int dynamicId;

 public Comment() {
    }

    public Comment(int authorId, String content, int dynamicId) {
        this.userId = authorId;
        this.content = content;
        this.dynamicId = dynamicId;
    }

    public Comment(int userId, String authorImageURL, String authorName, String content, int dynamicId) {
        this.userId = userId;
        this.authorImageUrl = authorImageURL;
        this.authorName = authorName;
        this.content = content;
        this.dynamicId = dynamicId;
    }

    public Comment(int authorId, String authorImageURL, String authorName, String content, String date, int dynamicId) {
        this.userId = authorId;
        this.authorImageUrl = authorImageURL;
        this.authorName = authorName;
        this.content = content;
        Date = date;
        this.dynamicId = dynamicId;
    }

    public Comment(int id, int authorId, String authorImageURL, String authorName, String content, String date, int dynamicId) {
        this.id = id;
        this.userId = authorId;
        this.authorImageUrl = authorImageURL;
        this.authorName = authorName;
        this.content = content;
        Date = date;
        this.dynamicId = dynamicId;
    }

    public int getDynamicId() {
        return dynamicId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int authorId) {
        this.userId = authorId;
    }

    public void setAuthorImageUrl(String authorImageURL) {
        this.authorImageUrl = authorImageURL;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return Date;
    }
}
