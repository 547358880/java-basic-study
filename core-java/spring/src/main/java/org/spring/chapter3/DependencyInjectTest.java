package org.spring.chapter3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectTest 
{
	@Test
	public void testConstructorDependencyInjectTest()
	{
		System.out.println("=========testConstructorDependencyInjectTest=========");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter3/constructorDependencyInject.xml");
		// 1.根据参数索引获取注入的Bean
		HelloApi byIndex = ctx.getBean("byIndex", HelloApi.class);
		byIndex.sayHello();
		
		// 2.根据参数类型
		HelloApi byType = ctx.getBean("byType", HelloApi.class);
		byType.sayHello();
		
		// 3.根据参数名称
		HelloApi byName = ctx.getBean("byName", HelloApi.class);
		byName.sayHello();
	}
	
	@Test
	public void testStaticFactoryDependencyInject()
	{
		System.out.println("=========testStaticFactoryDependencyInject=========");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter3/staticFactoryDependencyInject.xml");
		// 1.根据参数索引获取注入的Bean
		HelloApi byIndex = ctx.getBean("byIndex", HelloApi.class);
		byIndex.sayHello();
		
		// 2.根据参数类型
		HelloApi byType = ctx.getBean("byType", HelloApi.class);
		byType.sayHello();
		
		// 3.根据参数名称
		HelloApi byName = ctx.getBean("byName", HelloApi.class);
		byName.sayHello();
	}
	
	@Test
    public void testInstanceFactoryDependencyInject() 
	{
		System.out.println("=========testInstanceFactoryDependencyInject=========");
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("chapter3/instanceFactoryDependencyInject.xml");
        
        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();
        
        //获取根据参数类型依赖注入的Bean
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();
        
        //获取根据参数名字依赖注入的Bean
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();
        
    }
	
	//setter 注入
	@Test
	public void testSetterDependencyInject()
	{
		System.out.println("=========testSetterDependencyInject=========");
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("chapter3/setterDependencyInject.xml");
        
        //获取根据参数索引依赖注入的Bean
        HelloApi bySetter = beanFactory.getBean("bySetter", HelloApi.class);
        bySetter.sayHello();
	}
	
	@Test
	public void testListInject()
	{
		System.out.println("=========testListInject=========");
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("chapter3/listInject.xml");
        
        //获取根据参数索引依赖注入的Bean
        ListTestBean listTestBean = beanFactory.getBean("listBean", ListTestBean.class);
        System.out.println(listTestBean.getValues());
	}
	
	@Test
	public void testMapInject()
	{
		System.out.println("=========testMapInject=========");
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("chapter3/mapInject.xml");
        
        //获取根据参数索引依赖注入的Bean
        MapTestBean mapTestBean = beanFactory.getBean("mapBean", MapTestBean.class);
        System.out.println(mapTestBean.getValues());
	}
}
