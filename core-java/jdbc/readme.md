### JDBC的学习

#### 相关概练
##### 数据库URL讲解  
URL用于表示数据库的位置, 通过URL地址告诉JDBC程序要连接哪个数据库,URL写法如:  
![](data:image/png;base64,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)  

常用数据库URL地址的写法：  
- Oracle写法: jdbc:oracle:thin:@localhost:1521:sid
- SqlServer写法: jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=sid
- Mysql写法: jdbc:mysql://localhost:3306/sid

##### Connection类讲解
Jdbc程序中的Connection, 它用于代表数据库的连接，常用的方法有:
- createStatament(): 创建像数据库发送sql的Statement对象
- prepareStatement(sql): 创建想数据库发送预编译sql的PrepareStatement对象
- prepareCall(sql): 创建执行存储过程的callableStatement对象
- setAutoCommit(boolean autoCommit): 设置事务是否提交, 默认是开启的
- commit(): 提交事务
- rollback: 回滚事务

##### Statement类讲解
Statement对象用于想数据库发送Sql语句,常用方法与:
- executeQuery(String sql): 用于向数据库发送查询语句
- executeUpdate(String sql): 用于向数据库发送insert, update或delete
- execute(String sql): 用于向数据库发送任意sql语句
- addBatch(String sql): 把多条语句放到一个批处理中
- executeBatch(): 向数据库发送一批sql语句执行  

**execute和executeUpdate的区别：**
1. execute可以执行查询语句, 然后通过getResultSet把结果集取出来 , executeUpdate不能执行查询语句 
2. execute返回boolean类型， true表示执行的是查询语句， false表示是其他l executeUpdate返回的是int, 表示有多少条数据收到了影响

#### Resultset类
ResultSet用于代表sql语句的执行结果
获取任意类型的数据  
&nbsp;&nbsp;&nbsp;&nbsp;getObject(int index)    
&nbsp;&nbsp;&nbsp;&nbsp;getObject(String columnName)  
获取执行类型数据:  
&nbsp;&nbsp;&nbsp;&nbsp;getString(int index)    
&nbsp;&nbsp;&nbsp;&nbsp;getString(String columnName)  
ResultSet还提供了对结果集进行滚动的方法:
- next(): 移动到下一行
- previous(): 移动到前一行
- absolute(int row): 移动到指定行
- beforeFirst(): 移动resultSet到最强面
- afterLast: 移动到最后面


#### 元数据
元数据指的是"数据库", "表", "列"的定义信息

##### DataBaseMetaData元数据
数据库层面的元数据(conn.getMetaData())
- getURL(): 返回一个String类对象， 代表数据库的URL
- getUserName(): 返回连接当前数据库管理系统的用户名
- getDatabaseProductName(): 返回数据库的产品名称
- getDatabaseProductVersion(): 返回数据库的版本号
- getDriverName(): 返回驱动程序的名称
- getDriverVersion(): 返回驱动程序的版本号
- isReadOnly: 返回一个boolean值， 指示数据库是否只允许读操作

##### ParameterMetaData元数据
获得代表PreparedStatement元数据的ParameterMetaData随想
- getParameterCount(): 获取指定参数的个数
- getParameterType(int param): 获得指定参数的sql类型,MYSQL数据库驱动不支持

##### ResultSetMetaData元数据
获得代表ResultSet对象元数据的ResultSetMetaData对象
- getColumnCount() 返回resultset对象的列数
- getColumnName(int column) 获得指定列的名称
- getColumnTypeName(int column)获得指定类的类型

#### Spring Jdbc
- support包: 提供将JDBC异常转换为DAO非检查异常转换类,一些工具类如JdbcUtils等
- datasource报: 提供简化访问JDBC数据源(javax.sql.DataSource实现)工具类， 并提供了一些DataSource简单实现类从而使这些DataSource获取的连接能自动得到Spring管理事务支持
- core包：提供JDBC模块类实现及可变部分的回调接口
- object包: 提供关系数据库的对象表现形式

##### JdbcTemplate主要提供以下5类方法
- execute方法: 用于执行任何SQL语句, 一般执行DDL语句;
- update方法以及batchUpdate: update方法用于执行新增， 修改， 删除等语句; batchUpdate方法用于执行批处理相关语句;
- query和queryForXXX方法: 用于执行查询相关语句;
- call方法: 用于执行存储过程， 函数相关语句;

##### JdbcTemplate类支持的回调:

###### 预编译语句及存储过程创建回调:
- **PreparedStatementCreator:** 通过回调获取JdbcTemplate提供的Connection,由用户使用该Connection创建相关的PreparedStatement;
- **CallableStatementCreator:** 通过回调获取JdbcTemplate提供的Connectionm 由用户使用该Connection创建相关的CallableStatement;  

###### 预编译语句没值回调:用于给预编译语句相应参数设值
- **PreaoredStatementSetter:** 通过回调获取JdbcTemplate提供的PreparedStatement, 由用户来对相应的预编译语句相应参数设值;
- **BatchPreparedStatementSetter:** 批处理相关参数设值

###### 自定义回调:
- **ConnectionCallback:** 通过回调获取JdbcTemplate提供的Connection, 用户可在该Connection执行任何数量的操作;
- **StatementCallback:** 通过回调获取JdbcTemplate提供的Statement, 用户可以在该Statement执行任何数量的操作;
- **PreparedStatementCallback:** 通过回调获取JdbcTemplate提供的PreparedStatement
- **CallableStatementCallback:** 通过回调获取CallableStatement

###### 结果处理回调:通过回调处理ResultSet或将ResultSet转换为需要的形式
- **RowMapper:**用户将结果集每行数据转换为需要的类型， 用户需实现方法mapRow(ResultSet rs, int rowNum)来完成将每行数据转换为相应的类型
- **RowCallbackHandler:** 用于处理ResultSet的每一行数据
- **ResultSetExtractor:** 用于数据集提取，用户需要实现extractData(ResultSet rs)来出来结果集， 用户必须出来整个结果集

##### SimpleJdbc方式
1. **SimpleJdbcInsert:** 用户插入数据， 根据数据库元数据进行插入数据， 本类简化插入操作， 提供了三种类型方法:  
	- execute普通插入  
	- executeAndReturnKey及executeAndReturnHolder方法用于插入时获取主键值
	- executeBatch用于批处理

###### 类型参数
**不能实例话类型变量像 new T(...), new T[], T.class等**
**不能构造泛型数组**	
**泛型类的静态上下文中类型变量无效**
	
##### 控制数据库连接
SpringJDBC通过DataSource控制数据库连接, 即通过DataSource实现获取数据库连接
- DriverManagerDataSource: 简单封装了DriverManager获取数据库连接; 通过DriverManager的getConnection方法获取数据库连接;
- SingleConnectionDataSource: 内部封装了一个连接,该连接使用后不会关闭
- LazyConnectionDataSourceProxy: 包装了一个DataSource, 用于延迟获取数据库连接吗，只有在创建Statement时才获取连接


	
