package org.spring.chapter3;

public class HelloApiDecorator implements HelloApi
{
	private HelloApi helloApi;
	
	private String message;
	
	public HelloApiDecorator() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public HelloApiDecorator(HelloApi helloApi)
	{
		this.helloApi = helloApi;
	}
	
	
	public HelloApi getHelloApi() 
	{
		return helloApi;
	}

	public void setHelloApi(HelloApi helloApi) 
	{
		this.helloApi = helloApi;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	@Override
	public void sayHello() 
	{
		System.out.println("==========装饰开始===========");
        helloApi.sayHello();
        System.out.println("==========装饰完毕===========");
	}

}
