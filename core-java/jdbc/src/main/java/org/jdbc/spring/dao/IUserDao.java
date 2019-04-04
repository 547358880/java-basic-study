package org.jdbc.spring.dao;

import org.jdbc.spring.UserModel;

public interface IUserDao 
{
	public void save(UserModel model);
	
	public int countAll();
}
