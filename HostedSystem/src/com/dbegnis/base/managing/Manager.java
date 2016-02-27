package com.dbegnis.base.managing;

public abstract class Manager {
	
	public static ClientManager getClientManager() {
		return ClientManager.getClientManager();
	}
	
	public static BeanManager getBeanManager() {
		return BeanManager.getBeanManager();
	}
	
	public static ResourceManager getResourceManager() {
		return ResourceManager.getResourceManager();
	}
	
	public static CommandManager getCommandManager() {
		return CommandManager.getCommandManager();
	}
	
}
