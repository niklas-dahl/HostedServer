package com.hosted.tables.tables;

import com.hosted.tables.utils.ColumnType;
import com.hosted.tables.utils.Table;
import com.hosted.tables.utils.TableColumn;

public class Group extends Table {

	public static final String ID = "id";
	public static final String NAME = "name";

	public Group() {
		super(Group.class);

		addColumn(new TableColumn(ID, ColumnType.INT, true).setAutoIncrement(true));
		addColumn(new TableColumn(NAME, ColumnType.VARCHAR, true).setMaxLength("20"));
	}

}
