package org.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * CP30数据库连接工具类
 */
public class JdbcUtils_CP30 
{
	private static ComboPooledDataSource ds = null;
	
	// 在静态代码块中创建数据库连接池
	static {
		try {
			ds = new ComboPooledDataSource("MySql");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DataSource getDataSource()
	{
		return ds;
	}
	
	/*
	 * 从数据源中获取数据库连接
	 */
	public static Connection getConnection() throws SQLException
	{
		return ds.getConnection();
	}
	
	/*
	 * 释放资源
	 */
	public static void release(Connection conn, Statement st, ResultSet rs)
	{
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			st = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
