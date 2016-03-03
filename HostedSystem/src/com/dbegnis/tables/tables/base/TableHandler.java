package com.dbegnis.tables.tables.base;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;

public abstract class TableHandler {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TableHandler.class);

	protected final String name;

	public TableHandler(Class<?> clazz) {
		name = Constants.TABLE_PREFIX + clazz.getSimpleName();
	}

}
