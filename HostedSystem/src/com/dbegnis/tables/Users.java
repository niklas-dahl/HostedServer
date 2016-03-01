package com.dbegnis.tables;

import org.h2.engine.User;

import com.dbegnis.base.Logger;

public class Users extends Table {
	
	private static final Logger log = Logger.getLogger(User.class);

	public Users() {
		super(Users.class);
		addColumn("ID INT NOT NULL AUTO_INCREMENT");
		addColumn("NAME VARCHAR(20) NOT NULL UNIQUE");
		addColumn("PASSWORD VARCHAR(20) NOT NULL");
		addColumn("RIGHTS_GROUP INT DEFAULT 1");
		uploadToDB();
	}
	
	

}
