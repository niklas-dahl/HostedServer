package com.hosted.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class DataBaseHandler {

	private static final Logger log = Logger.getLogger(DataBaseHandler.class);

	private Connection connection;
	private Statement statement;
	private Server webServer;

	public DataBaseHandler() {
		try {
			setupDataBaseConnection();
		} catch (SQLException e) {
			log.error("error while initialising database: " + e);
		}
	}

	private void setupDataBaseConnection() throws SQLException {
		log.info("setup database connection..");
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			log.error("error while getting database class: " + e);
		}
		connection = DriverManager.getConnection(Constants.DATABASE_PATH, "sa", "sa");
		webServer = Server.createWebServer().start();
		statement = connection.createStatement();
		log.info("setup database connection finished");
	}

	public boolean executeUpdate(String sql) {
		try {
			log.info("run sql: " + sql);
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			log.error("sql execution failed: " + e);
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			log.info("run sql: " + sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			log.error("sql execution failed: " + e);
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
			log.info("dabase connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
