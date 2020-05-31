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

public class HotelActiono {
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
		//JsonRespone_hotel jsonResponse=new JsonRespone_hotel();
		HotelDao dao=new HotelDaoImpl();
		List<Hotel> list=dao.getHotelList(HotelCity,nums);
		int status;
		List<JsonRespone_hotel> arrayList=new ArrayList<JsonRespone_hotel>();
		if(list==null){
			status=500;
			out.print(gson.toJson(status));
			return null;
		}else {
			if(nums>list.size())
			{
				status=500;
				out.print(gson.toJson(status));
				return null;
			}else {
				for(int i=0;i<nums;i++)
				{
					Hotel hotel=list.get(i);
					JsonRespone_hotel jsonResponse=new JsonRespone_hotel();
					
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
					
					
				    String hotelStar=hotel.getHotelStar();
				    jsonResponse.setHotelStar(hotelStar);
				    
				    String hotelTag=hotel.getHotelTag();
				    jsonResponse.setHotelTag(hotelTag);
				    
				    String hotelScore=hotel.getHotelScore();
				    jsonResponse.setHotelScore(hotelScore);
				    
				    
				    String hotelPrice=hotel.getHotelPrice();
				    jsonResponse.setHotelPrice(hotelPrice);
				    
				    String hotelUser=hotel.getHotelUser();
				    jsonResponse.setHotelUrl(hotelUser);
				    
				    
				    arrayList.add(jsonResponse);
				}
				out.print(gson.toJson(arrayList));
			}
		}
		return null;
	}
}
