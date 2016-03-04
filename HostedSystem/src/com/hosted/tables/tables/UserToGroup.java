package com.hosted.tables.tables;

import com.hosted.base.managing.Manager;
import com.hosted.tables.utils.ColumnType;
import com.hosted.tables.utils.Table;
import com.hosted.tables.utils.TableColumn;

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
