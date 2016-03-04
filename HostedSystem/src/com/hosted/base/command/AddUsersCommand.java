package com.hosted.base.command;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hosted.base.Constants;
import com.hosted.base.DataBaseHandler;
import com.hosted.base.Logger;
import com.hosted.base.managing.Manager;
import com.hosted.network.ClientConnection;

public class AddUsersCommand extends BaseCommand {

	private static final Logger log = Logger.getLogger(AddUsersCommand.class);

	private static final int PARAMS_MIN = 3;
	private static final int PARAMS_USER = 1;

	private ClientConnection caller;

	public AddUsersCommand(int group) {
		super(group);
	}

	@Override
	public boolean validateParameters(String... params) {
		this.caller = (ClientConnection) super.caller;

		if (params == null || params.length < PARAMS_MIN) {
			caller.send("error while executing command: this command takes at least one argument");
			log.error("error while executing command: this command takes at least one argument");
			return false;
		}

		for (int i = 1; i < params.length; i++) {
			try {
				Integer.parseInt(params[i]);
			} catch (NumberFormatException e) {
				caller.send("error while executing command: this command takes only numbers argument");
				log.error("error while executing command: this command takes at only numbers argument");
				return false;
			}
		}

		if (Integer.parseInt(params[PARAMS_USER]) != caller.getId()) {
			caller.send("error while executing command: wrong executer");
			log.error("error while executing command: wrong executer");
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(String... params) {

		DataBaseHandler dbh = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);

		int result = 0;
		for (int i = 2; i < params.length; i++) {
			try {
				String uniqueGroupName = params[PARAMS_USER] + "_" + params[i];

				ResultSet rs = dbh.selectFrom(Constants.GROUPTABLE, "(ID)", "NAME LIKE '" + uniqueGroupName + "'");
				if (rs.isLast()) {
					result++;
					continue;
				}

				if (!dbh.insertInto(Constants.GROUPTABLE, "(NAME)", "('" + uniqueGroupName + "')")) {
					log.error("error while inserting data");
					continue;
				}

				rs = dbh.selectFrom(Constants.GROUPTABLE, "(ID)", "NAME LIKE '" + uniqueGroupName + "'");
				rs.next();
				
				boolean res = dbh.insertInto(Constants.USERTOUSERSTABLE, "(ID_U1, ID_U2, GROUP_ID)",
						"('" + params[PARAMS_USER] + "','" + params[i] + "','" + rs.getString(1) + "')");
				if (!res) {
					result++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result > 0) {
			caller.send("errors orcurred while adding users: " + result + " users could not be added");
			log.info("errors orcurred while adding users: " + result + " users could not be added");
			return false;
		}
		caller.send("command execution succsessful");
		return true;
	}

}
