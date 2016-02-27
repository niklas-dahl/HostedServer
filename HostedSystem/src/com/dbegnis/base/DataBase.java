package com.dbegnis.base;

import java.sql.*;

public class DataBase {

	public static void main(String[] a) {
		
		Connection conn = null;
		String tab = "TESTTABELLE";

		try {

			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			conn = DriverManager.getConnection("jdbc:h2:~/.javabeginners/h2Test", "", "");

			Statement stmt = conn.createStatement();


			String createQ = "CREATE TABLE IF NOT EXISTS " + tab
					+ "(ID INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, NAME VARCHAR(255))";
			stmt.executeUpdate(createQ);

			String insertQ = "INSERT INTO " + tab + " VALUES(TRANSACTION_ID(),'Hello World!')";
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

			conn.close();
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
}
