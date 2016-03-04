package com.hosted.tables.utils;

public enum ColumnType {
	
	INT("INT"),
	VARCHAR("VARCHAR"),
	BOOLEAN("BOOEAN");
	
	private String type;
	
	ColumnType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
