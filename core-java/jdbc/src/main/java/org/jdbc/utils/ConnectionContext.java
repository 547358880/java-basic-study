package org.jdbc.utils;

import java.sql.Connection;

/*
 * 数据库连接上下文
 * 单例模式
 */
public class ConnectionContext 
{
	private ConnectionContext() {}
	
	private static ConnectionContext connectionContext = new ConnectionContext();
	
	public static ConnectionContext getInstance()
	{
		return connectionContext;
	}
	
	/*
	 * 利用ThreadLocal存储数据库连接对象
	 */
	private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
	
	/*
	 * 利用ThreadLocal把数据库连接对象Connection和当前线程绑定
	 */
	public void bind(Connection connection) 
	{
		connectionThreadLocal.set(connection);
	}
	
	/*
	 */
	public Connection getConnection()
	{
		return connectionThreadLocal.get();
	}
	
	public void remove()
	{
		connectionContext.remove();
	}
}
