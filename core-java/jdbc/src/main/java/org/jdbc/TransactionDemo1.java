package org.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jdbc.utils.JdbcUtils;
import org.junit.Test;

public class TransactionDemo1 
{
//	@Test
	public void testTransaction1()
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);  // 开始事务
			String sql1 = "update account set money=money-100 where name='A'";
			st = conn.prepareStatement(sql1);
			st.executeUpdate();
			st.close();
			String sql2 = "update account set money=money+100 where name='B'";
			st = conn.prepareStatement(sql2);
			
			st.executeUpdate();
			conn.commit();
			System.out.println("成功!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	/*
	 * 测试使用同一个Statement连接
	 */
	@Test
	public void testTwoStatement()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			
			st.execute("select * from users");
			rs = st.getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
			
			st.execute("select * from account");
			rs = st.getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
}
