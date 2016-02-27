package com.dbegnis.base.command;

import com.dbegnis.base.Constants;
import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.Logger;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.ClientConnection;

public class AuthoriseCommand extends BaseCommand {
	
	private static final Logger log = Logger.getLogger(AuthoriseCommand.class);

	private ClientConnection caller;

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
		//TODO: authenticate user
		dbh.selectFrom(Constants.USERTABLE, "()", "");
		
		 if (Manager.getResourceManager().get( params[0]) == params[1]) {
			caller.authorise(params[0]);
			return true;
		} else {
			return false;
		}
	}

}
