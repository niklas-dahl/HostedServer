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

	public AuthoriseCommand(int group) {
		super(group);
	}

	@Override
	public boolean validateParameters(String... params) {
		if (params == null || params.length != 3) {
			log.error("command execution failed: this command takes exactly 2 arguments");
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(String... params) {
		this.caller = (ClientConnection) super.caller;

		DataBaseHandler dbh = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);
		try {
			ResultSet rs = dbh.selectFrom(Constants.USERTABLE, "*", "NAME LIKE '" + params[1] + "'");
			if (rs == null) {
				log.debug("no such user found");
				return false;
			}
			rs.next();
			if (!rs.isLast()) {
				log.info("wrong params");
				return false;
			}
			if (rs.getString(3) != null && rs.getString(3).equals(params[2])) {
				caller.authorise(rs.getInt(1), rs.getString(2), rs.getInt(4));
				return true;
			}
		} catch (SQLException e) {
			log.error("failed to check authentication: " + e);
		}
		return false;
	}

}
