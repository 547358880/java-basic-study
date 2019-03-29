package org.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.jdbc.utils.JdbcUtils;
import org.junit.Test;

/*
 * 通过PreparedStatement预处理对想完成CUED操作
 * 
 * 预编译相关知识:http://www.cnblogs.com/micrari/p/7112781.html
 */
public class JdbcCURDByPreparedStatement 
{
	//@Test
	public void insert()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into users(name, password, email, birthday) values(?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			/*
			 * 为sqk语句中的参数赋值, 注意索引是从1开始的
			 */
			ps.setString(1, "白虎圣皇");  //name是varchar类型的
			ps.setString(2, "123");
			ps.setString(3, "bhsh@sina.com");
			ps.setDate(4, new java.sql.Date(new Date().getTime()));
			
			int num = ps.executeUpdate();
			if (num > 0) {
				System.out.println("插入成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, ps, rs);
		}
	}
	
	//@Test
	public void delete()
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from users where id=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, 1);
			int num = st.executeUpdate();
			if (num > 0) {
				System.out.println("删除成功!");
			}
		} catch (Exception e) {
			
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	//@Test
	public void update()
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update users set name=?, email=?, where id=?";
			st = conn.prepareStatement(sql);
			st.setString(1, "gacl");
			st.setString(2, "gacl@sina.com");
			st.setInt(3, 2);
			int num = st.executeUpdate();
			if (num > 0) {
				System.out.println("更新成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	//@Test
	public void find()
	{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users where id=?";
			st = conn.prepareStatement(sql);
			st.setInt(1, 1);
			rs = st.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	@Test
	public void createTable()
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "create table if not exists haha(id int primary key, b varchar(50))engine=Innodb";
			int num = st.executeUpdate(sql);
			System.out.println(num);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}
}
