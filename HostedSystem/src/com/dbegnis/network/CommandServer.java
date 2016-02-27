package com.dbegnis.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;
import com.dbegnis.base.managing.Manager;

public class CommandServer implements Runnable {
	
	private static final Logger log = Logger.getLogger(CommandServer.class);

	private ServerSocket socket;
	private Socket connection;

	public CommandServer() {
		setupSocket();
		new Thread(this).start();

	}

	private void setupSocket() {
		log.info("setting up socket..");
		try {
			socket = new ServerSocket((int) Manager.getResourceManager().get(Constants.PORT));
		} catch (IOException e) {
			log.error("failed to open socket: " + e);
		}
		log.info("setting up socket finished");
	}

	@Override
	public void run() {
		while ((boolean) Manager.getResourceManager().get(Constants.RUNNING)) {
			try {
				log.info("waiting for connection..");
				connection = socket.accept();
				log.info("got connection from " + connection.getInetAddress().getHostAddress());
				new ClientConnection(connection);
			} catch (IOException e) {
				log.error("error while waiting on connection: " + e);
			}
		}
	}
	
	public void close() {
		try {
			socket.close();
			log.info("socket closed");
		} catch (IOException e) {
			log.error("failed to close socket: " + e);
		}
	}
}
