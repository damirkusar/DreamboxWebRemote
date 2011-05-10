/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.setup;


public class Settings {
	private static String host = null;
	private static String port = null;
	private static String user = null;
	private static String pass = null;
	public static final String PREFFILE = "dwr";
	
	
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		Settings.host = host;
	}
	public static String getPort() {
		return port;
	}
	public static void setPort(String port) {
		Settings.port = port;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		Settings.user = user;
	}
	public static String getPass() {
		return pass;
	}
	public static void setPass(String pass) {
		Settings.pass = pass;
	}
	
	
}
