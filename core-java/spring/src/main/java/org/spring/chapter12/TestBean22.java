package org.spring.chapter12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class TestBean22 
{
	private String message;
	
	// 方法参数使用
	@Autowired
	public void initMessage(@Value(value="#{message}#{message}") String message) 
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
