package com.Tourist.dao;

import com.Tourist.entity.User;

public interface UserDao {
	public User getUser(int userId);
	
	public int getUserIdByAccount(String userAccount);
	
	public Boolean login(String userAccount,String userPassword);
	
	public void saverUser(User user);

}
