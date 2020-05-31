package com.Tourist.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.Tourist.dao.ImageDao;
import com.Tourist.dao.ImageDaoImpl;
import com.Tourist.entity.Image;
import com.Tourist.util.JsonResponse_Image;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

public class ImageAction {
	
	private JsonResponse_Image jsonResponse=new JsonResponse_Image();
	
	private String ImageCity;
	
	public String getImageCity() {
		return ImageCity;
	}

	public void setImageCity(String imageCity) {
		ImageCity = imageCity;
	}

	public String execute() throws IOException{
		Gson gson =new Gson();
		ActionContext ctx=ActionContext.getContext();
		HttpServletResponse response=(HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		ImageDao dao=new ImageDaoImpl();
		
		Image image=dao.getImageUrl(ImageCity);
		
		if(image==null)
		{
			jsonResponse.setStatus(500);
		}else{
			jsonResponse.setStatus(200);
			
			String imageUrl=image.getImageUrl();
			jsonResponse.setImageUrl(imageUrl);
		}
		out.print(gson.toJson(jsonResponse));
		return null;
	}
}
