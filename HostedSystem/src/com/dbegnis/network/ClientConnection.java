package com.dbegnis.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import com.dbegnis.base.Constants;
import com.dbegnis.base.command.BaseCommand;
import com.dbegnis.base.managing.Manager;
import com.dbegnis.base.managing.ResourceManager;

public class ClientConnection implements Runnable {

	private Socket connection;
	private String ipAdress;
	private DataOutputStream out;
	private DataInputStream in;

	private Thread thread;

	private boolean connected = false;
	private boolean authorised = false;

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
			e.printStackTrace();
		}
		connected = true;
	}

	@Override
	public void run() {
		while (connected) {
			try {
				String str = in.readUTF();
				System.out.println("Received: " + str);
				doCommand(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	private void doCommand(String cmdString) {
		String[] params = cmdString.split(" ");
		BaseCommand cmd = Manager.getCommandManager().get(params[1]);
		if (cmd != null) {
			 if (!cmd.execute(this, params)) {
				 send("command execution failed");
			 }
		
		}
	}
	
	public void authorise(String userName) {
		authorised = true;
		Manager.getClientManager().put(ipAdress, this);
		send("Welcome " + userName);
	}

	public boolean send(String str) {
		try {
			out.writeUTF(str);
			out.flush();
			System.out.println("Sended  '" + str + "' to " + ipAdress);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void close() throws IOException {
		in.close();
		out.close();
		connection.close();
	}
}
