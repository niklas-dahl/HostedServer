package com.dbegnis.base;

import java.sql.*;

public class DataBase {

	private Connection conn;
	private Statement stmt;

	public DataBase() {
		try {
			setupDataBaseConnection();
			setupTables();
			insertTestData();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private void setupDataBaseConnection() throws SQLException {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection("jdbc:h2:./res/database", "", "");
		stmt = conn.createStatement();

	}

	private void setupTables() throws SQLException {

		String createQ = "CREATE TABLE IF NOT EXISTS USERS "
				+ "(ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(20) NOT NULL UNIQUE, PASSWORD VARCHAR(20) NOT NULL)";
		stmt.executeUpdate(createQ);
	}

	public boolean insertInto(String table, String columns, String params) {
		String insertQ = "INSERT INTO " + table + " " + columns + " VALUES" + params;
		try {
			stmt.executeUpdate(insertQ);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public ResultSet selectFrom(String table, String columns, String where) {
		String select = "SELECT " + columns + " FROM " + table;
		if (where != null && !"".equals(where)) {
			select +=  " WHERE " + where;
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(select);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet selectFrom(String table, String columns) {
		return selectFrom(table, columns, "");
	}
	
	public ResultSet selectFrom(String table) {
		return selectFrom(table, "*", "");
	}

	private void insertTestData() throws SQLException {
		
		boolean boo = insertInto("USERS", "(NAME, PASSWORD)", "('test2', 'geheim')");
		
		if (!boo) {
			System.out.println("Failed to insert Data");
		}

		ResultSet selectRS = selectFrom("USERS", "*", "");
		while (selectRS.next()) {
			System.out.printf("%s, %s\n", selectRS.getString(1), selectRS.getString(2));
		}

		System.out.println("Liste Tabellen...");
		String tablesQ = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'";
		ResultSet tablesRS = stmt.executeQuery(tablesQ);
		while (tablesRS.next()) {
			System.out.printf("Tabelle %s vorhanden \n", tablesRS.getString(1));
		}
	}
}
