package com.Tourist.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.Tourist.entity.Tourist;
import com.Tourist.util.HibernateSessionFactory;

public class TouristDaoImpl implements TouristDao{

	@Override
	public List<Tourist> getTouristView(String TouristCity, int nums) {
		// TODO Auto-generated method stub
		if(TouristCity!=null&&nums!=0)
		{
			Session session =HibernateSessionFactory.getSession();
			
			String hql="from Tourist as a where a.touristCity='" + TouristCity + "'";
			
			Query query=(Query)session.createQuery(hql);
			
			query.setFirstResult(0);
			query.setMaxResults(10);
			
			List<Tourist> list=query.list();
			
			if(!list.isEmpty())
			{
				HibernateSessionFactory.closeSession();
				return list;
				
			}else {
				HibernateSessionFactory.closeSession();
				return null;
			}
		}
		return null;
	}
	
}
/*
public class TouristDaoImpl implements TouristDao{
	
	@Override
	public <List>Tourist getTouristView(String TouristCity,int nums) {
		// TODO Auto-generated method stub
		if(TouristCity!=null&&nums!=0)
		{
			Session session =HibernateSessionFactory.getSession();
			
			String hql="from Tourist as a where a.touristCity='" + TouristCity + "'";
			Query query=(Query)session.createQuery(hql);
			
			//Tourist tourist=(Tourist)query.uniqueResult();
			
			query.setFirstResult(0);
			query.setMaxResults(10);
			
			List<Tourist> list=query.list();
			if(!list.isEmpty())
			{
				HibernateSessionFactory.closeSession();
				return list;
				
			}else {
				HibernateSessionFactory.closeSession();
				return null;
			}
		}else {
			return null;
		}
	}
}*/