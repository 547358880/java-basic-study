package org.jdbc;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jdbc.utils.JdbcUtils;
import org.junit.Test;

public class JdbcCRUDByStatement 
{
//	@Test
	public void insert()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "insert into users(name, password, email, birthday) values ('白虎神皇', '123', 'bhsh@sina.com', '1989-09-09')";
			int num = st.executeUpdate(sql);
			if (num > 0) {
				System.out.println("插入成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	@Test
	public void update()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "update users set name='白虎神皇' where id=34";
			int num = st.executeUpdate(sql);
			System.out.println(num);
			if (num > 0) {
				System.out.println("插入成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	@Test
	public void find()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * form users where id=3";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
}
