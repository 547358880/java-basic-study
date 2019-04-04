package org.jdbc.spring.dao.jdbc;

import org.jdbc.spring.UserModel;
import org.jdbc.spring.dao.IUserDao;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class UserJdbcDaoImpl extends NamedParameterJdbcDaoSupport implements IUserDao 
{
	private static final String INSERT_SQL = "insert into test1(name) values (:myName)";
	private static final String COUNT_ALL_SQL = "select count(*) from test1";
	
	@Override
	public void save(UserModel model) 
	{
		getNamedParameterJdbcTemplate().update(INSERT_SQL, new BeanPropertySqlParameterSource(model));
	}

	@Override
	public int countAll() 
	{
		return (int) getNamedParameterJdbcTemplate().queryForObject(COUNT_ALL_SQL, new EmptySqlParameterSource(), Integer.class);
	}

}
