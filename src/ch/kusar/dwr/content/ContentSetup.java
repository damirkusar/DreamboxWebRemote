/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.content;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import ch.kusar.dwr.R;
import ch.kusar.dwr.preferences.PreferencesFragment.PrefsFragment;
import ch.kusar.dwr.setup.Settings;
import ch.kusar.dwr.setup.SettingsEnum;

public class ContentSetup extends Activity {
	private EditText editTextHostIP = null;
	private EditText editTextHostPort = null;
	private EditText editTextUsername = null;
	private EditText editTextPassword = null;

	// private static PreferencesFile pf = new PreferencesFile();

	public View getSetupView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.setup, container, false);

		editTextHostIP = (EditText) view.findViewById(R.id.editTextHostIP);
		editTextHostIP.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (editTextHostIP.getText().toString()
						.contains(SettingsEnum.HOST.name())) {
					editTextHostIP.getText().clear();
				}
				return false;
			}
		});

		editTextHostPort = (EditText) view.findViewById(R.id.editTextHostPort);
		editTextHostPort.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (editTextHostPort.getText().toString()
						.contains(SettingsEnum.PORT.name())) {
					editTextHostPort.getText().clear();
				}
				return false;
			}
		});

		editTextUsername = (EditText) view.findViewById(R.id.editTextUser);
		editTextUsername.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (editTextUsername.getText().toString()
						.contains(SettingsEnum.USERNAME.name())) {
					editTextUsername.getText().clear();
				}
				return false;
			}
		});

		editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
		editTextPassword.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (editTextPassword.getText().toString()
						.contains(SettingsEnum.PASSWORD.name())) {
					editTextPassword.getText().clear();
				}
				return false;
			}
		});

		final Button buttonSave = (Button) view.findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// pf.saveSettings(editTextHostIP.getText().toString(),
				// editTextHostPort
				// .getText().toString(), editTextUsername.getText().toString(),
				// editTextPassword.getText().toString());
			}
		});
		
		return view;
	}
}
