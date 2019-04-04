package org.spring.chapter3;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MoreDenpendencyInjectTest 
{
	@Test
	public void testDependOn() throws IOException
	{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chapter3/depends-on.xml");
		// 一定要初测销毁回调， 否则销毁方法不执行
		ctx.registerShutdownHook();
		DependentBean dependentBean = ctx.getBean("dependentBean", DependentBean.class);
		dependentBean.write("aaa");
	}
}
