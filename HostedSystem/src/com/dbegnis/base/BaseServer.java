package com.dbegnis.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dbegnis.base.managing.Manager;
import com.dbegnis.network.CommandServer;

public class BaseServer {

	public BaseServer() {
		setupDataBase();
		setupResources();
		setupCommandServer();
	}

	private void setupDataBase() {
		Manager.getBeanManager().put(DataBaseHandler.class, new DataBaseHandler());
	}

	private void setupResources() {
		Manager.getResourceManager().put(Constants.PORT, 12345);
		Manager.getResourceManager().put(Constants.RUNNING, true);

		loadResourcesFromFiles();
	}

	private void loadResourcesFromFiles() {
		File f = new File("res/txt");
		for (File resource : f.listFiles()) {
			if (!loadResourcesFromFile(resource)) {
				System.out.println("Failed to load resources from: " + resource.getName());
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
			return false;
		}
		return true;
	}

	private void setupCommandServer() {
		Manager.getBeanManager().put(CommandServer.class, new CommandServer());
	}

}
