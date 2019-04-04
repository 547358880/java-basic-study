package org.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jdbc.utils.JdbcUtils;
import org.junit.Test;


/*
 * 获取自增id
 */
public class JdbcAutoIncrement 
{
	@Test
	public void test1()
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into test1(name) values(?)";
			st = conn.prepareStatement(sql);
			st.setString(1, "aaa");
			st.executeUpdate();
			// 获取数据库自动生成的主键
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
}
