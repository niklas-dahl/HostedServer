package com.dbegnis.base.command;

import com.dbegnis.base.Constants;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.ClientConnection;

public class AuthoriseCommand extends BaseCommand {

	private ClientConnection caller;

	@Override
	public boolean validateParameters(String... params) {
		if (params == null || params.length != 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(String... params) {
		this.caller = (ClientConnection) super.caller;
		if (params == null || params.length != 2) {
			return false;
		} else if (Manager.getResourceManager().get(Constants.USER + params[0]) == params[1]) {
			caller.authorise(params[0]);
			return true;
		} else {
			return false;
		}
	}

}
