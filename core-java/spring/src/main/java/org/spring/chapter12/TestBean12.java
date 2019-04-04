package org.spring.chapter12;

import org.springframework.beans.factory.annotation.Autowired;

public class TestBean12 
{
	@Autowired //字段注入
	private String message;

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	
}
