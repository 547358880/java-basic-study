package chapter3;

import java.util.Objects;

import org.junit.Test;

/*
 * Java字符串常量池
 */
public class Test1 
{
	@Test
	public void test1()
	{
		String s1 = "Hello";
		String s2 = "Hello";
		String s3 = "Hel" + "lo";
		String s4 = "Hel" + new String("lo");
		String s5 = new String("Hello");
		String s6 = s5.intern();
		String s7 = "H";
		String s8 = "ello";
		String s9 = s7 + s8;
		
		System.out.println(s1);
		System.out.println(Objects.hash(s5));
		System.out.println(Objects.hash(s6));
		
		System.out.println(s1 == s2);  //true
		System.out.println(s1 == s3);  // true
		System.out.println(s1 == s4);  // false
		System.out.println(s1 == s9);  //false
		System.out.println(s1 == s5); //false
		System.out.println(s1 == s6); //true
		System.out.println(s4 == s5); //false
 	}
	
	@Test
	public void test2()
	{
		System.out.println("==================");
		//当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（用 equals(Object) 方法确定），则返回池中的字符串。否则，将此 String 对象添加到池中，并返回此 String 对象的引用
		String s = new String("1");
		s.intern();
		String s2 = "1";
		System.out.println(s == s2);
		
		String s3 = new String("1") + new String("1");
		s3.intern();
		String s4 = "11";
		System.out.println(s3 == s4);
		
	}
}
