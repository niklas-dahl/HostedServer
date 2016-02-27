package com.dbegnis.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import org.h2.tools.Server;

import com.dbegnis.base.managing.Manager;

public class DataBaseHandler {

	private Connection connection;
	private Statement statement;
	private Server webServer;

	public DataBaseHandler() {
		try {
			setupDataBaseConnection();
			setupTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setupDataBaseConnection() throws SQLException {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:h2:./res/database", "sa", "sa");
		webServer = Server.createWebServer().start();
		statement = connection.createStatement();
	}

	private void setupTables() throws SQLException {
		Set<String> keys = Manager.getResourceManager().getKeySet();
		for(String key : keys) {
			System.out.println(key);
			if (key.startsWith(Constants.TABLE_PREFIX)) {
				String sql = (String) Manager.getResourceManager().get(key);
				sql = sql.replace(Constants.TABLE_PLACEHOLDER, key);
				System.out.println("sql: " + sql);
				executeUpdate(sql);
				
			}
		}
	}

	public boolean executeUpdate(String sql) {
		try {
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean insertInto(String table, String columns, String params) {
		String sql = "INSERT INTO " + table + " " + columns + " VALUES" + params;
		return executeUpdate(sql);
	}

	public ResultSet selectFrom(String table, String columns, String where) {
		String select = "SELECT " + columns + " FROM " + table;
		if (where != null && !"".equals(where)) {
			select += " WHERE " + where;
		}
		return executeQuery(select);
	}

	public ResultSet selectFrom(String table, String columns) {
		return selectFrom(table, columns, "");
	}

	public ResultSet selectFrom(String table) {
		return selectFrom(table, "*", "");
	}
	
	public void close() {
		try {
			webServer.shutdown();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
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
		ResultSet tablesRS = statement.executeQuery(tablesQ);
		while (tablesRS.next()) {
			System.out.printf("Tabelle %s vorhanden \n", tablesRS.getString(1));
		}
	}
}
