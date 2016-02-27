package com.dbegnis.base.command;

public abstract class BaseCommand {
	
	protected Object caller;
	
	public boolean execute(Object caller, String...params) {
		boolean valid = validateParameters(params);
		if (!valid) {
			return false;
		}
		return handle(params);
	}
	
	public abstract boolean validateParameters(String...params);
	
	public abstract boolean handle(String...params);
}
