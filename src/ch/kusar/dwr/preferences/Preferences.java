/**
 * This class handles the preferences.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.preferences;

import ch.kusar.dwr.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

public class Preferences {

	private static String host = null;
	private static String port = null;
	private static String user = null;
	private static String pass = null;

	private static String defaultHost = PreferencesEnum.HOST.name();
	private static String defaultPort = "80";
	private static String defaultUser = "root";
	private static String defaultPass = "dreambox";

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		Preferences.host = host;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		Preferences.port = port;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Preferences.user = user;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		Preferences.pass = pass;
	}

	/**
	 * 
	 * @param activity
	 *            . Usually with getActivity()
	 */
	public static void setPreferences(FragmentActivity activity) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(activity);
		Preferences.setHost(prefs.getString(PreferencesEnum.HOST.name(),
				defaultHost));
		Preferences.setPort(prefs.getString(PreferencesEnum.PORT.name(),
				defaultPort));
		Preferences.setUser(prefs.getString(PreferencesEnum.USERNAME.name(),
				defaultUser));
		Preferences.setPass(prefs.getString(PreferencesEnum.PASSWORD.name(),
				defaultPass));
	}
}
