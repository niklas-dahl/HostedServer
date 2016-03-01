package com.dbegnis.tables;

public class Groups extends Table {

	public Groups() {
		super(Groups.class);
		createTable();
	}

	private void createTable() {
		addColumn("ID INT NOT NULL AUTO_INCREMENT");
		addColumn("NAME VARCHAR(20) NOT NULL");
		super.uploadToDB();
	}

}
