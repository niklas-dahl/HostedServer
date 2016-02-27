package com.dbegnis.base;

import java.sql.*;

public class DataBase {

	private Connection conn;
	private Statement stmt;

	private String tab = "USERS";

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

		String createQ = "CREATE TABLE IF NOT EXISTS " + tab
				+ "(ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(20) NOT NULL UNIQUE, PASSWORD VARCHAR(20) NOT NULL)";
		stmt.executeUpdate(createQ);
	}

	private void insertTestData() throws SQLException {
		String insertQ = "INSERT INTO " + tab + " VALUES('2','testuser', 'secret')";
		stmt.executeUpdate(insertQ);

		ResultSet selectRS = stmt.executeQuery("SELECT * FROM " + tab);
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
