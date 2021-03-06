/**
 * OverviewFragment class. Handles the Overview fragment.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.layout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ch.kusar.dwr.content.StartScreen;

public class MainFragment extends Fragment { // implements
												// DialogListenerSettings,
	// ShowDetailsListener {

	private boolean mDualPane;
	private int currentButton = ButtonCommandEnum.REMOTE.ordinal();
	private SharedPreferences prefs = null;
	private SharedPreferences.Editor prefsEditor = null;
	private final String DIALOG_SETTINGS_TAG = "DIALOG_SETTINGS_TAG";
	private final String DIALOG_MESSAGE_TAG = "DIALOG_MESSAGE_TAG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefsEditor = prefs.edit();

//		if (prefs.getString(PreferencesEnum.HOST.name(),
//				Preferences.getDefaultHost()).equals(
//				Preferences.getDefaultHost())) {
//			showDialog(DIALOG_SETTINGS_TAG);
//		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		// Check to see if we have a frame in which to embed the details
//		// fragment directly in the containing UI.
//		View contentFrame = getActivity().findViewById(R.id.content); // hereChanged
//		mDualPane = contentFrame != null
//				&& contentFrame.getVisibility() == View.VISIBLE;
//
//		if (savedInstanceState != null) {
//			// Restore last state for checked position.
//			currentButton = savedInstanceState
//					.getInt(ButtonCommandEnum.CURRENTBUTTON.name());
//		}
//
//		if (mDualPane) {
//			// Make sure our UI is in the correct state.
//			showDetails(currentButton);
//		}
//	}

	/**
	 * This method creates the overview layout.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// View view = inflater.inflate(R.layout.overview, container, false);
		View view = new StartScreen(getFragmentManager()).getView(
				inflater, container, savedInstanceState);

		return view;
	}

//	private void showPreferences() {
//		Intent intent = new Intent();
//		intent.setClass(getActivity(), PreferencesActivity.class);
//		startActivity(intent);
//	}
//
//	private void showDialog(String dialog) {
//		FragmentTransaction ft = getFragmentManager().beginTransaction();
//		if (dialog.contains(DIALOG_MESSAGE_TAG)) {
//			MessageDialogFragment dmf = MessageDialogFragment
//					.newInstance("null");
//			dmf.show(ft, dialog);
//		} else if (dialog.contains(DIALOG_SETTINGS_TAG)) {
//			SettingsDialogFragment dpf = SettingsDialogFragment
//					.newInstance("null");
//			dpf.show(ft, dialog);
//		}
//	}

	/**
	 * Helper function to show the details of a selected item, either by
	 * displaying a fragment in-place in the current UI, or starting a whole new
	 * activity in which it is displayed.
	 */
//	private void showDetails(int index) {
//		currentButton = index;
//
//		if (mDualPane) {
//			// We can display everything in-place with fragments.
//
//			// Check what fragment is currently shown, replace if needed.
//			ContentFragment cf = (ContentFragment) getFragmentManager()
//					.findFragmentById(R.id.content); // /hereChanged
//
//			// if (cf == null || (cf.getPressedButton() != index)) {
//			if (cf == null
//					|| (prefs.getInt(ButtonCommandEnum.PRESSEDBUTTON.name(), 0) != index)) {
//				setPressedButton(index);
//
//				// Make new fragment to show this selection.
//				cf = ContentFragment.newInstance(index);
//				// cf = ContentFragment.getInstance();
//
//				// Execute a transaction, replacing any existing fragment
//				// with this one inside the frame.
//				FragmentTransaction ft = getFragmentManager()
//						.beginTransaction();
//				ft.replace(R.id.content, cf); // /hereChanged
//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//				ft.commit();
//			}
//		} else {
//			// Otherwise we need to launch a new activity to display
//			// the dialog fragment with selected text.
//			setPressedButton(index);
//			Intent intent = new Intent();
//			intent.setClass(getActivity(), ContentActivity.class);
//			intent.putExtra("button", index);
//			startActivity(intent);
//		}
//	}

	private void setPressedButton(int index) {
		prefsEditor.putInt(ButtonCommandEnum.PRESSEDBUTTON.name(), index);
		prefsEditor.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ButtonCommandEnum.CURRENTBUTTON.name(), currentButton);
	}

	// /**
	// * The Callback Method from DialogListenerSettings.
	// */
	// @Override
	// public void onDialogDone(String tag, boolean cancelled, String message) {
	// String s = "";
	// if (!cancelled) {
	// s = tag + "; responds with: " + message;
	// showPreferences();
	// } else if (cancelled) {
	// s = "Please set the preferences before using the App";
	// Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
	// }
	// Log.e("OverviewFragment.onDialogDone.message", s);
	// }
	//
	// /**
	// * The Callback Method from ShowDetailsListener.
	// */
	// @Override
	// public void onShow(int details) {
	// if (details == ButtonCommandEnum.MESSAGE.ordinal()) {
	// showDialog(DIALOG_MESSAGE_TAG);
	// } else if (details == ButtonCommandEnum.SEARCH.ordinal()) {
	//
	// } else if (details == ButtonCommandEnum.REFRESH.ordinal()) {
	//
	// } else {
	// showDetails(details);
	// }
	// }
}
