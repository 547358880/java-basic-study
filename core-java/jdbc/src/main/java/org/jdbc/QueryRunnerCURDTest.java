package org.jdbc;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jdbc.utils.JdbcUtils_CP30;
import org.junit.Test;

/*
 * QueryRunner类的主要方法
 */
public class QueryRunnerCURDTest 
{
	//@Test
	public void add() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "insert into users(name, password, email, birthday) values(?, ?, ?, ?)";
		Object[] params = {"许静", "123456", "547358880@qq.com", new Date()};
		qr.update(sql, params);
	}
	
	//@Test
	public void delete() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "delete from users where id=?";
		qr.update(sql, 1);
	}
	
	//@Test
	public void update() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "update users set name=? where id=?";
		Object[] params = {"ddd", 5};
		qr.update(sql, params);
	}
	
	//@Test
	public void find() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users where id = ?";
		Object[] params = {39};
		User user = qr.query(sql, new BeanHandler<User>(User.class), params);
		System.out.println(user.getBirthday());
	}
	
	//@Test
	public void getAll() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users";
		List<User> list = qr.query(sql, new BeanListHandler<>(User.class));
		System.out.println(list);
	}
	
	/*
	 * 批处理
	 */
	@Test
	public void testBatch() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "insert into users(name, password, email, birthday) values(?, ?, ?, ?)";
		Object[][] params = new Object[10][];
		for (int i = 0; i < 10; i++) {
			params[i] = new Object[] {"aa" + i, "123", "aa@sina.com", new Date()};
		}
		qr.batch(sql, params);
		
	}
}
