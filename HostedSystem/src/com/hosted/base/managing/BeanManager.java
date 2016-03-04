package com.hosted.base.managing;

import java.util.HashMap;

public class BeanManager {

	private static BeanManager beanManager;

	private HashMap<Class<?>, Object> beans;

	private BeanManager() {
		beans = new HashMap<>();
	}

	public Object get(Class<?> clazz) {
		return beans.get(clazz);
	}

	public void put(Class<?> clazz, Object value) {
		beans.put(clazz, value);
	}

	public void remove(Class<?> clazz) {
		beans.remove(clazz);
	}

	protected static BeanManager getBeanManager() {
		if (beanManager == null) {
			beanManager = new BeanManager();
		}
		return beanManager;
	}

}
