package com.dbegnis.tables.tables;

import com.dbegnis.tables.tables.utils.ColumnType;
import com.dbegnis.tables.tables.utils.Table;
import com.dbegnis.tables.tables.utils.TableColumn;

public class Group extends Table {

	public static final String ID = "id";
	public static final String NAME = "name";

	public Group() {
		super(Group.class);

		addColumn(new TableColumn(ID, ColumnType.INT, true).setAutoIncrement(true));
		addColumn(new TableColumn(NAME, ColumnType.VARCHAR, true).setMaxLength("20"));
	}

}
