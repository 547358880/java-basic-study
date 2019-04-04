package ch0206.localdates;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

/*
 * 本地时间
 */
public class LocalDates 
{
	@Test
	public void test()
	{
		LocalDate today = LocalDate.now();	//Today's date
		System.out.println("today: " + today);
		
		LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
		alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
		System.out.println("alonzosBirthday: " + alonzosBirthday);
		
		LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
		System.out.println("programmersDay: " + programmersDay);
		
		LocalDate independenceDay = LocalDate.of(2018, Month.JULY, 4);
		LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);
		
		System.out.println(independenceDay.until(christmas));
	}
}
