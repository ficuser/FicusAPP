package com.Tourist.entity;



/**
 * Hotel entity. @author MyEclipse Persistence Tools
 */

public class Hotel  implements java.io.Serializable {


    // Fields    

     private Integer hotelId;
     private String hotelCity;
     private String hotelHostelName;
     private String hotelStar;
     private String hotelImageUrl;
     private String hotelAddress;
     private String hotelTag;
     private String hotelScore;
     private String hotelCharm;
     private String hotelLow;
     private String hotelEvaluate;
     private String hotelPrice;
     private String hotelUser;
     private String hotelUrl;


    // Constructors

    /** default constructor */
    public Hotel() {
    }

    
    /** full constructor */
    public Hotel(String hotelCity, String hotelHostelName, String hotelStar, String hotelImageUrl, String hotelAddress, String hotelTag, String hotelScore, String hotelCharm, String hotelLow, String hotelEvaluate, String hotelPrice, String hotelUser, String hotelUrl) {
        this.hotelCity = hotelCity;
        this.hotelHostelName = hotelHostelName;
        this.hotelStar = hotelStar;
        this.hotelImageUrl = hotelImageUrl;
        this.hotelAddress = hotelAddress;
        this.hotelTag = hotelTag;
        this.hotelScore = hotelScore;
        this.hotelCharm = hotelCharm;
        this.hotelLow = hotelLow;
        this.hotelEvaluate = hotelEvaluate;
        this.hotelPrice = hotelPrice;
        this.hotelUser = hotelUser;
        this.hotelUrl = hotelUrl;
    }

   
    // Property accessors

    public Integer getHotelId() {
        return this.hotelId;
    }
    
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelCity() {
        return this.hotelCity;
    }
    
    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelHostelName() {
        return this.hotelHostelName;
    }
    
    public void setHotelHostelName(String hotelHostelName) {
        this.hotelHostelName = hotelHostelName;
    }

    public String getHotelStar() {
        return this.hotelStar;
    }
    
    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getHotelImageUrl() {
        return this.hotelImageUrl;
    }
    
    public void setHotelImageUrl(String hotelImageUrl) {
        this.hotelImageUrl = hotelImageUrl;
    }

    public String getHotelAddress() {
        return this.hotelAddress;
    }
    
    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelTag() {
        return this.hotelTag;
    }
    
    public void setHotelTag(String hotelTag) {
        this.hotelTag = hotelTag;
    }

    public String getHotelScore() {
        return this.hotelScore;
    }
    
    public void setHotelScore(String hotelScore) {
        this.hotelScore = hotelScore;
    }

    public String getHotelCharm() {
        return this.hotelCharm;
    }
    
    public void setHotelCharm(String hotelCharm) {
        this.hotelCharm = hotelCharm;
    }

    public String getHotelLow() {
        return this.hotelLow;
    }
    
    public void setHotelLow(String hotelLow) {
        this.hotelLow = hotelLow;
    }

    public String getHotelEvaluate() {
        return this.hotelEvaluate;
    }
    
    public void setHotelEvaluate(String hotelEvaluate) {
        this.hotelEvaluate = hotelEvaluate;
    }

    public String getHotelPrice() {
        return this.hotelPrice;
    }
    
    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getHotelUser() {
        return this.hotelUser;
    }
    
    public void setHotelUser(String hotelUser) {
        this.hotelUser = hotelUser;
    }

    public String getHotelUrl() {
        return this.hotelUrl;
    }
    
    public void setHotelUrl(String hotelUrl) {
        this.hotelUrl = hotelUrl;
    }
   








}