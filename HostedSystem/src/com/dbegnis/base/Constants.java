package com.dbegnis.base;

public abstract class Constants {

	/*
	 * constants
	 */
	public static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

	/*
	 * constants keys
	 */
	public static final String PORT = "const_port";
	public static final String RUNNING = "const_running";

	/*
	 * command names
	 */
	public static final String AUTHCMD_NAME = "/login";

	/*
	 * commands rights groups 0 => unregistered user, 1 => normal user, 3 =>
	 * admin, 4 => system admin
	 */
	public static final int AUTHCMD_RG = 0;

	/*
	 * table names
	 */
	public static final String TABLE_PREFIX = "T_";
	public static final String TABLENAME_PLACEHOLDER = "$tablename";
	public static final String USER_PREFIX = "U_";
	public static final String USERTABLE = "T_USERS";

}
