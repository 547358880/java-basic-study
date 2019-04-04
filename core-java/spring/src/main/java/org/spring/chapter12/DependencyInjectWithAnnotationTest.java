package org.spring.chapter12;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectWithAnnotationTest 
{
	private static String configLocation = "classpath:chapter12/dependecyInjectWithAnnotation.xml";
	
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
	
	//1、Spring自带依赖注入注解  
	@Test  
	public void testRequiredForXmlSetterInject() {  
       TestBean testBean = ctx.getBean("testBean", TestBean.class);  
       Assert.assertEquals("Hello", testBean.getMessage());  
	}  
	
	@Test
	public void testAutowiredForConstructor()
	{
		System.out.println("==========testAutowiredForConstructor==========");
		TestBean11 testBean11 = ctx.getBean("testBean11", TestBean11.class);
		Assert.assertEquals("Hello", testBean11.getMessage());
	}
	
	@Test
	public void testAutowiredForField()
	{
		System.out.println("==========testAutowiredForField==========");
		TestBean12 testBean12 = ctx.getBean("testBean12", TestBean12.class);
		Assert.assertEquals("Hello", testBean12.getMessage());
	}
	
	@Test
	public void testAutowiredForMethod()
	{
		System.out.println("==========testAutowiredForMethod==========");
		TestBean13 testBean13 = ctx.getBean("testBean13", TestBean13.class);
		Assert.assertEquals("Hello", testBean13.getMessage());
		
		TestBean14 testBean14 = ctx.getBean("testBean14", TestBean14.class);  
	    Assert.assertEquals("Hello", testBean14.getMessage());  
	    Assert.assertEquals(ctx.getBean("list", List.class), testBean14.getList());  
	}
	
	@Test
    public void testValueInject() 
	{
        TestBean21 testBean21 = ctx.getBean("testBean21", TestBean21.class);
        Assert.assertEquals("Hello", testBean21.getMessage());
        TestBean22 testBean22 = ctx.getBean("testBean22", TestBean22.class);
        Assert.assertEquals("HelloHello", testBean22.getMessage());
    }
	
	
}
