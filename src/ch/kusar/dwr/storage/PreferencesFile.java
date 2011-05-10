///**
// * @author Damir Kusar (damir@kusar.ch)
// * @date 10.05.2011
// * @version 0.1 - Created the class 
// *  
// */
//
//package ch.kusar.dwr.storage;
//
//import java.io.FileOutputStream;
//
//import ch.kusar.dwr.setup.Settings;
//import ch.kusar.dwr.setup.SettingsEnum;
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//
//public class PreferencesFile extends Activity {
//	public static final String PREFFILE = "dwr";
//
//	public void saveSettings(String host, String port, String user, String pass) {
//		// We need an Editor object to make preference changes.
//		// All objects are from android.context.Context
//		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//		SharedPreferences.Editor editor = settings.edit();
//
//		editor.putString(SettingsEnum.HOST.name(), host);
//		editor.putString(SettingsEnum.PORT.name(), port);
//		editor.putString(SettingsEnum.USERNAME.name(), user);
//		editor.putString(SettingsEnum.PASSWORD.name(), pass);
//
//		// Commit the edits!
//		editor.commit();
//	}
//
//	public void loadSettings() {
//		// Restore preferences
//		SharedPreferences settings = getSharedPreferences(PREFFILE, BIND_AUTO_CREATE);
//		Settings.setHost(settings.getString(SettingsEnum.HOST.name(),
//				SettingsEnum.HOST.name()));
//		Settings.setPort(settings.getString(SettingsEnum.PORT.name(),
//				SettingsEnum.PORT.name()));
//		Settings.setUser(settings.getString(SettingsEnum.USERNAME.name(),
//				SettingsEnum.USERNAME.name()));
//		Settings.setPass(settings.getString(SettingsEnum.PASSWORD.name(),
//				SettingsEnum.PASSWORD.name()));
//	}
//	
////	private void createFile()
////	{
////		String FILENAME = "hello_file";
////		String string = "hello world!";
////
////		FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
////		fos.write(string.getBytes());
////		fos.close();
////	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//
//		// Restore preferences
//		SharedPreferences settings = getSharedPreferences(PREFFILE, BIND_AUTO_CREATE);
//		Settings.setHost(settings.getString(SettingsEnum.HOST.name(),
//				SettingsEnum.HOST.name()));
//		Settings.setPort(settings.getString(SettingsEnum.PORT.name(),
//				SettingsEnum.PORT.name()));
//		Settings.setUser(settings.getString(SettingsEnum.USERNAME.name(),
//				SettingsEnum.USERNAME.name()));
//		Settings.setPass(settings.getString(SettingsEnum.PASSWORD.name(),
//				SettingsEnum.PASSWORD.name()));
//	}
//
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//
//		// We need an Editor object to make preference changes.
//		// All objects are from android.context.Context
//		SharedPreferences settings = getSharedPreferences(PREFFILE, BIND_AUTO_CREATE);
//		SharedPreferences.Editor editor = settings.edit();
//
//		editor.putString(SettingsEnum.HOST.name(), SettingsEnum.HOST.name());
//		editor.putString(SettingsEnum.PORT.name(), SettingsEnum.PORT.name());
//		editor.putString(SettingsEnum.USERNAME.name(),
//				SettingsEnum.USERNAME.name());
//		editor.putString(SettingsEnum.PASSWORD.name(),
//				SettingsEnum.PASSWORD.name());
//
//		// Commit the edits!
//		editor.commit();
//	}
//
//}
