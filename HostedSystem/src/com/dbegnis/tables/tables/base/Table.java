package com.dbegnis.tables.tables.base;

import java.util.LinkedList;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;

public abstract class Table {

	protected static final Logger log = Logger.getLogger(Table.class);

	private LinkedList<TableColumn> columns = new LinkedList<>();

	protected String tableName;

	public Table(Class<? extends Table> clazz) {
		this.tableName = Constants.TABLE_PREFIX + clazz.getSimpleName();
	}

	protected void addColumn(TableColumn column) {
		columns.add(column);
	}

	public String getTableSQL() {
		String sql = "CREATE TABLE IF NOT EXISTS  " + tableName + " (" + getColumnString() + ")";
		return sql;
	}

	private String getColumnString() {
		StringBuilder columns = new StringBuilder();
		for (TableColumn column : this.columns) {
			columns.append(column.toString());
			columns.append(",");
		}
		columns.deleteCharAt(columns.length() - 1);
		return columns.toString();
	}

	public String getTableName() {
		return tableName;
	}

}
