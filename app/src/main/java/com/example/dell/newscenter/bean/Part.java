package com.example.dell.newscenter.bean;

public class Part {
    private int id;
    private int imageId;
    private String name;

    public Part(int id, int imageId, String name) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
