package com.Tourist.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Tourist.entity.Hotel;
import com.Tourist.entity.View;
import com.Tourist.util.HibernateSessionFactory;

public class ViewDaoImpl implements ViewDao{

	@Override
	public List<View> getViewList(String ViewCity, int viewPage) {
		if(ViewCity !=null&&viewPage!=0)
		{
			Session session =HibernateSessionFactory.getSession();
			
			String hql="from View as a where a.viewCity='"+ViewCity + "'";
			Query query=(Query)session.createQuery(hql);
			
			if(viewPage>10)
			{
				query.setFirstResult(viewPage-10);
				query.setMaxResults(viewPage);
			}else {
				query.setFirstResult(0);
				query.setMaxResults(10);
			}
			List<View> list=query.list();
			if(list!=null)
			{
				HibernateSessionFactory.closeSession();
				return list;
				
			}else {
				HibernateSessionFactory.closeSession();
				return null;
			}
			
		}else{
			return null;
		}
	}
}
