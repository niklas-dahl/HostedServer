package com.dbegnis.tables;

import com.dbegnis.base.managing.Manager;

public class UsersToUsers extends Table {

	public UsersToUsers() {
		super(UsersToUsers.class);

		String userTableName = Manager.getTableManager().get(Users.class).getTableName();

		addColumn("ID_U1 INT NOT NULL");
		addColumn("ID_U2 INT NOT NULL");
		addColumn("FOREIGN KEY(ID_U1) REFERENCES " + userTableName + "(ID)");
		addColumn("FOREIGN KEY(ID_U2) REFERENCES " + userTableName + "(ID)");
		uploadToDB();
	}

}
