/**
 * Creates the message content.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import ch.kusar.dwr.R;
import ch.kusar.dwr.commands.RemoteCommands;

public class ContentMessage {

	/**
	 * Generates a view from the message layout.
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return View. The generated View.
	 */
	public static View getMessageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.message, container, false);

		final EditText editTextMessage = (EditText) view.findViewById(R.id.editTextMessage);

		final Button buttonSend = (Button) view.findViewById(R.id.buttonSend);
		buttonSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RemoteCommands.sendMessage(editTextMessage.getText().toString());
				editTextMessage.getText().clear();
			}
		});
		return view;
	}
}
