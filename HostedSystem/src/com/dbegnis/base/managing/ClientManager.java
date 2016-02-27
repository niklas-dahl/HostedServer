package com.dbegnis.base.managing;

import java.util.HashMap;

import com.dbegnis.network.ClientConnection;

public class ClientManager {

	private static ClientManager clientManager;

	private HashMap<String, ClientConnection> clients;

	private ClientManager() {
		clients = new HashMap<>();
	}

	public Object get(String obj) {
		return clients.get(obj);
	}

	public void put(String key, ClientConnection value) {
		clients.put(key, value);
	}

	public void remove(String obj) {
		clients.remove(obj);
	}

	protected static ClientManager getClientManager() {
		if (clientManager == null) {
			clientManager = new ClientManager();
		}
		return clientManager;
	}

}
