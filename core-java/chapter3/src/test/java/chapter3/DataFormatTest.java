package chapter3;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

import org.junit.Test;

/*
 * 数据格式化输出
 */
public class DataFormatTest 
{
	@Test
	public void test1()
	{
		String name = "盖伦";
		int kill = 8;
		String title = "超神";
		/*
		 * 格式化输出 %s表示字符串, %d表示数字, %n表示换行
		 */
		String sentenceFormat = "%s 在进行了连续  %d 次击杀后，获得了 %s 的称号%n";
		// 使用printf格式化输出
		System.out.printf(sentenceFormat, name, kill, title);
		// 使用format格式化输出
		System.out.format(sentenceFormat, name, kill, title);
	}
	
	@Test
	public void test2()
	{
		System.out.println("============");
		int year = 2020;
		 //总长度，左对齐，补0，千位分隔符，小数点位数，本地化表达
		System.out.format("%d%n", year);
		//总长度是8, 默认右对其
		System.out.format("%8d%n", year);
		// 左对齐
		System.out.format("%-8d%n", year);
		// 总长度是8, 不够补0
		System.out.format("%08d%n", year);
		// 千未分隔符
		System.out.format("%,8d%n", year);
		// 小数点位数
		System.out.format("%.2f%n", Math.PI);
		System.out.format("%10.2f%n", 33333.33);
		System.out.format("%2.2f%n", 33333.33);
		System.out.println("============");
	}
	
	@Test
	public void test3()
	{
		System.out.println("=====test3=====");
		
		System.out.format("%tc", new Date());
		
		System.out.println(Clock.systemDefaultZone());
		
		System.out.format("%1$s %2$tB", "Due date", new Date());
		
		System.out.println();
		
		System.out.println(Instant.now().getEpochSecond());
		
		System.out.println(new Date().getTime());
		
		System.out.println();
		
		System.out.println("============");
	}
}
