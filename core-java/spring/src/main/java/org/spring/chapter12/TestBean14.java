package org.spring.chapter12;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TestBean14 
{
	private String message;
	
	private List<String> list;
	
	// 方法参数注入, 一个或多个参数
	@Autowired
	public void initMessage(String message, ArrayList<String> list)
	{
		this.message = message;
		this.list = list;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public List<String> getList()
	{
		return list;
	}
}
