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

	private static final Logger log = Logger.getLogger(DataBaseHandler.class);

	private Connection connection;
	private Statement statement;
	private Server webServer;

	public DataBaseHandler() {
		try {
			setupDataBaseConnection();
			setupTables();
			insertBaseData();
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
		connection = DriverManager.getConnection("jdbc:h2:./res/database", "sa", "sa");
		webServer = Server.createWebServer().start();
		statement = connection.createStatement();
		log.info("setup database connection finished");
	}

	private void setupTables() throws SQLException {
		log.info("setting up tables..");
		Set<String> keys = Manager.getResourceManager().getKeySet();
		for (String key : keys) {
			if (key.startsWith(Constants.TABLE_PREFIX)) {
				String sql = (String) Manager.getResourceManager().get(key);
				sql = sql.replace(Constants.TABLENAME_PLACEHOLDER, key);
				executeUpdate(sql);
			}
		}
		log.info("setting up tables finished");
	}

	private void insertBaseData() {
		log.info("inserting base data..");
		Set<String> keys = Manager.getResourceManager().getKeySet();
		for (String key : keys) {
			if (key.startsWith(Constants.USER_PREFIX)) {
				String[] data = ((String) Manager.getResourceManager().get(key)).split(" ");
				insertInto(Constants.USERTABLE, "(NAME, PASSWORD, RIGHTS_GROUP)",
						"('" + key.replace(Constants.USER_PREFIX, "") + "', '" + data[0] + "', '" + data[1] + "')");
			}
		}
		log.info("base data inserted");
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
