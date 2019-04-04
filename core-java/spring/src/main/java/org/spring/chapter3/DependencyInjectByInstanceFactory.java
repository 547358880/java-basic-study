package org.spring.chapter3;

/*
 * 实例工厂类
 */
public class DependencyInjectByInstanceFactory 
{
	public HelloApi newInstance(String message, int index)
	{
		return new HelloImpl3(message, index);
	}
}
