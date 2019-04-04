package org.spring.chapter12;

import org.springframework.beans.factory.annotation.Autowired;

public class TestBean11 
{
	private String message;
	
	@Autowired	//构造器注入
	private TestBean11(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
