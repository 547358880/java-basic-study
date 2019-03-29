package org.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class JdbcFirstDemo 
{
	@Test
	public void test1()
	{
		//要连接数据库的URL
		String url = "jdbc:mysql://localhost:3306/jdbcStudy?characterEncoding=UTF-8";
		String user = "root";
		String password = "";
		Connection conn = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println(conn.getAutoCommit());
			
			Statement st = conn.createStatement();
			
			String sql = "insert into users(name, password, email, birthday) values";
			
			for (int i = 0; i < 10; i++) {
				sql += "('name" + i + "', '123456', '547358880@qq.com', '1987-07-12')";
				if (i < 9) {
					sql += ",";
				}
			}
			
			System.out.println(sql);
			
			int result = st.executeUpdate(sql);
			
			System.out.println(result);
			
			
			System.out.println("连接成功");
			
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test2()
	{
		String url = "jdbc:mysql://localhost:3306/jdbcStudy?characterEncoding=UTF-8";
		String user = "root";
		String password = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//1. 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获取与数据库的连接
			conn = DriverManager.getConnection(url, user, password);
			// 3.获取用于向数据库发送sql语句的statement
			st = conn.createStatement(); 
			
			String sql = "select id, name, password, email, birthday from users";
			
			// 4.向数据库发送sql, 并获取结果集
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.print("[");
				System.out.print("id = " + rs.getObject("id"));
				System.out.print(" ,name = " + rs.getObject("name"));
				System.out.print(" ,password = " + rs.getObject("password"));
				System.out.print(" ,email = " + rs.getObject("email"));
				System.out.print(" ,birthday = " + rs.getObject("birthday"));
				System.out.print("]");
				System.out.println();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
}
