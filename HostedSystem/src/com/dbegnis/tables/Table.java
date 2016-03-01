package com.dbegnis.tables;

import java.util.LinkedList;

import com.dbegnis.base.Constants;
import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.Logger;
import com.dbegnis.base.managing.Manager;

public abstract class Table {
	
	private static final Logger log = Logger.getLogger(Table.class);

	protected final String name;

	protected final Class<?> tablehandler;

	private LinkedList<String> columns = new LinkedList<>();
	
	
	protected static DataBaseHandler DBHandler;

	public Table(Class<?> clazz) {
		DBHandler = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);
		tablehandler = clazz;
		name = Constants.TABLE_PREFIX + clazz.getSimpleName();
	}

	protected void addColumn(String column) {
		columns.add(column);
	}

	protected void uploadToDB() {
		String sql = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLENAME_PH + " (" + Constants.COLUMNS_PH + ")";
		sql = sql.replace(Constants.TABLENAME_PH, name);
		sql = sql.replace(Constants.COLUMNS_PH, getColumnString());

		boolean res = DBHandler.executeUpdate(sql);
		
		if (!res) {
			log.info("could not create table " + name);
		}
	}

	private String getColumnString() {
		StringBuilder columns = new StringBuilder();
		for (String column : this.columns) {
			columns.append(column);
			columns.append(",");
		}
		columns.deleteCharAt(columns.length() - 1);
		return columns.toString();
	}
	
	
	public String getTableName() {
		return name;
	}

}
