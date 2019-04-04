package org.jdbc.spring;

import org.jdbc.spring.dao.IUserDao;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateTest1 
{
	@Test
	public void testBestPractive()
	{
		String[] configLocation = new String[] {
			"classpath:applicationContext-resources.xml"
		};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
		IUserDao userDao = ctx.getBean(IUserDao.class);
		UserModel model = new UserModel();
		model.setMyName("test");
		userDao.save(model);
		Assert.assertEquals(1, userDao.countAll());
	}
}
