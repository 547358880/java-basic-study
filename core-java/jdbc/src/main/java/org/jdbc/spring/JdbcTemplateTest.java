package org.jdbc.spring;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.model.FrameworkMember;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/*
 * DDL(Data Definition Languages): 数据定义语句，这些语句定义了不同数据段， 数据库， 表， 列， 索引等数据库对象， 常用的语句关键字有create, drop, alter等
 * DML(Data Mainipulation Language): 数据操作语句， 用于添加， 删除， 更新和查询数据库记录， 并检查数据完整性， 常用的关键字包括insert, delete, update和select
 */
public class JdbcTemplateTest 
{
	private static JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void setUpClass()
	{
		String url = "jdbc:mysql://localhost:3306/mybaits?characterEncoding=UTF-8";
		String username = "root";
		String password = "";
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	@Test
	public void test()
	{
		String sql = "select * from information_schema.tables";
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				//2. 处理结果集
				String value = rs.getString("table_name");
				System.out.println("Column tablename:" + value);
			}
		});
	}
	
	/*
	 * @Before和@After分别表示在测试方法之前和之后执行
	 */
	@Before
	public void setUp()
	{
		String createTablesSql = "create table test(id int auto_increment primary key, name varchar(100)) engine=Innodb charset=UTF8";
		jdbcTemplate.update(createTablesSql);
		
//		String createHsqlProcedureSql =
//				"create procedure procedure_test" + 
//				"(inout inOutName varchar(100), out outId int)" + 
//				"begin" +
//				"insert into test(name) values (inOutName); " + 
//				"set outId = identity(); " +
//				"set inOutName = 'haha'; " + 
//				"end";
		
		String createHsqlProcedureSql =  
			      "CREATE PROCEDURE PROCEDURE_TEST" +  
			      "(INOUT inOutName VARCHAR(100), OUT outId INT) " +  
			      "BEGIN " +  
			      "  insert into test(name) values (inOutName); " +  
			      "  SET outId = 1; " +  
			      "  SET inOutName = concat('Hello, ', inOutName); " +  
			    "END"; 
		jdbcTemplate.execute(createHsqlProcedureSql);
	}
	
	@After
	public void tearDown()
	{
		String dropTableSql = "drop table test";
		jdbcTemplate.execute(dropTableSql);
		
		jdbcTemplate.execute("drop procedure procedure_test");
	}
	
	@Test
	public void testCURD()
	{
		insert();
		delete();
		update();
		select();
	}
	
	private void insert()
	{
		jdbcTemplate.update("insert into test(name) values ('name1')");
		jdbcTemplate.update("insert into test(name) values ('name2')");
		Assert.assertEquals(2, (int) jdbcTemplate.queryForObject("select count(*) from test", Integer.class));
	}
	
	private void delete()
	{
		System.out.println(int.class == Integer.TYPE);
		jdbcTemplate.update("delete from test where name=?", new Object[] {"name2"});
		Assert.assertEquals(1, (int) jdbcTemplate.queryForObject("select count(*) from test", int.class));
		
	}
	
	private void update()
	{
		jdbcTemplate.update("update test set name='name3' where name=?", new Object[] {"name1"});
		Assert.assertEquals(1, (int) jdbcTemplate.queryForObject("select count(*) from test where name='name3'", int.class));
	}
	
	private void select()
	{
		jdbcTemplate.query("select * from test", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.print("=====id:" + rs.getInt("id"));
				System.out.print(", name:" + rs.getString("name"));
			}
		});
	}
	
	/*
	 * 预编译语句创建回调，自定义功能回调的使用
	 */
	@Test
	public void testPreparedStatement1()
	{
		int count = jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("select count(*) from test");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.execute();
				ResultSet rs = ps.getResultSet();
				rs.next();
				return rs.getInt(1);
			}
		});
		
		Assert.assertEquals(0, count);
	}
	
	/*
	 * 预编译语句没值回调
	 */
	@Test
	public void testPreparedStatament2()
	{
		String insertSql = "insert into test(name) values(?)";
		int count = jdbcTemplate.update(insertSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setObject(1, "name4");
			}
		});
		Assert.assertEquals(1, count);
		String deleteSql = "delete from test where name=?";
		count = jdbcTemplate.update(deleteSql, new Object[] {"name4"});
		Assert.assertEquals(1, count);
	}
	
	/*
	 * 结果集处理回调
	 */
	@Test
	public void testResultSet1()
	{
		jdbcTemplate.update("insert into test(name) values('name5')");
		String listSql = "select * from test";
		List<Map<Integer, String>> result = jdbcTemplate.query(listSql, new RowMapper<Map<Integer, String>>() {
			@Override
			public Map<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<Integer, String> row = new HashMap<>();
				row.put(rs.getInt("id"), rs.getString("name"));
				return row;
			}
		});
		System.out.println(result);
		Assert.assertEquals(1, result.size());
		jdbcTemplate.update("delete from test where name='name5'");
	}
	
	@Test
	public void testResultSet2()
	{
		jdbcTemplate.update("insert into test(name) values('name5')");
		String listSql = "select * from test";
		final List<Map<Integer, String>> result = new ArrayList<>();
		jdbcTemplate.query(listSql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Map<Integer, String> row = new HashMap<>();
				row.put(rs.getInt("id"), rs.getString("name"));
				result.add(row);
			}
		});
		Assert.assertEquals(1, result.size());
		jdbcTemplate.update("delete from test where name='name5'");
	}
	
	@Test
	public void testResultSet3()
	{
		jdbcTemplate.update("insert into test(name) values('name5')");
		String listSql = "select * from test";
		List<Map<Integer, String>> result = jdbcTemplate.query(listSql, new ResultSetExtractor<List<Map<Integer, String>>>() {
			@Override
			public List<Map<Integer, String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Map<Integer, String>> result = new ArrayList<>();
				while (rs.next()) {
					Map<Integer, String> row = new HashMap<>();
					row.put(rs.getInt("id"), rs.getString("name"));
					result.add(row);
				}
				return result;
			}
		});
		Assert.assertEquals(1, result.size());
		jdbcTemplate.update("delete from test where name='name5'");
	}
	
//	@Test
	public void testCallableStatementCreator1()
	{
		final String callProcedureSql = "{call PROCEDURE_TEST(?, ?)}";  
	    List<SqlParameter> params = new ArrayList<SqlParameter>();  
	    params.add(new SqlInOutParameter("inOutName", Types.VARCHAR));  
	    params.add(new SqlOutParameter("outId", Types.INTEGER));  
	    Map<String, Object> outValues = jdbcTemplate.call(  
	      new CallableStatementCreator() {  
	        @Override  
	        public CallableStatement createCallableStatement(Connection conn) throws SQLException {  
	          CallableStatement cstmt = conn.prepareCall(callProcedureSql);  
	          cstmt.registerOutParameter(1, Types.VARCHAR);  
	          cstmt.registerOutParameter(2, Types.INTEGER);  
	          cstmt.setString(1, "test");  
	          return cstmt;  
	    }}, params);  
	    Assert.assertEquals("Hello, test", outValues.get("inOutName"));  
	    Assert.assertEquals(1, outValues.get("outId"));  
	}
	
	@Test
	public void testNamedParameterJdbcTemplate1()
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String insertSql = "insert into test(name) values(:name)";
		String selectSql = "select * from test where name=:name";
		String deleteSql = "delete from test where name=:name";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", "name5");
		namedParameterJdbcTemplate.update(insertSql, paramMap);
		final List<Integer> result = new ArrayList<>();
		namedParameterJdbcTemplate.query(selectSql, paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(rs.getInt("id"));
			}
		});
		Assert.assertEquals(1, result.size());
		SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
		namedParameterJdbcTemplate.update(deleteSql, parameterSource);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2()
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		UserModel model = new UserModel();
		model.setMyName("name5");
		String insertSql = "insert into test(name) values(:myName)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(model);
		namedParameterJdbcTemplate.update(insertSql, paramSource);
	}
	
	@Test
	public void testSimpleJdbcInsert()
	{
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("test");
		Map<String, Object> args = new HashMap<>();
		args.put("name", "name5");
		insert.compile();
		
		// 1.普通插入
		insert.execute(args);
		Assert.assertEquals(1, (int) jdbcTemplate.queryForObject("select count(*) from test", int.class));
		
		//2.插入时获取主键值
		insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("test");
		insert.setGeneratedKeyName("id");
		Number id = insert.executeAndReturnKey(args);
	//	Assert.assertEquals(2, id);
		
		//3. 批处理
		insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("test");
		insert.setGeneratedKeyName("id");
		int[] updateCount = insert.executeBatch(new Map[] {args, args, args});
		
		/*
		 * 1.不能实例话类型变量
		 * 像 new T(...), new T[], T.class等
		 * 见Test1.java
		 */
//		List<String>[] ls = new ArrayList<String>[10];	// 不支持
		Assert.assertEquals(1, updateCount[0]);
		Assert.assertEquals(5, (int) jdbcTemplate.queryForObject("select count(*) from test", int.class));
	}
	
	/*
	 * JdbcTemplate获取自动生成的主键
	 */
	@Test
	public void testFetchKey1()
	{
		System.out.println("=========");
		final String insertSql = "insert into test(name) values('name5')";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(insertSql, new String[] {"id"});
			}
		}, generatedKeyHolder);
		
		System.out.println(generatedKeyHolder.getKey());
		System.out.println("=========");
	}
	
	/*
	 * SqlUpdate获取自动生成主键
	 */
	@Test
	public void testFetchKey2()
	{
		final String insertSql = "insert into test(name) values ('name5')";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlUpdate update = new SqlUpdate();
		update.setJdbcTemplate(jdbcTemplate);
		update.setReturnGeneratedKeys(true);
		update.setSql(insertSql);
		update.update(null, generatedKeyHolder);
		Assert.assertEquals(1, generatedKeyHolder.getKey());
	}
	
	/*
	 * JdbcTemplate批处理
	 */
	@Test
	public void testBatchUpdate2()
	{
		String insertSql = "insert into test(name) values(?)";
		final String[] batchValues = new String[] {"name5", "name6"};
		jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, batchValues[i]);
			}
			
			@Override
			public int getBatchSize() {
				return batchValues.length;
			}
		});
		
		Assert.assertEquals(2, (int) jdbcTemplate.queryForObject("select count(*) from test", int.class));
	}
	
	/*
	 * NamedParameterJdbcTemplate批处理
	 */
	@Test
	public void testBatchUpdate3()
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String insertSql = "insert into test(name) values(:myName)";
		UserModel model = new UserModel();
		model.setMyName("name5");
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(new Object[] {model, model});
		namedParameterJdbcTemplate.batchUpdate(insertSql, params);
		Assert.assertEquals(2, (int) jdbcTemplate.queryForObject("select count(*) from test", int.class));
	}
	
	/*
	 * SimpleJdbcInsert批处理
	 */
	@Test
	public void testBatchUpdate4()
	{
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("test");
		Map<String, Object> valueMap = new HashMap<>();
		valueMap.put("name", "name5");
		insert.executeBatch(new Map[] {valueMap, valueMap});
		Assert.assertEquals(2, (int) jdbcTemplate.queryForObject("select count(*) from test", int.class));
	}
}
