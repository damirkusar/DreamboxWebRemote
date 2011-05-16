/**
 * OverviewFragment class. Handles the Overview fragment.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import ch.kusar.dwr.R;
import ch.kusar.dwr.dialog.DialogMessageFragment;
import ch.kusar.dwr.dialog.DialogPromptFragment;
import ch.kusar.dwr.dialog.OnDialogDoneListener;
import ch.kusar.dwr.preferences.Preferences;
import ch.kusar.dwr.preferences.PreferencesActivity;
import ch.kusar.dwr.preferences.PreferencesEnum;

public class OverviewFragment extends Fragment implements OnDialogDoneListener {

	private boolean mDualPane;
	private int currentButton = ContentEnum.REMOTE.ordinal();
	private SharedPreferences prefs = null;
	private SharedPreferences.Editor prefsEditor = null;
	private final String DIALOG_PROMPT_TAG = "DIALOG_PROMPT_TAG";
	private final String DIALOG_MESSAGE_TAG = "DIALOG_MESSAGE_TAG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefsEditor = prefs.edit();

		if (prefs.getString(PreferencesEnum.HOST.name(),
				Preferences.getDefaultHost()).equals(
				Preferences.getDefaultHost())) {
			showDialog(DIALOG_PROMPT_TAG);
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View contentFrame = getActivity().findViewById(R.id.content); // hereChanged
		mDualPane = contentFrame != null
				&& contentFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			currentButton = savedInstanceState.getInt(ContentEnum.CURRENTBUTTON
					.name());
		}

		if (mDualPane) {
			// Make sure our UI is in the correct state.
			showDetails(currentButton);
		}
	}

	/**
	 * This method creates the overview layout.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.overview, container, false);

		final ImageButton buttonRemote = (ImageButton) view
				.findViewById(R.id.buttonRemote);
		buttonRemote.setBackgroundColor(Color.TRANSPARENT);
		buttonRemote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.REMOTE.ordinal());
			}
		});

		final ImageButton buttonTVBouquet = (ImageButton) view
				.findViewById(R.id.buttonTVBouquets);
		buttonTVBouquet.setBackgroundColor(Color.TRANSPARENT);
		buttonTVBouquet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.TV_BOUQUETS.ordinal());
			}
		});

		final ImageButton buttonEPG = (ImageButton) view
				.findViewById(R.id.buttonEPG);
		buttonEPG.setBackgroundColor(Color.TRANSPARENT);
		buttonEPG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.EPG.ordinal());
			}
		});

		final ImageButton buttonChannels = (ImageButton) view
				.findViewById(R.id.buttonChannels);
		buttonChannels.setBackgroundColor(Color.TRANSPARENT);
		buttonChannels.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.CHANNELS.ordinal());
			}
		});

		final ImageButton buttonRadioBouquet = (ImageButton) view
				.findViewById(R.id.buttonRadioBouquets);
		buttonRadioBouquet.setBackgroundColor(Color.TRANSPARENT);
		buttonRadioBouquet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.RADIO_BOUQUETS.ordinal());
			}
		});

		final ImageButton buttonRecorded = (ImageButton) view
				.findViewById(R.id.buttonRecorded);
		buttonRecorded.setBackgroundColor(Color.TRANSPARENT);
		buttonRecorded.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.RECORDED.ordinal());
			}
		});

		final ImageButton buttonSetup = (ImageButton) view
				.findViewById(R.id.buttonSetup);
		buttonSetup.setBackgroundColor(Color.TRANSPARENT);
		buttonSetup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showPreferences();
			}
		});

		final ImageButton buttonMessage = (ImageButton) view
				.findViewById(R.id.buttonMessage);
		buttonMessage.setBackgroundColor(Color.TRANSPARENT);
		buttonMessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_MESSAGE_TAG);
			}
		});

		return view;
	}

	private void showPreferences() {
		Intent intent = new Intent();
		intent.setClass(getActivity(), PreferencesActivity.class);
		startActivity(intent);
	}

	private void showDialog(String dialog) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (dialog.contains(DIALOG_MESSAGE_TAG)) {
			DialogMessageFragment dmf = DialogMessageFragment
					.newInstance("null");
			dmf.show(ft, dialog);
		} else if (dialog.contains(DIALOG_PROMPT_TAG)) {
			DialogPromptFragment dpf = DialogPromptFragment.newInstance("null");
			dpf.show(ft, dialog);
		}
	}

	/**
	 * Helper function to show the details of a selected item, either by
	 * displaying a fragment in-place in the current UI, or starting a whole new
	 * activity in which it is displayed.
	 */
	private void showDetails(int index) {
		currentButton = index;

		if (mDualPane) {
			// We can display everything in-place with fragments.

			// Check what fragment is currently shown, replace if needed.
			ContentFragment cf = (ContentFragment) getFragmentManager()
					.findFragmentById(R.id.content); // /hereChanged

			// if (cf == null || (cf.getPressedButton() != index)) {
			if (cf == null
					|| (prefs.getInt(ContentEnum.PRESSEDBUTTON.name(), 0) != index)) {
				setPressedButton(index);

				// Make new fragment to show this selection.
				cf = ContentFragment.newInstance(index);
				// cf = ContentFragment.getInstance();

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.content, cf); // /hereChanged
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			setPressedButton(index);
			Intent intent = new Intent();
			intent.setClass(getActivity(), ContentActivity.class);
			intent.putExtra("button", index);
			startActivity(intent);
		}
	}

	private void setPressedButton(int index) {
		prefsEditor.putInt(ContentEnum.PRESSEDBUTTON.name(), index);
		prefsEditor.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ContentEnum.CURRENTBUTTON.name(), currentButton);
	}

	/**
	 * The Callback Method from OnDialogDoneListener.
	 */
	public void onDialogDone(String tag, boolean cancelled, String message) {
		String s = tag + "; responds with: " + message;
		showPreferences();
		if (cancelled) {
			s = "Please set the preferences before using the App";
			Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
		}
		Log.e("OverviewFragment.onDialogDone.message", s);
	}
}
