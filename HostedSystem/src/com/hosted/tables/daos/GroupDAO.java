package com.hosted.tables.daos;

import com.hosted.base.Constants;
import com.hosted.base.Logger;
import com.hosted.tables.tables.Group;
import com.hosted.tables.utils.DAO;
import com.hosted.tables.utils.TableParameter;

public class GroupDAO extends DAO {
	
	private static Logger log = Logger.getLogger(GroupDAO.class);
	
	public GroupDAO(String groupName) {
		addTableParameter(new TableParameter(Group.NAME, groupName));
	}

	@Override
	public boolean create() {
		boolean result = super.insertInto(Constants.GROUPTABLE);
		if (!result) {
			log.info("could not create group");
		}
		log.info("group created");
		return result;
	}

}
