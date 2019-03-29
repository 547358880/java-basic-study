package org.jdbc.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/*
 * 将结果集转换成bean对象处理器
 */
public class BeanHandler implements ResultSetHandler
{
	private Class<?> clazz;
	
	public BeanHandler(Class<?> clazz) 
	{
		this.clazz = clazz;
	}
	
	
	@Override
	public Object handler(ResultSet rs) 
	{
		try {
			if (!rs.next()) {
				return null;
			}
			
			Object bean = clazz.newInstance();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount(); //得到及结果集中有几列数据
			for (int i = 0; i < columnCount; i++) {
				String coulmnName = metaData.getColumnName(i + 1); //得到每列的列明
				Object coulmnData = rs.getObject(i + 1);
				Field f = clazz.getDeclaredField(coulmnName);
				f.setAccessible(true);
				f.set(bean, coulmnData);
			}
			
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 	
	}

}
