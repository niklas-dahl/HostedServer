package com.dbegnis.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.dbegnis.base.Constants;
import com.dbegnis.base.managing.Manager;

public class CommandServer implements Runnable {

	private ServerSocket socket;
	private Socket connection;

	public CommandServer() {
		setupSocket();
		new Thread(this).start();

	}

	private void setupSocket() {
		try {
			socket = new ServerSocket((int) Manager.getResourceManager().get(Constants.PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while ((boolean) Manager.getResourceManager().get(Constants.RUNNING)) {
			try {
				System.out.println("waiting...");
				connection = socket.accept();
				new ClientConnection(connection);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
