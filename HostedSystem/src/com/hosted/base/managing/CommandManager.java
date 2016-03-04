package com.hosted.base.managing;

import java.util.HashMap;

import com.hosted.base.command.BaseCommand;

public class CommandManager {
	private static CommandManager commandManager;

	private HashMap<String, BaseCommand> commands;

	private CommandManager() {
		commands = new HashMap<>();
	}

	public BaseCommand get(String key) {
		return commands.get(key);
	}

	public void put(String key, BaseCommand value) {
		commands.put(key, value);
	}

	public void remove(String key) {
		commands.remove(key);
	}

	protected static CommandManager getCommandManager() {
		if (commandManager == null) {
			commandManager = new CommandManager();
		}
		return commandManager;
	}
}
