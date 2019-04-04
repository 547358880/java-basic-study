package org.spring.chapter3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.SingletonBeanRegistry;

/*
 * 通过注册表实现的单列
 */
public class SingletonBeanRegister implements SingletonBeanRegistry
{
	// 单列Bean缓存池
	private final Map<String, Object> beans = new HashMap<String, Object>();
	
	@Override
	public void registerSingleton(String beanName, Object singletonObject) 
	{
		if (beans.containsKey(beanName)) {
			throw new RuntimeException("[" + beanName + "]已存在");
		}
		beans.put(beanName, singletonObject);
	}

	@Override
	public Object getSingleton(String beanName) 
	{
		// TODO Auto-generated method stub
		return beans.get(beanName);
	}

	@Override
	public boolean containsSingleton(String beanName) 
	{
		
		return beans.containsKey(beanName);
	}

	@Override
	public String[] getSingletonNames() 
	{
		// TODO Auto-generated method stub
		return beans.keySet().toArray(new String[]{});
	}

	@Override
	public int getSingletonCount() 
	{
		return beans.size();
	}

	@Override
	public Object getSingletonMutex() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
