package org.spring.chapter12;

import org.springframework.beans.factory.annotation.Value;

public class TestBean21 
{
	// 类字段使用
	@Value(value = "#{message}")
	private String message;

	public String getMessage() 
	{
		return message;
	}
	
	
}
