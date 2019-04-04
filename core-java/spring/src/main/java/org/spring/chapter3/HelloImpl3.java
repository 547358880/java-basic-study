package org.spring.chapter3;

import java.beans.ConstructorProperties;

public class HelloImpl3 implements HelloApi
{
	private String message;
	private int index;
	
//	@ConstructorProperties({"message", "index"})
	public HelloImpl3(String message, int index) 
	{
		this.index = index;
		this.message = message;
	}
	
	@Override
	public void sayHello() 
	{
		System.out.println(index + ": " + message);
	}
	
}
