package org.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.jdbc.utils.JdbcUtils;
import org.junit.Test;


/*
 * 数据库元数据
 */
public class JdbcMetaData 
{
	@Test
	public void testBaseMetaData() throws SQLException
	{
		Connection conn = JdbcUtils.getConnection();
		DatabaseMetaData metaData = conn.getMetaData();
//		- getURL(): 返回一个String类对象， 代表数据库的URL
		System.out.println(metaData.getURL());  //jdbc:mysql://localhost:3306/jdbcStudy?characterEncoding=UTF-8&useServerPrepStmts=true&cachePrepStmts=true
//		- getUserName(): 返回连接当前数据库管理系统的用户名
		System.out.println(metaData.getUserName());
//		- getDatabaseProductName(): 返回数据库的产品名称
		System.out.println(metaData.getDatabaseProductName()); //MySQL
//		- getDatabaseProductVersion(): 返回数据库的版本号 // 5.5.5-10.1.13-MariaDB
		System.out.println(metaData.getDatabaseProductVersion());
//		- getDriverName(): 返回驱动程序的名称
		System.out.println(metaData.getDriverName());
//		- getDriverVersion(): 返回驱动程序的版本号
		System.out.println(metaData.getDriverVersion());
//		- isReadOnly: 返回一个boolean值， 指示数据库是否只允许读操作
		System.out.println(metaData.isReadOnly());
		
		// 获取数据库名称
		ResultSet rs = metaData.getCatalogs();
		while (rs.next()) {
			System.out.println("数据库名称:\t"+rs.getString(1));
		}
		
		JdbcUtils.release(conn, null, rs);
	}
	
	@Test
	public void testParameterMetaData() throws SQLException
	{
		Connection conn = JdbcUtils.getConnection();
		String sql = "select * from user where name=? and password=?";
		PreparedStatement st = conn.prepareStatement(sql);
		ParameterMetaData pm = st.getParameterMetaData();
		System.out.println(pm.getParameterCount());
//		System.out.println(pm.getParameterType(1));
		JdbcUtils.release(conn, st, null);
	}
	
	@Test
	public void testResultSetMetaData() throws SQLException
	{
		Connection conn = JdbcUtils.getConnection();
		String sql = "select * from account";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		 
		ResultSetMetaData metadata = rs.getMetaData();
		//getColumnCount() 返回resultset对象的列数
		System.out.println(metadata.getColumnCount());  //3
		//getColumnName(int column) 获得指定列的名称
		System.out.println(metadata.getColumnName(1));  //id
		 //getColumnTypeName(int column)获得指定列的类型
		System.out.println(metadata.getColumnTypeName(1));  //INT
		JdbcUtils.release(conn, st, rs);
	}
}
