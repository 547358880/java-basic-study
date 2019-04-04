package org.jdbc.utils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;


/*
 * Java数据库连接池的实现
 */
public class JdbcPool2 implements DataSource
{
	private static LinkedList<Connection> listConnections = new LinkedList<>();
	
	static {
		InputStream in = JdbcPool2.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			// 数据库连接池的初始化连接数大小
			int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
			// 加载数据库驱动
			Class.forName(driver);
			for (int i = 0; i < jdbcPoolInitSize; i++) {
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("获取到了链接" + conn);
				listConnections.add(conn);
			}
			
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 *  获取数据库连接(non-Javadoc)
	 *  通过代理实现
	 */
	@Override
	public synchronized Connection getConnection() throws SQLException 
	{
		if (listConnections.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		final Connection conn = listConnections.removeFirst();
		System.out.println("listConnections数据库连接池大小是" + listConnections.size());
		return (Connection) Proxy.newProxyInstance(JdbcPool2.class.getClassLoader(), conn.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
					{
						if (!method.getName().equals("close")) {
							return method.invoke(conn, args);
						} else {
							listConnections.add(conn);
							System.out.println(conn + "被还给listConnections数据库连接池了！！");
					        System.out.println("listConnections数据库连接池大小为" + listConnections.size());
					        this.notifyAll();
							return null;
						}
					}
				});
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
