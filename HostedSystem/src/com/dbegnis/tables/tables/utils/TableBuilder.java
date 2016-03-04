package com.dbegnis.tables.tables.utils;

import java.util.Set;

import com.dbegnis.base.Constants;
import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.Logger;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.tables.tables.handler.UserDAO;

public class TableBuilder {

	private static final Logger log = Logger.getLogger(TableBuilder.class);

	public static void setupTables() {
		log.info("setting up tables..");
		
		DataBaseHandler dataBaseHandler = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);
		for (Table t : Manager.getTableManager()) {
			boolean res = dataBaseHandler.executeUpdate(t.getTableSQL());
			if (!res) {
				log.info("could not create table " + t.getTableName());
				continue;
			}
			log.info("created table " + t.getTableName());
		}
		log.info("all tables setup");
	}

	public static void insertBaseData() {
		log.info("inserting base data..");
		
		Set<String> keys = Manager.getResourceManager().getKeySet();
		for (String key : keys) {
			if (key.startsWith(Constants.USER_PREFIX)) {
				String[] data = ((String) Manager.getResourceManager().get(key)).split(" ");
				new UserDAO(key.replace(Constants.USER_PREFIX, ""), data[0], data[1]).create();
			}
		}
		log.info("base data inserted");
	}

}
