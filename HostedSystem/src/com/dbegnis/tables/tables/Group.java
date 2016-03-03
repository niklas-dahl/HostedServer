package com.dbegnis.tables.tables;

import com.dbegnis.tables.tables.base.ColumnType;
import com.dbegnis.tables.tables.base.Table;
import com.dbegnis.tables.tables.base.TableColumn;

public class Group extends Table {

	public Group() {
		super(Group.class);
		
		addColumn(new TableColumn("ID", ColumnType.INT, true).setAutoIncrement(true));
		addColumn(new TableColumn("NAME", ColumnType.VARCHAR, true).setMaxLength("20"));
	}

}
