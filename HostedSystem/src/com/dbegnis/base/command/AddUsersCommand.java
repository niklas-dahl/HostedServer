package com.dbegnis.base.command;

import com.dbegnis.base.Constants;
import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.Logger;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.ClientConnection;

public class AddUsersCommand extends BaseCommand {

	private static final Logger log = Logger.getLogger(AddUsersCommand.class);

	private ClientConnection caller;

	public AddUsersCommand(int group) {
		super(group);
	}

	@Override
	public boolean validateParameters(String... params) {
		this.caller = (ClientConnection) super.caller;

		if (params == null || params.length < 3) {
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

		if (Integer.parseInt(params[1]) != caller.getId()) {
			caller.send("error while executing command: this command takes at least one argument");
			log.error("error while executing command: this command takes at least one argument");
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(String... params) {

		DataBaseHandler dbh = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);

		int result = 0;
		for (int i = 2; i < params.length; i++) {

			boolean res = dbh.insertInto(Constants.USERTOUSERSTABLE, "(ID_U1, ID_U2)",
					"('" + params[1] + "','" + params[i] + "')");
			if (!res) {
				result++;
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
