package com.hosted;

import com.hosted.base.BaseServer;
import com.hosted.base.Constants;
import com.hosted.base.Logger;

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
