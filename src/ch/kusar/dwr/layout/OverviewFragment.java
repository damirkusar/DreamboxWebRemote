/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.layout;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import ch.kusar.dwr.R;
import ch.kusar.dwr.content.ContentSetup;
import ch.kusar.dwr.preferences.PreferencesFragment.PrefsFragment;
import ch.kusar.dwr.preferences.PreferencesActivity;

public class OverviewFragment extends Fragment {

	boolean mDualPane;
	int currentButton = ContentEnum.REMOTE.ordinal();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.overview, container, false);

		final ImageButton buttonRemote = (ImageButton) view
				.findViewById(R.id.buttonRemote);
		buttonRemote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.REMOTE.ordinal());
			}
		});

		final ImageButton buttonTVBouquet = (ImageButton) view
				.findViewById(R.id.buttonTVBouquets);
		buttonTVBouquet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.TV_BOUQUETS.ordinal());
			}
		});

		final ImageButton buttonEPG = (ImageButton) view
				.findViewById(R.id.buttonEPG);
		buttonEPG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.EPG.ordinal());
			}
		});

		final ImageButton buttonChannels = (ImageButton) view
				.findViewById(R.id.buttonChannels);
		buttonChannels.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.CHANNELS.ordinal());
			}
		});

		final ImageButton buttonRadioBouquet = (ImageButton) view
				.findViewById(R.id.buttonRadioBouquets);
		buttonRadioBouquet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.RADIO_BOUQUETS.ordinal());
			}
		});

		final ImageButton buttonRecorded = (ImageButton) view
				.findViewById(R.id.buttonRecorded);
		buttonRecorded.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDetails(ContentEnum.RECORDED.ordinal());
			}
		});

		final ImageButton buttonSetup = (ImageButton) view
				.findViewById(R.id.buttonSetup);
		buttonSetup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// showDetails(ContentEnum.SETUP.ordinal());
				// When the button is clicked, launch an activity through this
				//showDetails(ContentEnum.SETUP.ordinal());
				Intent intent = new Intent();
				intent.setClass(getActivity(), PreferencesActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View contentFrame = getActivity().findViewById(R.id.content); // hereChanged
		mDualPane = contentFrame != null
				&& contentFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			currentButton = savedInstanceState.getInt("selectedButton");
		}

		if (mDualPane) {
			// Make sure our UI is in the correct state.
			showDetails(currentButton);
		}
	}

	/**
	 * Helper function to show the details of a selected item, either by
	 * displaying a fragment in-place in the current UI, or starting a whole new
	 * activity in which it is displayed.
	 */
	void showDetails(int index) {
		currentButton = index;

		if (mDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			// getListView().setItemChecked(index, true);

			// Check what fragment is currently shown, replace if needed.
			ContentFragment cf = (ContentFragment) getFragmentManager()
					.findFragmentById(R.id.content); // /hereChanged
			if (cf == null || (cf.getPressedButton() != index)) {
				// Make new fragment to show this selection.
				cf = ContentFragment.newInstance(index);
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
			Intent intent = new Intent();
			intent.setClass(getActivity(), ContentActivity.class);
			// intent.putExtra("index", index);
			intent.putExtra("button", index);
			startActivity(intent);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("selectedButton", currentButton);
	}
}
