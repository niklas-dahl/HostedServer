package com.hosted.tables.tables;

import com.hosted.tables.utils.ColumnType;
import com.hosted.tables.utils.Table;
import com.hosted.tables.utils.TableColumn;

public class User extends Table {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String RIGHTSGROUP = "rightsgroup";

	public User() {
		super(User.class);
		addColumn(new TableColumn(ID, ColumnType.INT, true, true).setAutoIncrement(true));
		addColumn(new TableColumn(NAME, ColumnType.VARCHAR, true, true));
		addColumn(new TableColumn(PASSWORD, ColumnType.VARCHAR, true));
		addColumn(new TableColumn(RIGHTSGROUP, ColumnType.INT, false).setDefault("1"));
	}

}
