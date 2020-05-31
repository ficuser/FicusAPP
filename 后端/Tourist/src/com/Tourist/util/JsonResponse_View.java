package com.Tourist.util;

public class JsonResponse_View {
	
	
	private Integer viewId;
	private String viewImgeUrl;
	private String viewText;
	private String viewUrl;
	private String viewCity;
	private int Status;
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getViewCity() {
		return viewCity;
	}
	public void setViewCity(String viewCity) {
		this.viewCity = viewCity;
	}
	public Integer getViewId() {
		return viewId;
	}
	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}
	public String getViewImgeUrl() {
		return viewImgeUrl;
	}
	public void setViewImgeUrl(String viewImgeUrl) {
		this.viewImgeUrl = viewImgeUrl;
	}
	public String getViewText() {
		return viewText;
	}
	public void setViewText(String viewText) {
		this.viewText = viewText;
	}
	public String getViewUrl() {
		return viewUrl;
	}
	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}
	
	
}
