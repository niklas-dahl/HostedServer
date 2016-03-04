package com.hosted.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.hosted.base.Logger;
import com.hosted.base.command.BaseCommand;
import com.hosted.base.managing.Manager;

public class ClientConnection implements Runnable {

	private static final Logger log = Logger.getLogger(ClientConnection.class);

	private Socket connection;
	private String ipAdress;
	private DataOutputStream out;
	private DataInputStream in;
	private Thread thread;

	private String userName;

	private int id;
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
			connected = true;
			log.info("connection to client " + ipAdress + " stabled");
		} catch (IOException e) {
			log.error("could not correctly setup connection to client: " + e);
		}
	}

	@Override
	public void run() {
		while (connected) {
			try {
				String str = in.readUTF();
				log.info("Received: '" + str + "' from " + userName + "(" + ipAdress + ")");
				doCommand(str);
			} catch (IOException e) {
				log.error("error while receiving data from " + userName + "(" + ipAdress + ")");
				close();
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

	public void authorise(int id, String userName, int group) {
		this.id = id;
		this.userName = userName;
		this.rightsGroup = group;
		Manager.getClientManager().put(id, this);
		send("Welcome " + userName);
	}

	public boolean send(String str) {
		try {
			out.writeUTF(str);
			out.flush();
			log.info("Sended  '" + str + "' to " + userName + "(" + ipAdress + ")");
		} catch (IOException e) {
			e.printStackTrace();
			close();
			return false;
		}
		return true;
	}

	public void close() {
		try {
			in.close();
			out.close();
			connection.close();
			connected = false;
			Manager.getClientManager().remove(id);
			log.info("closed connection to " + userName + "(" + ipAdress + ")");
		} catch (IOException e) {
			log.error("failed to close connection to: " + userName + "(" + ipAdress + ")" + " error: " + e);
		}
	}

	public int getRightsGroup() {
		return rightsGroup;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getId() {
		return id;
	}
}
