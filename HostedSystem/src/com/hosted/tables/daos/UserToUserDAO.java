package com.hosted.tables.daos;

import com.hosted.base.Constants;
import com.hosted.base.Logger;
import com.hosted.tables.tables.UserToGroup;
import com.hosted.tables.utils.DAO;
import com.hosted.tables.utils.TableParameter;

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
