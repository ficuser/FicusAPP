package com.Tourist.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.Tourist.dao.HotelDao;
import com.Tourist.dao.HotelDaoImpl;
import com.Tourist.dao.ViewDao;
import com.Tourist.dao.ViewDaoImpl;
import com.Tourist.entity.Hotel;
import com.Tourist.entity.View;
import com.Tourist.util.JsonRespone_hotel;
import com.Tourist.util.JsonResponse_View;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

public class ViewAction {
	private String ViewCity;
	private int viewPage;
	public String getViewCity() {
		return ViewCity;
	}
	public void setViewCity(String viewCity) {
		ViewCity = viewCity;
	}
	public int getViewPage() {
		return viewPage;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}
	public String execute() throws IOException{
		Gson gson =new Gson();
		ActionContext ctx=ActionContext.getContext();
		HttpServletResponse response=(HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//JsonRespone_sum jsonResponseSum=new JsonRespone_sum();
		//JsonResponse_View jsonResponse=new JsonResponse_View();
		ViewDao dao=new ViewDaoImpl();
		List<View> list=dao.getViewList(ViewCity,viewPage);
		List<JsonResponse_View> arrayList=new ArrayList<JsonResponse_View >();
		int status;
		if(list==null){
			status=500;
			out.print(gson.toJson(status));
			return null;
		}else {
			if(viewPage>list.size())
			{
				status=500;
				out.print(gson.toJson(status));
				return null;
			}else {
				for(int i=0;i<viewPage;i++)
				{
					View view=list.get(i);
					JsonResponse_View jsonResponse=new JsonResponse_View();
					
					String viewImgeUrl=view.getViewImgeUrl();
					jsonResponse.setViewImgeUrl(viewImgeUrl);
					 
					String viewText=view.getViewText();
					jsonResponse.setViewText(viewText);
					
					String viewUrl=view.getViewUrl();
					jsonResponse.setViewUrl(viewUrl);
					
					int viewId=view.getViewId();
					jsonResponse.setViewId(viewId);
				}
				out.print(gson.toJson(arrayList));
			}
		}
		
		return null;
	}
}
