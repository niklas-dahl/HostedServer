package com.dbegnis.tables.tables.handler;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;
import com.dbegnis.tables.tables.UserToGroup;
import com.dbegnis.tables.tables.utils.DAO;
import com.dbegnis.tables.tables.utils.TableParameter;

public class UserToUserDAO extends DAO {
	
	private static Logger log = Logger.getLogger(UserToUserDAO.class);

	public UserToUserDAO(String u1_Id, String u2_Id) {
		addTableParameter(new TableParameter(UserToGroup.ID_USER, u1_Id));
		addTableParameter(new TableParameter(UserToGroup.ID_GROUP, u2_Id));
	}

	@Override
	public boolean create() {
		boolean result = super.insertInto(Constants.USERTOGROUPTABLE);
		if (!result) {
			log.info("could not connect users");
		}
		log.info("connected users");
		return result;
	}

}
