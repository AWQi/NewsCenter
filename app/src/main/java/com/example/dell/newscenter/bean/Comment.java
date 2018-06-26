package com.example.dell.newscenter.bean;

public class Comment {
private  int id ;
private  int authorId;
private  String authorImageURL;
private  String authorName;
private  String content;
private  String Date;
private  int dynamicId;

 public Comment() {
    }

    public Comment(int authorId, String authorImageURL, String authorName, String content, String date, int dynamicId) {
        this.authorId = authorId;
        this.authorImageURL = authorImageURL;
        this.authorName = authorName;
        this.content = content;
        Date = date;
        this.dynamicId = dynamicId;
    }

    public Comment(int id, int authorId, String authorImageURL, String authorName, String content, String date, int dynamicId) {
        this.id = id;
        this.authorId = authorId;
        this.authorImageURL = authorImageURL;
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

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorImageURL(String authorImageURL) {
        this.authorImageURL = authorImageURL;
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

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorImageURL() {
        return authorImageURL;
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
