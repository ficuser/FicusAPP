package com.Tourist.entity;



/**
 * Image entity. @author MyEclipse Persistence Tools
 */

public class Image  implements java.io.Serializable {


    // Fields    

     private Integer imageId;
     private String imageCity;
     private String imageUrl;


    // Constructors

    /** default constructor */
    public Image() {
    }

    
    /** full constructor */
    public Image(String imageCity, String imageUrl) {
        this.imageCity = imageCity;
        this.imageUrl = imageUrl;
    }

   
    // Property accessors

    public Integer getImageId() {
        return this.imageId;
    }
    
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageCity() {
        return this.imageCity;
    }
    
    public void setImageCity(String imageCity) {
        this.imageCity = imageCity;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
   








}