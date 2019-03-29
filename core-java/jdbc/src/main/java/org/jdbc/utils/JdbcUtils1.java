package org.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils1 
{
	private static JdbcPool pool = new JdbcPool();
	
	/*
	 * 从数据库中获取连接对象
	 */
	public static Connection getConnection() throws SQLException
	{
		return pool.getConnection();
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
