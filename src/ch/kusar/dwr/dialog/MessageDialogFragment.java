/**
 * Creates the Dialog for message sending.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 16.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ch.kusar.dwr.R;
import ch.kusar.dwr.commands.MessageCommands;
import ch.kusar.dwr.preferences.Preferences;

public class MessageDialogFragment extends DialogFragment implements
		View.OnClickListener {

	private EditText et = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setCancelable(true);
		int style = DialogFragment.STYLE_NORMAL, theme = android.R.style.Theme_Black_NoTitleBar;
		setStyle(style, theme);
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		Log.v("DialogMessage.onCancel", "");
		super.onCancel(dialog);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		Log.v("DialogMessage.onDismiss", "");
		super.onDismiss(dialog);
	}

	@Override
	public void onSaveInstanceState(Bundle arg0) {
		Log.v("DialogMessage.onSaveInstanceState", "");
		super.onSaveInstanceState(arg0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.dialog_message, container, false);

		TextView tv = (TextView) v.findViewById(R.id.TextView_Dialog_Message);
		if (!(getArguments().getString("prompt").contains("null"))) {
			tv.setText(getArguments().getString("prompt"));
		}

		et = (EditText) v.findViewById(R.id.EditText_Dialog_Message);

		Button btn_send = (Button) v.findViewById(R.id.btn_dialog_send);
		btn_send.setOnClickListener(this);

		Button btn_cancel = (Button) v.findViewById(R.id.btn_dialog_cancel);
		btn_cancel.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_dialog_send) {
			// EditText et = (EditText) v
			// .findViewById(R.id.EditText_Dialog_Message);
			Preferences.setPreferences(getActivity());
			new MessageCommands().sendMessage(et.getText().toString());
			dismiss();
			return;
		}
		if (v.getId() == R.id.btn_dialog_cancel) {
			dismiss();
			return;
		}
	}

	/**
	 * 
	 * @param prompt
	 *            . If "null" then the default is used from
	 *            res/values/strings.xml
	 * @return DialogPromptFragment
	 */
	public static MessageDialogFragment newInstance(String prompt) {
		MessageDialogFragment dpf = new MessageDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putString("prompt", prompt);
		dpf.setArguments(bundle);
		return dpf;
	}

}
