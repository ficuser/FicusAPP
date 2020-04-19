package com.example.ficus.db;


import org.litepal.crud.LitePalSupport;

public class Image extends LitePalSupport{
    private Integer ImageId;
    private String ImageCity;
    private String ImageUrl;

    public Integer getImageId() {
        return ImageId;
    }

    public void setImageId(Integer imageId) {
        ImageId = imageId;
    }

    public String getImageCity() {
        return ImageCity;
    }

    public void setImageCity(String imageCity) {
        ImageCity = imageCity;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
