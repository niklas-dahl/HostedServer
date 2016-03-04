package com.dbegnis.tables.tables;

import com.dbegnis.base.managing.Manager;
import com.dbegnis.tables.tables.utils.ColumnType;
import com.dbegnis.tables.tables.utils.Table;
import com.dbegnis.tables.tables.utils.TableColumn;

public class UserToGroup extends Table {
	
	public static final String ID_USER = "id_user";
	public static final String ID_GROUP = "id_group";

	public UserToGroup() {
		super(UserToGroup.class);
		
		String userTableName = ((Table) Manager.getBeanManager().get(User.class)).getTableName();
		String groupTableName = ((Table) Manager.getBeanManager().get(Group.class)).getTableName();
		
		addColumn(new TableColumn(ID_USER, ColumnType.INT, true).setForeignKey(userTableName, User.ID));
		addColumn(new TableColumn(ID_GROUP, ColumnType.INT, true).setForeignKey(groupTableName, Group.ID));
		
	}
	
	 

}
