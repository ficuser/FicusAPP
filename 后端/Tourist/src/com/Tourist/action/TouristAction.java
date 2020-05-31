package com.Tourist.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.Tourist.dao.TouristDao;
import com.Tourist.dao.TouristDaoImpl;
import com.Tourist.entity.Tourist;
import com.Tourist.util.JsonRespone_hotel;
import com.Tourist.util.JsonResponse_tourist;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;



public class TouristAction {
	
	private String touristCity;
	
	private List<Tourist> list;
	
	private int nums;
	
	public String execute() throws IOException{
		Gson gson =new Gson();
		ActionContext ctx=ActionContext.getContext();
		HttpServletResponse response=(HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		TouristDao dao=new TouristDaoImpl();
		
		list=dao.getTouristView(touristCity,nums);
		List<JsonResponse_tourist> arrayList=new ArrayList<JsonResponse_tourist>();
		if(list==null)
		{
			out.print(gson.toJson(500));
			return null;
		}else {
			if(nums>list.size())
			{
				out.print(gson.toJson(500));
				return null;
			}else{
				for(int i=0;i<nums;i++)
				{
					JsonResponse_tourist jsonResponse=new JsonResponse_tourist();
					Tourist tour=list.get(i);
					
					int touristId=tour.getTouristId();
					jsonResponse.setTouristId(touristId);
					
					String touristTesturl=tour.getTouristTesturl();
					jsonResponse.setTouristTesturl(touristTesturl);
					
					String touristGrade=tour.getTouristTouristGrade();
					jsonResponse.setTouristTouristGrade(tour.getTouristTouristGrade());
					
					String touristPrice=tour.getTouristViewPrice();
					jsonResponse.setTouristViewPrice(touristPrice);
					
					String TouristViewname=tour.getTouristViewName();
					jsonResponse.setTouristViewName(TouristViewname);
					
					String touristImage1=tour.getTouristImageView1();
					jsonResponse.setTouristImageView1(touristImage1);
					
					String touristImage2=tour.getTouristImageView2();
					jsonResponse.setTouristImageView2(touristImage2);
					
					String touristImage3=tour.getTouristImageView3();
					jsonResponse.setTouristImageView3(touristImage3);
					
					String touristImage4=tour.getTouristImageView4();
					jsonResponse.setTouristImageView4(touristImage4);
					
					String touristImage5=tour.getTouristImageView5();
					jsonResponse.setTouristImageView5(touristImage5);
					
					arrayList.add(jsonResponse);
				}
				out.print(gson.toJson(arrayList));
			}
		}
		return null;
	}

	public String getTouristCity() {
		return touristCity;
	}

	public void setTouristCity(String touristCity) {
		this.touristCity = touristCity;
	}

	public int getNums(){
		return nums;
	}

	public void setNums(int nums){
		this.nums = nums;
	}
	
}
