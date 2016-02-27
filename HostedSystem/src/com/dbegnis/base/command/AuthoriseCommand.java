package com.dbegnis.base.command;

import com.dbegnis.base.Constants;
import com.dbegnis.base.DataBaseHandler;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.ClientConnection;

public class AuthoriseCommand extends BaseCommand {

	private ClientConnection caller;

	@Override
	public boolean validateParameters(String... params) {
		if (params == null || params.length != 3) {
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(String... params) {
		this.caller = (ClientConnection) super.caller;
		
		DataBaseHandler dbh = (DataBaseHandler) Manager.getBeanManager().get(DataBaseHandler.class);
		
		dbh.selectFrom(Constants.USERTABLE, "()", "");
		
		 if (Manager.getResourceManager().get( params[0]) == params[1]) {
			caller.authorise(params[0]);
			return true;
		} else {
			return false;
		}
	}

}
