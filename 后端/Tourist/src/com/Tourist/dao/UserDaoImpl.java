package com.Tourist.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Tourist.entity.User;
import com.Tourist.util.HibernateSessionFactory;
import com.Tourist.dao.UserDao;

public class UserDaoImpl implements UserDao {
	private Transaction transaction;
	
	@Override
	public Boolean login(String userAccount, String userPassword) {
		// 根据
		if(userAccount !=null&&userPassword !=null)
		{
			Session session =HibernateSessionFactory.getSession();
			
			String hql="from User as a where a.userAccount='" + userAccount + "'and a.userPassword='" + userPassword + "'";
			Query query=(Query)session.createQuery(hql);
			
			User user=(User)query.uniqueResult();
			if(user!=null)
			{
				HibernateSessionFactory.closeSession();
				return true;
			}
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	@Override
	public User getUser(int userId) {
		// 根据ID去查
		Session session =HibernateSessionFactory.getSession();
		
		User user=(User)session.load(User.class, userId);
		return user;
	}

	@Override
	public int getUserIdByAccount(String userAccount) {
		// 根据属性去查
		Session session =HibernateSessionFactory.getSession();
		
		String hql="from User as a where a.userAccount='" + userAccount + "'";
		Query query=(Query)session.createQuery(hql);
		
		User user=(User)query.uniqueResult();
		if(user!=null)
		{
			HibernateSessionFactory.closeSession();
			return user.getUserId();
		}
		
		HibernateSessionFactory.closeSession();
		return user.getUserId();
	}

	@Override
	public void saverUser(User user) {
		// 增
		Session session=HibernateSessionFactory.getSession();
		try {
			transaction=session.beginTransaction();
			session.save(user);
			transaction.commit();
		}catch(Exception e) {
			transaction.rollback();
		}finally {
			HibernateSessionFactory.closeSession();
		}
		
	}
	

}
