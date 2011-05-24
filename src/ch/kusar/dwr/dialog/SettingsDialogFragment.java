/**
 * Creates the Dialog on Startup for the preferences.
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
import android.widget.TextView;
import ch.kusar.dwr.R;

public class SettingsDialogFragment extends DialogFragment implements
		View.OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setCancelable(true);
		int style = DialogFragment.STYLE_NORMAL, theme = android.R.style.Theme_Black_NoTitleBar;
		setStyle(style, theme);
	}

	// @Override
	// public void onAttach(Activity act) {
	// OnDialogDoneListener oddl = (OnDialogDoneListener) act;
	// super.onAttach(act);
	// }

	@Override
	public void onCancel(DialogInterface dialog) {
		Log.v("DialogFragment.onCancel", "");
		super.onCancel(dialog);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		Log.v("DialogFragment.onDismiss", "");
		super.onDismiss(dialog);
	}

	@Override
	public void onSaveInstanceState(Bundle arg0) {
		Log.v("DialogFragment.onSaveInstanceState", "");
		super.onSaveInstanceState(arg0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.dialog_settings, container,
				false);

		TextView tv = (TextView) v.findViewById(R.id.TextView_Dialog_Prompt);
		if (!(getArguments().getString("prompt").contains("null"))) {
			tv.setText(getArguments().getString("prompt"));
		}

		Button btn_yes = (Button) v.findViewById(R.id.btn_dialog_yes);
		btn_yes.setOnClickListener(this);

		Button btn_no = (Button) v.findViewById(R.id.btn_dialog_no);
		btn_no.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {
		DialogListenerSettings act = (DialogListenerSettings) getFragmentManager()
				.findFragmentById(R.id.headerfragment);
		if (v.getId() == R.id.btn_dialog_yes) {
			act.onDialogDone(this.getTag(), false, "go");
			dismiss();
			return;
		}
		if (v.getId() == R.id.btn_dialog_no) {
			act.onDialogDone(this.getTag(), true, null);
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
	public static SettingsDialogFragment newInstance(String prompt) {
		SettingsDialogFragment dpf = new SettingsDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putString("prompt", prompt);
		dpf.setArguments(bundle);
		return dpf;
	}

}
