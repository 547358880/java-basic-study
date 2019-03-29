package org.jdbc.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/*
 * 将结果集转换成bean对象的list集合处理器
 */
public class BeanListHandler implements ResultSetHandler
{
	private Class<?> clazz;
	
	public BeanListHandler(Class<?> clazz)
	{
		this.clazz = clazz;
	}
	
	@Override
	public Object handler(ResultSet rs) 
	{
		try {
			List<Object> list = new ArrayList<>();
			while(rs.next()){
                Object bean = clazz.newInstance();
                
                ResultSetMetaData  metadata = rs.getMetaData();
                int count = metadata.getColumnCount();
                for(int i=0;i<count;i++){
                    String name = metadata.getColumnName(i+1);
                    Object value = rs.getObject(name);
                    
                    Field f = bean.getClass().getDeclaredField(name);
                    f.setAccessible(true);
                    f.set(bean, value);
                }
                list.add(bean);
            }
			
            return list.size()>0?list:null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

	
