package org.jdbc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.jdbc.utils.JdbcUtils_CP30;
import org.junit.Test;

/*
 * ResultSetHandler接口的实现
 * ArrayHandler: 把结果集中的第一行数据转成对象数组
 * ArrayListHandler: 把结果集中的每一行数据都转换成一个数组，在存放咋List中
 * BeanHandler: 把结果集中的第一行数据封装到一个对应的JavaBean实例中
 * BeanListHandler: 将结果集中的每一行数据都封装到一个对应的JavaBean实例中， 存放到List里
 * ColumnListHandler: 将结果集中某一例的数据存在到List中
 * KeyedHandler(name): 将及结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里, 其key为指定的key
 * MapHandler: 将结果集中的第一行数据封装到一个Map里
 * MapListHanlder: 将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 */
public class ResultSetHandlerTest 
{
	@Test
	public void testArrayHandler() throws SQLException
	{
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users";
		Object[] result = qr.query(sql, new ArrayHandler()); 
		System.out.println(Arrays.toString(result));
	}
	
	/*
	 * 可以使用流
	 */
	@Test
	public void testArrayListHandler() throws SQLException
	{
		System.out.println("==========");
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users";
		List<Object[]> result = qr.query(sql, new ArrayListHandler());
		System.out.println(result);
		
		Stream<Object[]> steam = result.stream();
		steam.forEach((Object[] o)->{System.out.println(Arrays.toString(o));});
	}
	
	@Test
	public void testColumnListHandler() throws SQLException
	{
		System.out.println("=========");
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users";
		List<Integer> list = qr.query(sql, new ColumnListHandler<Integer>("id"));
		System.out.println(list);
	}
	
	@Test
	public void testKeyedHandler() throws Exception
	{
		System.out.println("=========");
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users";
		Map<Integer, Map<String, Object>> map = qr.query(sql, new KeyedHandler<Integer>("id"));
		for (Map.Entry<Integer, Map<String, Object>> me : map.entrySet()) {
			int id = me.getKey();
			Map<String, Object> innermap = me.getValue();
			for (Map.Entry<String, Object> innerme : innermap.entrySet()) {
				String columnName = innerme.getKey();
				Object value = innerme.getValue();
				System.out.println("id=" + id + ", columnName=" + columnName + ", value=" + value);
			}
			System.out.println("*************");
		}
	}
	
	@Test
	public void testMapHandler() throws Exception
	{
		System.out.println("=========");
		QueryRunner qr = new QueryRunner(JdbcUtils_CP30.getDataSource());
		String sql = "select * from users";
		Map<String, Object> map = qr.query(sql, new MapHandler());
		for (Map.Entry<String, Object> me : map.entrySet()) {
			System.out.println(me.getKey() + "=" + me.getValue());
		}
	}
}
