package com.dbegnis.tables.tables.handler;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;
import com.dbegnis.tables.tables.UserToGroup;
import com.dbegnis.tables.tables.utils.DAO;
import com.dbegnis.tables.tables.utils.TableParameter;

public class UserToGroupDAO extends DAO {
	
	private static Logger log = Logger.getLogger(UserToGroupDAO.class);
	
	public UserToGroupDAO(String userID, String groupID) {
		addTableParameter(new TableParameter(UserToGroup.ID_USER, userID));
		addTableParameter(new TableParameter(UserToGroup.ID_GROUP, groupID));
	}

	@Override
	public boolean create() {
		boolean result = super.insertInto(Constants.USERTOGROUPTABLE);
		if (!result) {
			log.info("could not add user to group");
		}
		log.info("added user to group");
		return result;
	}

}
