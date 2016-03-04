package com.dbegnis.tables.tables;

import com.dbegnis.base.managing.Manager;
import com.dbegnis.tables.tables.utils.ColumnType;
import com.dbegnis.tables.tables.utils.Table;
import com.dbegnis.tables.tables.utils.TableColumn;

public class UsersToUser extends Table {

	public static final String ID_U1 = "id_u1";
	public static final String ID_U2 = "id_u2";

	public UsersToUser() {
		super(UsersToUser.class);

		String userTableName = ((Table) Manager.getBeanManager().get(User.class)).getTableName();

		addColumn(new TableColumn(ID_U1, ColumnType.INT, true).setForeignKey(userTableName, User.ID));
		addColumn(new TableColumn(ID_U2, ColumnType.INT, true).setForeignKey(userTableName, User.ID));

	}

}
