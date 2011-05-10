/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import ch.kusar.dwr.R;

public class ContentSetup {
	private static EditText editTextHostIP = null;
	private static EditText editTextHostPort = null;
	private static EditText editTextUsername = null;
	private static EditText editTextPassword = null;

	public static View getSetupView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.setup, container, false);

		editTextHostIP = (EditText) view.findViewById(R.id.editTextHostIP);
		editTextHostIP.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (editTextHostIP.getText().toString().contains("Host")) {
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
				if (editTextHostPort.getText().toString().contains("Port")) {
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
				if (editTextUsername.getText().toString().contains("Username")) {
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
				if (editTextPassword.getText().toString().contains("Password")) {
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

			}
		});

		return view;
	}

	public static void onSaveInstanceState(Bundle outState) {
//		outState.putString("host", editTextHostIP.getText().toString());
//		outState.putString("port", editTextHostPort.getText().toString());
//		outState.putString("user", editTextUsername.getText().toString());
//		outState.putString("pass", editTextPassword.getText().toString());
	}
	
	public static void onActivityCreated(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			// Restore last state for checked position.
//			editTextHostIP.setText(savedInstanceState.getString("host"));
//			editTextHostPort.setText(savedInstanceState.getString("port"));
//			editTextUsername.setText(savedInstanceState.getString("user"));
//			editTextPassword.setText(savedInstanceState.getString("pass"));
		}
	}

}
