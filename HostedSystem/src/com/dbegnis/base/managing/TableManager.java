package com.dbegnis.base.managing;

import java.util.HashMap;

import com.dbegnis.base.Constants;
import com.dbegnis.tables.Table;

public class TableManager {

	private static TableManager beanManager;

	private HashMap<String, Table> beans;

	private TableManager() {
		beans = new HashMap<>();
	}

	public Table get(Class<?> clazz) {
		return beans.get(Constants.TABLE_PREFIX + clazz.getSimpleName());
	}

	public void put(Class<?> clazz, Table value) {
		beans.put(Constants.TABLE_PREFIX + clazz.getSimpleName(), value);
	}

	public void remove(Class<?> clazz) {
		beans.remove(Constants.TABLE_PREFIX + clazz.getSimpleName());
	}

	protected static TableManager getTableManager() {
		if (beanManager == null) {
			beanManager = new TableManager();
		}
		return beanManager;
	}

}
