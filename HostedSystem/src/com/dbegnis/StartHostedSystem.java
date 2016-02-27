package com.dbegnis;

import com.dbegnis.base.BaseServer;
import com.dbegnis.base.Logger;

public abstract class StartHostedSystem {
	
	private static final Logger log = Logger.getLogger(StartHostedSystem.class);

	public static void main(String[] args) {
		Logger.setLogDirectory("res/log");
		log.info("");
		log.info("-----------------------------------------------------------------");
		log.info("");
		new BaseServer();
	}

}
