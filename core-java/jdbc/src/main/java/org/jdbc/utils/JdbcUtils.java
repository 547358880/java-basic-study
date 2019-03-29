package org.jdbc.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils 
{
	private static String driver = null;
	
	private static String url = null;
	
	private static String username = null;
	
	private static String password = null;
	
	static {
		try {
			// 读取db.properties文件中的数据库连接信息
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			// 获取数据库连接
			driver = prop.getProperty("driver");
			
			// 获取数据库连接URL地址
			url = prop.getProperty("url");
			
			// 获取数据库连接用户名
			username = prop.getProperty("username");
			
			// 获取数据库连接密码
			password = prop.getProperty("password");
			
			// 加载数据里驱动
			Class.forName(driver);
			
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/*
	 * 获取数据库连接
	 */
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
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
	
	/*
	 * 万能更新
	 */
	public static void update(String sql, Object[] params) throws SQLException
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i + 1, params[i]);
			}
			st.executeUpdate();
		} finally {
			release(conn, st, rs);
		}
	}
	
	/*
	 * 完成查询
	 */
	public static Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				st.setObject(i+1, params[i]);
			}
			rs = st.executeQuery();
			/*
			 * 对于返回的结果集处理使用策略模式
			 */
			return rsh.handler(rs);
		} finally {
			release(conn, st, rs);
		}
	}
}
