package chapter3;

import java.util.Properties;

import org.junit.Test;

/*
 * 数值转换
 */
public class DataTransformTest 
{
	@Test
	public void test1()
	{
		byte a = -10;
		System.out.println(Integer.toBinaryString(a));
		
		System.out.println(Integer.toHexString(a));
		
		System.out.println(System.getProperty("file.encoding")); //获取本机字符编码
		
		Properties pro = System.getProperties();
		pro.list(System.err);
	}
}
