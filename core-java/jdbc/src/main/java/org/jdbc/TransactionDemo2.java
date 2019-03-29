package org.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import org.jdbc.utils.JdbcUtils;
import org.junit.Test;

/*
 * 设置事务回滚点
 */
public class TransactionDemo2 
{
	@Test
	public void test1()
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Savepoint sp = null;
		
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);
			
			String sql1 = "update account set money=money-100 where name='A'";
			st = conn.prepareStatement(sql1);
			st.executeUpdate();
			
			// 设置事务回滚点
			sp = conn.setSavepoint();
			
			String sql2 = "update account set money=money+100 where name='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			
			int x = 1 / 0;
			String sql3 = "update account set money=money+100 where name='C'";
			st = conn.prepareStatement(sql3);
			st.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback(sp);
				// 回滚之后要进行事务提交
				conn.commit();
			} catch (Exception el) {
				
			}
			
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
}
