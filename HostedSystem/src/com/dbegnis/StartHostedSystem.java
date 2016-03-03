package com.dbegnis;

import com.dbegnis.base.BaseServer;
import com.dbegnis.base.Constants;
import com.dbegnis.base.Logger;

public abstract class StartHostedSystem {

	private static final Logger log = Logger.getLogger(StartHostedSystem.class);

	public static void main(String[] args) {
		Logger.setLogDirectory(Constants.LOG_PATH);
		log.info("");
		log.info("-----------------------------------------------------------------");
		log.info("");
		new BaseServer();
	}

}
