package com.hosted.tables.tables;

import com.hosted.base.managing.Manager;
import com.hosted.tables.utils.ColumnType;
import com.hosted.tables.utils.Table;
import com.hosted.tables.utils.TableColumn;

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
