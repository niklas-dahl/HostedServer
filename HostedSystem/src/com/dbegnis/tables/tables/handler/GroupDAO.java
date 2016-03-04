package com.dbegnis.tables.tables.handler;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;
import com.dbegnis.tables.tables.Group;
import com.dbegnis.tables.tables.utils.DAO;
import com.dbegnis.tables.tables.utils.TableParameter;

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
