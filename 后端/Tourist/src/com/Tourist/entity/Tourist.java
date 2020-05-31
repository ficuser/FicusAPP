package com.Tourist.entity;

/**
 * Tourist entity. @author MyEclipse Persistence Tools
 */

public class Tourist implements java.io.Serializable {

	// Fields

	private Integer touristId;
	private String touristTesturl;
	private String touristViewName;
	private String touristImageView1;
	private String touristImageView2;
	private String touristImageView3;
	private String touristImageView4;
	private String touristImageView5;
	private String touristTouristGrade;
	private String touristViewPrice;
	private String touristCity;

	// Constructors

	/** default constructor */
	public Tourist() {
	}

	/** minimal constructor */
	public Tourist(String touristViewName, String touristImageView4) {
		this.touristViewName = touristViewName;
		this.touristImageView4 = touristImageView4;
	}

	/** full constructor */
	public Tourist(String touristTesturl, String touristViewName, String touristImageView1, String touristImageView2,
			String touristImageView3, String touristImageView4, String touristImageView5, String touristTouristGrade,
			String touristViewPrice, String touristCity) {
		this.touristTesturl = touristTesturl;
		this.touristViewName = touristViewName;
		this.touristImageView1 = touristImageView1;
		this.touristImageView2 = touristImageView2;
		this.touristImageView3 = touristImageView3;
		this.touristImageView4 = touristImageView4;
		this.touristImageView5 = touristImageView5;
		this.touristTouristGrade = touristTouristGrade;
		this.touristViewPrice = touristViewPrice;
		this.touristCity = touristCity;
	}

	// Property accessors

	public Integer getTouristId() {
		return this.touristId;
	}

	public void setTouristId(Integer touristId) {
		this.touristId = touristId;
	}

	public String getTouristTesturl() {
		return this.touristTesturl;
	}

	public void setTouristTesturl(String touristTesturl) {
		this.touristTesturl = touristTesturl;
	}

	public String getTouristViewName() {
		return this.touristViewName;
	}

	public void setTouristViewName(String touristViewName) {
		this.touristViewName = touristViewName;
	}

	public String getTouristImageView1() {
		return this.touristImageView1;
	}

	public void setTouristImageView1(String touristImageView1) {
		this.touristImageView1 = touristImageView1;
	}

	public String getTouristImageView2() {
		return this.touristImageView2;
	}

	public void setTouristImageView2(String touristImageView2) {
		this.touristImageView2 = touristImageView2;
	}

	public String getTouristImageView3() {
		return this.touristImageView3;
	}

	public void setTouristImageView3(String touristImageView3) {
		this.touristImageView3 = touristImageView3;
	}

	public String getTouristImageView4() {
		return this.touristImageView4;
	}

	public void setTouristImageView4(String touristImageView4) {
		this.touristImageView4 = touristImageView4;
	}

	public String getTouristImageView5() {
		return this.touristImageView5;
	}

	public void setTouristImageView5(String touristImageView5) {
		this.touristImageView5 = touristImageView5;
	}

	public String getTouristTouristGrade() {
		return this.touristTouristGrade;
	}

	public void setTouristTouristGrade(String touristTouristGrade) {
		this.touristTouristGrade = touristTouristGrade;
	}

	public String getTouristViewPrice() {
		return this.touristViewPrice;
	}

	public void setTouristViewPrice(String touristViewPrice) {
		this.touristViewPrice = touristViewPrice;
	}

	public String getTouristCity() {
		return this.touristCity;
	}

	public void setTouristCity(String touristCity) {
		this.touristCity = touristCity;
	}

}