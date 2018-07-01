package com.example.live;

public class Studio {
    private Integer id;

    private String imageUrl;

    private String title;

    private Integer premiere;

    private String studioUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPremiere() {
        return premiere;
    }

    public void setPremiere(Integer premiere) {
        this.premiere = premiere;
    }

    public String getStudioUrl() {
        return studioUrl;
    }

    public void setStudioUrl(String studioUrl) {
        this.studioUrl = studioUrl == null ? null : studioUrl.trim();
    }
}