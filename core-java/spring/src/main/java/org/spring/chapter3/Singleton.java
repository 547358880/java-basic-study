package org.spring.chapter3;

/*
 * 单例模式
 */
public class Singleton 
{
	private Singleton() {}
	
	private static class InstanceHolder
	{
		private static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance()
	{
		return InstanceHolder.INSTANCE;
	}
}
