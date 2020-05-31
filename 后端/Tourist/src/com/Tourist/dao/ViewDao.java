package com.Tourist.dao;

import java.util.List;

import com.Tourist.entity.Hotel;
import com.Tourist.entity.View;

public interface ViewDao  {
	public List<View> getViewList(String ViewCity,int viewPage);
}
