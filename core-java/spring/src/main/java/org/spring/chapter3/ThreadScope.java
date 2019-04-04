package org.spring.chapter3;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope
{
	protected final ThreadLocal<Map<String, Object>> thread_scope = new ThreadLocal<Map<String, Object>>() {
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) 
	{
		if(thread_scope.get().containsKey(name)) {
			return thread_scope.get().get(name);
		}
		thread_scope.get().put(name, objectFactory.getObject());
		return thread_scope.get().get(name);
	}

	@Override
	public Object remove(String name) 
	{
		return thread_scope.get().remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) 
	{
		  //此处不实现就代表类似proytotype，容器返回给用户后就不管了
	}

	@Override
	public Object resolveContextualObject(String key) 
	{
		return null;
	}

	@Override
	public String getConversationId()
	{
		return null;
	}

}
