package org.spring.chapter3;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireBeanTest 
{
	@Test
	public void testAutowireByName() throws IOException
	{
		System.out.println("=========testAutowireByName==========");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byName.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}
	
	@Test
	public void testAutowireByType1() throws IOException
	{
		System.out.println("=========testAutowireByType1==========");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byType1.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}
}
