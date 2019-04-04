package org.jdbc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils2 
{
	private static ComboPooledDataSource ds = null;
	// 使用ThreadLocal存储当前线程中的Connection对象
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	static {
		try {
			ds = new ComboPooledDataSource("MySql");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/*
	 * 从数据源中获取连接
	 */
	public static Connection getConnection() throws SQLException
	{
		Connection conn = threadLocal.get();
		if (conn == null) {
			conn = getDataSource().getConnection();
			threadLocal.set(conn);
		}
		return conn;
	}
	
	/*
	 * 开启事务
	 */
	public static void startTransaction()
	{
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 回滚事务
	 */
	public static void rollback()
	{
		try {
			Connection conn = getConnection();
			conn.rollback();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 提交事务
	 */
	public static void commit()
	{
		try {
			Connection conn = getConnection();
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 关闭数据库
	 */
	public static void close()
	{
		try {
			Connection conn = getConnection();
			conn.close();
			threadLocal.remove();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 获取数据源
	 */
	public static DataSource getDataSource()
	{
		return ds;
	}
}
