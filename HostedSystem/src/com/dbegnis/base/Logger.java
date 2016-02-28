package com.dbegnis.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private static File logDirectory;

	private Class<?> clazz;
	private static PrintStream consoleStream;
	private static PrintWriter serverStream, errorStream, infoStream, debugStream;

	private static final DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

	private Logger(Class<?> clazz) {
		this.clazz = clazz;
		consoleStream = System.out;
	}

	public void info(String str) {
		String logStr = "[" + dateFormat.format(new Date()) + "] [INFO] " + "[" + clazz.getSimpleName() + "]" + ": "
				+ str;
		consoleStream.println(logStr);
		if (logDirectory != null) {
			infoStream.println(logStr);
			infoStream.flush();
			serverStream.println(logStr);
			serverStream.flush();
		}
	}

	public void error(String str) {
		String logStr = "[" + dateFormat.format(new Date()) + "] [INFO] " + "[" + clazz.getSimpleName() + "]" + ": "
				+ str;
		consoleStream.println(logStr);
		if (logDirectory != null) {
			errorStream.println(logStr);
			errorStream.flush();
			serverStream.println(logStr);
			serverStream.flush();
		}
	}

	public void debug(String str) {
		String logStr = "[" + dateFormat.format(new Date()) + "] [INFO] " + "[" + clazz.getSimpleName() + "]" + ": "
				+ str;
		consoleStream.println(logStr);
		if (logDirectory != null) {
			debugStream.println(logStr);
			debugStream.flush();
			serverStream.println(logStr);
			serverStream.flush();
		}
	}

	public static void setLogDirectory(String path) {
		logDirectory = new File(path);
		if (!logDirectory.isDirectory()) {
			logDirectory.mkdirs();
		}
		try {
			File file = createLogFile("server.log");
			serverStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			file = createLogFile("info.log");
			infoStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			file = createLogFile("error.log");
			errorStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			file = createLogFile("debug.log");
			debugStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static File createLogFile(String fileName) {
		File file = new File(logDirectory.getPath() + "/" + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static void close() {
		serverStream.close();
		infoStream.close();
		debugStream.close();
		errorStream.close();
	}

	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz);
	}

}
