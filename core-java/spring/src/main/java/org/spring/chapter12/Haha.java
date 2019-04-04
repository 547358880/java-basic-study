package org.spring.chapter12;

import org.junit.Test;

public class Haha 
{
	private static final String TEST;
	
	static {
		TEST = "haha";
	}
	
	public static String show()
	{
		return TEST;
	}
	
	@Test
	public void test()
	{
		System.out.println(show());
	}
}
