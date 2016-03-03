package com.dbegnis.tables.tables.utils;

public class TableParameter {

	private String column;
	private String value;

	public TableParameter(String column, String value) {
		this.column = column;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setParam(String param) {
		this.column = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
