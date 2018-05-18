package com.example.dell.newscenter.bean;

public class Commen {
private  int id ;
private  int authorId;
private  String authorImageURL;
private  int authorName;
private  String content;
private  String Date;

    public Commen() {
    }

    public Commen(int id, int authorId, String authorImage, int authorName, String content, String date) {
        this.id = id;
        this.authorId = authorId;
        this.authorImageURL = authorImageURL;
        this.authorName = authorName;
        this.content = content;
        Date = date;
    }

    public Commen(String authorImageURL, String content) {
        this.authorImageURL = authorImageURL;
        this.content = content;
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

    public int getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return Date;
    }
}
