package com.dbegnis.tables.tables;

import com.dbegnis.base.Logger;
import com.dbegnis.tables.tables.utils.ColumnType;
import com.dbegnis.tables.tables.utils.Table;
import com.dbegnis.tables.tables.utils.TableColumn;

public class User extends Table {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(User.class);
	
	public User() {
		super(User.class);
		addColumn(new TableColumn("ID", ColumnType.INT, true, true).setAutoIncrement(true));
		addColumn(new TableColumn("name", ColumnType.VARCHAR, true, true));
		addColumn(new TableColumn("password", ColumnType.VARCHAR, true));
		addColumn(new TableColumn("rightsgroup", ColumnType.INT, false).setDefault("1"));
	}

}
