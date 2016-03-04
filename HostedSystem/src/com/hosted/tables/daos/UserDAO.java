package com.hosted.tables.daos;

import com.hosted.base.Logger;
import com.hosted.base.managing.Manager;
import com.hosted.tables.tables.User;
import com.hosted.tables.utils.DAO;
import com.hosted.tables.utils.Table;
import com.hosted.tables.utils.TableParameter;

public class UserDAO extends DAO {
	
	private static Logger log = Logger.getLogger(UserDAO.class);

	public UserDAO(String name, String password, String rightsGroup) {
		addTableParameter(new TableParameter(User.NAME, name));
		addTableParameter(new TableParameter(User.PASSWORD, password));
		addTableParameter(new TableParameter(User.RIGHTSGROUP, rightsGroup));
	}

	@Override
	public boolean create() {
		boolean result = super.insertInto(((Table) Manager.getBeanManager().get(User.class)).getTableName());
		if (!result) {
			log.info("could not create user");
		}
		log.info("user created");
		return result;
	}
	
	
	

}
