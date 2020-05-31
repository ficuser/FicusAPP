package com.Tourist.dao;

import java.util.ArrayList;
import java.util.List;

import com.Tourist.entity.Hotel;
public interface HotelDao {
	public List<Hotel> getHotelList(String Hotel,int nums);
}
