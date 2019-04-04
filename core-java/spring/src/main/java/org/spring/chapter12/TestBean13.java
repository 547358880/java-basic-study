package org.spring.chapter12;

import org.springframework.beans.factory.annotation.Autowired;

public class TestBean13 
{
	private String message;

	public String getMessage() 
	{
		return message;
	}

	@Autowired  //方法参数注入
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	
}
