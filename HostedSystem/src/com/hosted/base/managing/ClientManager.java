package com.hosted.base.managing;

import java.util.HashMap;

import com.hosted.network.ClientConnection;

public class ClientManager {

	private static ClientManager clientManager;

	private HashMap<Integer, ClientConnection> clients;

	private ClientManager() {
		clients = new HashMap<>();
	}

	public Object get(Integer key) {
		return clients.get(key);
	}

	public void put(Integer key, ClientConnection value) {
		clients.put(key, value);
	}

	public void remove(Integer key) {
		clients.remove(key);
	}

	protected static ClientManager getClientManager() {
		if (clientManager == null) {
			clientManager = new ClientManager();
		}
		return clientManager;
	}

}
