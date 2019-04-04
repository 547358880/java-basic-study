package org.spring.chapter3;

import java.io.IOException;

public class DependentBean 
{
	ResourceBean resourceBean;
	
	public void write(String ss) throws IOException
	{
		System.out.println("DependentBean: ==========写资源");
		resourceBean.getFos().write(ss.getBytes());
	}
	
	// 初始化
	public void init() throws IOException
	{
		System.out.println("DependentBean: =========初始化");
		resourceBean.getFos().write("DependentBean: =========初始化=========".getBytes());
	}
	
	// 销毁
	public void destory() throws IOException
	{
		System.out.println("DenependentBean: ==========销毁");
		resourceBean.getFos().write("DependentBean: ========销毁========".getBytes());
	}
	
	public void setResourceBean(ResourceBean resourceBean)
	{
		this.resourceBean = resourceBean;
	}
}
