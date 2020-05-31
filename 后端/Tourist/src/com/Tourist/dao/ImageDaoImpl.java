package com.Tourist.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Tourist.entity.Image;
import com.Tourist.entity.View;
import com.Tourist.util.HibernateSessionFactory;

public class ImageDaoImpl implements ImageDao{

	@Override
	public Image getImageUrl(String imageCity) {
		if(imageCity!=null)
		{
			Session session =HibernateSessionFactory.getSession();
			
			String hql="from Image as a where a.imageCity='" + imageCity + "'";
			Query query=(Query)session.createQuery(hql);
			List<Image> list=query.list();
			if(list!=null)
			{
				HibernateSessionFactory.closeSession();
				return list.get(0);
				
			}else {
				HibernateSessionFactory.closeSession();
				return null;
			}
			
		}else{
			return null;
		}
	}
}
