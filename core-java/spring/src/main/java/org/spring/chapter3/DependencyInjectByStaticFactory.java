package org.spring.chapter3;

/*
 * 静态工厂方法注入
 */
public class DependencyInjectByStaticFactory 
{
	public static HelloApi newInstance(String message, int index)
	{
		return new HelloImpl3(message, index);
	}
}
