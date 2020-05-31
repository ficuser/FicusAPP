package com.Tourist.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Tourist.entity.Hotel;
import com.Tourist.util.HibernateSessionFactory;

public class HotelDaoImpl implements HotelDao {

	@Override
	public List<Hotel> getHotelList(String hotelCity, int nums) {
		// TODO Auto-generated method stub
		if(hotelCity!=null&&nums!=0)
		{
			Session session =HibernateSessionFactory.getSession();
			
			String hql="from Hotel as a where a.hotelCity='" + hotelCity + "'";
			Query query=(Query)session.createQuery(hql);
			if(nums>10)
			{
				nums=nums-10;
				query.setFirstResult(nums);
				nums=nums+10;
				query.setMaxResults(nums);
			}else {
				query.setFirstResult(0);
				query.setMaxResults(10);
			}
			List<Hotel> list=query.list();
			if(list!=null)
			{
				HibernateSessionFactory.closeSession();
				return list;//.subList(nums-10,nums-1);
				
			}else {
				HibernateSessionFactory.closeSession();
				return null;
			}
			
		}else{
			return null;
		}
	}
}
