package org.spring.chapter3;

public class HelloImpl implements HelloApi
{

	@Override
	public void sayHello() 
	{
		System.out.println("Hello World!");
	}
	
	public void destory()
	{
		System.out.println("de");
	}
	
}
