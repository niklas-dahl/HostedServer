package com.hosted.tables.daos;

import com.hosted.base.Constants;
import com.hosted.base.Logger;
import com.hosted.tables.tables.UserToGroup;
import com.hosted.tables.utils.DAO;
import com.hosted.tables.utils.TableParameter;

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
