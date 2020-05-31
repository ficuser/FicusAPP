package com.Tourist.util;

public class JsonResponse_Image {
	
	private int status;
	
	private String ImageUrl;
	
	private String ImageCity;
	
	private int ImageId;

	public int getImageId() {
		return ImageId;
	}

	public void setImageId(int imageId) {
		ImageId = imageId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getImageCity() {
		return ImageCity;
	}

	public void setImageCity(String imageCity) {
		ImageCity = imageCity;
	}
	

}
