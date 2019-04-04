package org.spring.chapter3;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.Bean;

public class DefaultBeanFactory 
{
	// Bean注册表
	private BeanDifinitionRegister definitions = new BeanDifinitionRegister();
	
	// 单例注册表
	private final SingletonBeanRegistry singletons = new SingletonBeanRegister();
	
	public Object getBean(String beanName)
	{
		// 1.验证bean定义是存在
		if (!definitions.containsBeanDefinition(beanName)) {
			throw new RuntimeException("不存在[" + beanName + "]Bean定义");  
		}
		
		// 2.获取Bean定义
		BeanDefinition bd = definitions.getBeanDefinition(beanName);
		// 3.是否该bean定义是单例作用域
		if (bd.getScope() == BeanDefinition.SCOPE_SINGLETON) {
			if (singletons.containsSingleton(beanName)) {
				return singletons.getSingleton(beanName);
			}
			singletons.registerSingleton(beanName, createBean(bd));
			return singletons.getSingleton(beanName);
		}
		
		if (bd.getScope() == BeanDefinition.SCOPE_PROTOTYPE) {
			return createBean(bd);
		}
		
		throw new RuntimeException("错误的bean定义");
	}
	
	public void registerBeanDefinition(BeanDefinition bd)
	{
		definitions.registerBeanDedinition(bd.getId(), bd);
	}
	
	private Object createBean(BeanDefinition bd)
	{
		try {
			Class<?> clazz = Class.forName(bd.getClazz());
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
