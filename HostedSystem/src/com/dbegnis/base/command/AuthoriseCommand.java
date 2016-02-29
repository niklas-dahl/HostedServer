package com.dbegnis.base.command;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbegnis.base.Constants;
import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.Logger;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.ClientConnection;

public class AuthoriseCommand extends BaseCommand {

	private static final Logger log = Logger.getLogger(AuthoriseCommand.class);

	private ClientConnection caller;
	
	private static final int PARAMS_COUNT = 3;
	
	private static final int PARAMS_NAME = 1;
	private static final int PARAMS_PW = 2;
	
	private static final int RS_ID = 1;
	private static final int RS_NAME= 2;
	private static final int RS_PW= 3;
	private static final int RS_GROUP= 4;

	public AuthoriseCommand(int group) {
		super(group);
	}

	@Override
	public boolean validateParameters(String... params) {
		this.caller = (ClientConnection) super.caller;
		
		if (params == null || params.length != PARAMS_COUNT) {
			caller.send("error while executing command: this command takes exactly two arguments");
			log.error("error while executing command: this command takes exactly two arguments");
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(String... params) {

		DataBaseHandler dbh = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);
		
		try {
			ResultSet rs = dbh.selectFrom(Constants.USERTABLE, "*", "NAME LIKE '" + params[PARAMS_NAME] + "'");
			if (rs.isLast()) {
				log.info("error while authenticate user: no such user in data base");
				return false;
			}
			rs.next();
			
			if (!rs.isLast()) {
				log.error("error while authenticate user: result set has too many rows");
				return false;
			}
			if (rs.getString(RS_PW) != null && rs.getString(RS_PW).equals(params[PARAMS_PW])) {
				caller.authorise(rs.getInt(RS_ID), rs.getString(RS_NAME), rs.getInt(RS_GROUP));
				return true;
			}
		} catch (SQLException e) {
			log.error("failed to check authentication: " + e);
		}
		return false;
	}

}
