package com.dbegnis.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.dbegnis.base.Logger;
import com.dbegnis.base.command.BaseCommand;
import com.dbegnis.base.managing.Manager;

public class ClientConnection implements Runnable {

	private static final Logger log = Logger.getLogger(ClientConnection.class);

	private Socket connection;
	private String ipAdress;
	private DataOutputStream out;
	private DataInputStream in;
	private Thread thread;
	
	private int rightsGroup = 0;
	private boolean connected = false;

	public ClientConnection(Socket connection) {
		this.connection = connection;
		setup();
		thread = new Thread(this);
		thread.start();
	}

	private void setup() {
		ipAdress = connection.getInetAddress().getHostAddress();
		try {
			out = new DataOutputStream(connection.getOutputStream());
			in = new DataInputStream(connection.getInputStream());
		} catch (IOException e) {
			log.error("could not correctly setup connection to client: " + e);
		}
		connected = true;
		log.info("connection to client " + ipAdress + " stabled");
	}

	@Override
	public void run() {
		while (connected) {
			try {
				String str = in.readUTF();
				log.info("Received: '" + str + "' from " + ipAdress);
				doCommand(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doCommand(String cmdString) {
		String[] params = cmdString.split(" ");
		BaseCommand cmd = Manager.getCommandManager().get(params[0]);
		if (cmd != null) {
			if (!cmd.execute(this, params)) {
				log.error("faild to execute command: " + cmd.getClass().getSimpleName());
				send("command execution failed");
			}
		} else {
			send("no such command");
			log.error("no such command");
		}
	}

	public void authorise(String userName, int group) {
		this.rightsGroup = group;
		Manager.getClientManager().put(ipAdress, this);
		send("Welcome " + userName);
	}

	public boolean send(String str) {
		try {
			out.writeUTF(str);
			out.flush();
			log.info("Sended  '" + str + "' to " + ipAdress);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void close()  {
		try {
			in.close();
			out.close();
			connection.close();
			log.info("closed connection to " + ipAdress);
		} catch (IOException e) {
			log.error("failed to close connection to: " + ipAdress + " error: " + e);
		}
	}
	
	public int getRightsGroup() {
		return rightsGroup;
	}
}
