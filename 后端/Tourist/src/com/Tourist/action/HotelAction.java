package com.Tourist.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.List;
import com.Tourist.entity.Hotel;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.Tourist.util.JsonRespone_sum;
import com.Tourist.dao.HotelDao;
import com.Tourist.dao.HotelDaoImpl;

import com.Tourist.util.JsonRespone_hotel;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

public class HotelAction{
	private String HotelCity;
	private int nums;
	
	public String getHotelCity() {
		return HotelCity;
	}

	public void setHotelCity(String hotelCity) {
		HotelCity = hotelCity;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public String execute() throws IOException{
		Gson gson =new Gson();
		ActionContext ctx=ActionContext.getContext();
		HttpServletResponse response=(HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//JsonRespone_sum jsonResponseSum=new JsonRespone_sum();
		JsonRespone_hotel jsonResponse=new JsonRespone_hotel();
		HotelDao dao=new HotelDaoImpl();
		List<Hotel> list=dao.getHotelList(HotelCity,nums);
		
		if(list==null){
			jsonResponse.setStatus(500);
		}else {
			if(nums>list.size())
			{
				jsonResponse.setStatus(500);
			}else {
				Hotel hotel=list.get(nums);
				jsonResponse.setStatus(200);
				String HotelUrl=hotel.getHotelUrl();
				jsonResponse.setHotelImageUrl(hotel.getHotelUrl());
				
				String HotelAddreaa=hotel.getHotelAddress();
				jsonResponse.setHotelAddress(hotel.getHotelAddress());
				
				String HotelCharm=hotel.getHotelCharm();
				jsonResponse.setHotelCharm(hotel.getHotelCharm());
				
				String HotelCity=hotel.getHotelCity();
				jsonResponse.setHotelCity(hotel.getHotelCity());
				
				String HotelEvaluate=hotel.getHotelEvaluate();
				jsonResponse.setHotelEvaluate(hotel.getHotelEvaluate());
				
				String HostelName=hotel.getHotelHostelName();
				jsonResponse.setHotelHostelName(hotel.getHotelHostelName());
				
				int HotelId=hotel.getHotelId();
				jsonResponse.setHotelId(hotel.getHotelId());
				
				String imageUrl=hotel.getHotelImageUrl();
				jsonResponse.setHotelImageUrl(imageUrl);
				
				String hotellow=hotel.getHotelLow();
				jsonResponse.setHotelLow(hotellow);
			}
		}
		out.print(gson.toJson(jsonResponse));
		return null;
	}
}
