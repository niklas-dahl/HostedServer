package com.dbegnis.tables.tables.handler;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;
import com.dbegnis.tables.tables.utils.DAO;
import com.dbegnis.tables.tables.utils.TableParameter;

public class UserDAO extends DAO {
	
	private static Logger log = Logger.getLogger(UserDAO.class);

	public UserDAO(String name, String password, String rightsGroup) {
		addTableParameter(new TableParameter("name", name));
		addTableParameter(new TableParameter("password", password));
		addTableParameter(new TableParameter("rightsgroup", rightsGroup));
	}

	@Override
	public boolean create() {
		boolean result = super.insertInto(Constants.USERTABLE);
		if (!result) {
			log.info("could not create user");
		}
		log.info("user created");
		return result;
	}
	
	
	

}
