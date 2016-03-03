package com.dbegnis.tables.tables.utils;

import java.util.LinkedList;

import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.managing.Manager;

public abstract class DAO {

	private LinkedList<TableParameter> params = new LinkedList<>();

	public abstract boolean create();

	protected final boolean insertInto(String tableName) {
		DataBaseHandler dataBaseHandler = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);
		return dataBaseHandler.insertInto(tableName, getColumsAsSQLString(), getValuesAsSQLString());
	}

	protected void addTableParameter(TableParameter tableParameter) {
		params.add(tableParameter);
	}

	private String getColumsAsSQLString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (TableParameter tp : params) {
			sb.append(tp.getColumn());
			sb.append(", ");
		}
		sb.replace(sb.length() - 2, sb.length(), ")");
		return sb.toString();
	}

	private String getValuesAsSQLString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (TableParameter tp : params) {
			sb.append("'");
			sb.append(tp.getValue());
			sb.append("', ");
		}
		sb.replace(sb.length() - 2, sb.length(), ")");
		return sb.toString();
	}

}
