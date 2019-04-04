package org.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Test1 
{
	static final int SIZE = 100;
	static Generic<Integer>[] gia; //可以声明泛型数组
	
	@Test
	@SuppressWarnings("unchecked")
	public void test()
	{
		//! gia = new Generic<Generic>[SIZE];
		gia = (Generic<Integer>[]) new Generic[SIZE];
		System.out.println(gia.getClass().getName());
		
		gia[0] = new Generic<Integer>();
		Generic<Integer> generic = gia[0];
		
//		gia[1] = new Generic<String>();
	}
	
	@Test 
	public void test1()
	{
		// 下面代码是不允许de
//		List<String>[] lsa = new List<String>[10];
//		// 向上转型
//		Object[] oa = (Object[]) lsa;
//		List<Integer> li = new ArrayList<>();
//		li.add(new Integer(3));
//		oa[1] = li;
//		String s = lsa[1].get(0);  // 如果代码合理这里可定会发生异常
	}
}
