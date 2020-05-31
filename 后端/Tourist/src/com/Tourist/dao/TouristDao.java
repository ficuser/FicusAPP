package com.Tourist.dao;

import java.util.List;

import com.Tourist.entity.Tourist;

public interface TouristDao {
	public List<Tourist> getTouristView(String City,int nums);
}
