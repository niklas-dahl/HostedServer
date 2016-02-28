package com.dbegnis.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dbegnis.base.command.AddUsersCommand;
import com.dbegnis.base.command.AuthoriseCommand;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.CommandServer;

public class BaseServer {

	private static final Logger log = Logger.getLogger(BaseServer.class);

	public BaseServer() {
		log.info("starting up..");
		Manager.getBeanManager().put(BaseServer.class, this);
		setupResources();
		setupDataBase();
		setupCommands();
		setupCommandServer();
	}

	private void setupResources() {
		log.info("setting up resources..");
		Manager.getResourceManager().put(Constants.PORT, 12345);
		Manager.getResourceManager().put(Constants.RUNNING, true);
		loadResourcesFromFiles();
		log.info("resources loaded");
	}

	private void setupDataBase() {
		log.info("setting up database..");
		Manager.getBeanManager().put(DataBaseHandler.class, new DataBaseHandler());
		log.info("database setup finished");
	}

	private void setupCommands() {
		Manager.getCommandManager().put(Constants.AUTHCMD_NAME, new AuthoriseCommand(Constants.AUTHCMD_RG));
		Manager.getCommandManager().put(Constants.ADDUSERCMD_NAME, new AddUsersCommand(Constants.ADDUSERCMD_RG));
	}

	private void loadResourcesFromFiles() {
		File f = new File("res/txt");
		for (File resource : f.listFiles()) {
			if (!loadResourcesFromFile(resource)) {
				log.error("Failed to load resources from: " + resource.getName());
			}
		}
	}

	private boolean loadResourcesFromFile(File resource) {
		try (Scanner scanner = new Scanner(resource)) {
			String code = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] res = scanner.nextLine().split(":");
				if (res != null && res.length == 2) {
					Manager.getResourceManager().put(code + res[0], res[1]);
				}
			}
		} catch (FileNotFoundException e) {
			log.error("failed to load resources (file not found) " + e);
			return false;
		}
		log.info("loaded resources from " + resource.getPath());
		return true;
	}

	private void setupCommandServer() {
		log.info("setting up command server..");
		Manager.getBeanManager().put(CommandServer.class, new CommandServer());
		log.info("command server setup finished");
	}

}
