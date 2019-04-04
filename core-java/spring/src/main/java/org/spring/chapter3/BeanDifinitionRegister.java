package org.spring.chapter3;

import java.util.HashMap;
import java.util.Map;

public class BeanDifinitionRegister 
{
	private final Map<String, BeanDefinition> definitions = new HashMap<>();
	
	public void registerBeanDedinition(String beanName, BeanDefinition bd)
	{
		if (definitions.containsKey(bd.getId())) {
			throw new RuntimeException("已存在Bean定义,此实现不允许覆盖");
		}
		definitions.put(bd.getId(), bd);
	}
	
	public BeanDefinition getBeanDefinition(String beanName)
	{
		return definitions.get(beanName);
	}
	
	public boolean containsBeanDefinition(String beanName)
	{
		return definitions.containsKey(beanName);
	}
}
