package com.dbegnis.base.command;

import com.dbegnis.base.Logger;
import com.dbegnis.network.ClientConnection;

public abstract class BaseCommand {

	private static final Logger log = Logger.getLogger(BaseCommand.class);

	protected final int group;

	protected Object caller;

	public BaseCommand(int group) {
		this.group = group;
	}

	public boolean execute(Object caller, String... params) {
		this.caller = caller;
		if (!isAllowed() || !validateParameters(params)) {
			log.error("command execution failed: parameters not valid");
			return false;
		}
		return handle(params);
	}

	private boolean isAllowed() {
		if (caller instanceof ClientConnection) {
			if (((ClientConnection)caller).getRightsGroup() >= group) {
				return true;
			}
			return false;
		}
		return true;
	}

	public abstract boolean validateParameters(String... params);

	public abstract boolean handle(String... params);

}
