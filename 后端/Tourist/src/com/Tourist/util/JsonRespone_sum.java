package com.Tourist.util;

import java.util.ArrayList;
import java.util.List;

public class JsonRespone_sum {
	
	
	private List<JsonRespone_hotel> JsonResponehotelList;
	
	//private List<JsonResponse_tourist> JsonResponetouristList;
	
	private int status;
	
	public List<JsonRespone_hotel> getJsonResponehotelList(){
		return JsonResponehotelList;
	}

	public void setJsonResponehotelList(JsonRespone_hotel jsonResponehotel) {
		JsonResponehotelList.add(jsonResponehotel);
	}
/*
	public List<JsonResponse_tourist> getJsonResponetouristList() {
		return JsonResponetouristList;
	}

	public void setJsonResponetouristList(JsonResponse_tourist jsonResponetourist) {
		JsonResponetouristList.add(jsonResponetourist);
	}*/

	public int getStatus(){
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
