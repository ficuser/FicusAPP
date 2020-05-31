package com.Tourist.entity;

/**
 * View entity. @author MyEclipse Persistence Tools
 */

public class View  implements java.io.Serializable {

	// Fields

	private Integer viewId;
	private String viewImgeUrl;
	private String viewText;
	private String viewUrl;
	private String viewCity;

	// Constructors

	/** default constructor */
	public View() {
	}

	/** minimal constructor */
	public View(String viewUrl, String viewCity) {
		this.viewUrl = viewUrl;
		this.viewCity = viewCity;
	}

	/** full constructor */
	public View(String viewImgeUrl, String viewText, String viewUrl, String viewCity) {
		this.viewImgeUrl = viewImgeUrl;
		this.viewText = viewText;
		this.viewUrl = viewUrl;
		this.viewCity = viewCity;
	}

	// Property accessors

	public Integer getViewId() {
		return this.viewId;
	}

	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}

	public String getViewImgeUrl() {
		return this.viewImgeUrl;
	}

	public void setViewImgeUrl(String viewImgeUrl) {
		this.viewImgeUrl = viewImgeUrl;
	}

	public String getViewText() {
		return this.viewText;
	}

	public void setViewText(String viewText) {
		this.viewText = viewText;
	}

	public String getViewUrl() {
		return this.viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getViewCity() {
		return this.viewCity;
	}

	public void setViewCity(String viewCity) {
		this.viewCity = viewCity;
	}

}