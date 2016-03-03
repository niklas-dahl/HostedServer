package com.dbegnis.tables.tables;

import com.dbegnis.base.managing.Manager;
import com.dbegnis.tables.tables.base.ColumnType;
import com.dbegnis.tables.tables.base.Table;
import com.dbegnis.tables.tables.base.TableColumn;

public class UsersToUser extends Table {

	public UsersToUser() {
		super(UsersToUser.class);

		String userTableName = ((Table) Manager.getBeanManager().get(User.class)).getTableName();

		addColumn(new TableColumn("ID_U1", ColumnType.INT, true).setForeignKey(userTableName, "ID"));
		addColumn(new TableColumn("ID_U2", ColumnType.INT, true).setForeignKey(userTableName, "ID"));

	}

}
