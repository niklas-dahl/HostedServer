package com.dbegnis.base.command;

import com.dbegnis.base.Logger;

public abstract class BaseCommand {

	private static final Logger log = Logger.getLogger(BaseCommand.class);

	protected Object caller;

	public boolean execute(Object caller, String... params) {
		boolean valid = validateParameters(params);
		if (!valid) {
			log.error("command execution failed: parameters not valid");
			return false;
		}
		return handle(params);
	}

	public abstract boolean validateParameters(String... params);

	public abstract boolean handle(String... params);
}
