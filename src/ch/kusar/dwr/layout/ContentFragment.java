/**
 * ContentFragment class. Handles the Content fragment.
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ch.kusar.dwr.content.ContentChannels;
import ch.kusar.dwr.content.ContentEpg;
import ch.kusar.dwr.content.ContentMessage;
import ch.kusar.dwr.content.ContentRadioBouquets;
import ch.kusar.dwr.content.ContentRecorded;
import ch.kusar.dwr.content.ContentRemote;
import ch.kusar.dwr.content.ContentTVBouquets;
import ch.kusar.dwr.preferences.Preferences;

public class ContentFragment extends Fragment {
	View view = null;
	SharedPreferences prefs = null;
	SharedPreferences.Editor prefsEdit = null;
	private static ContentFragment contentFragmentInstance = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("ContentFragment.onCreate", "onCreate");
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("ContentFragment.onPause", "onPause");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("ContentFragment.onStop", "onStop");
		Preferences.setPreferences(getActivity());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("ContentFragment.onCreateView", "onCreateView");
		// TODO Auto-generated method stub
		if (container == null) {
			// // We have different layouts, and in one of them this
			// // fragment's containing frame doesn't exist. The fragment
			// // may still be created from its saved state, but there is
			// // no reason to try to create its view hierarchy because it
			// // won't be displayed. Note this is not needed -- we could
			// // just run the code below, where we would create and return
			// // the view hierarchy; it would just never be used.
			//return null;
		}

		if (getPressedButton() == ContentEnum.REMOTE.ordinal()) {
			view = ContentRemote.getRemoteView(inflater, container,
					savedInstanceState);
		}
		if (getPressedButton() == ContentEnum.TV_BOUQUETS.ordinal()) {
			view = ContentTVBouquets.getTVBouquetsView(inflater, container,
					savedInstanceState);
		}
		if (getPressedButton() == ContentEnum.EPG.ordinal()) {
			view = ContentEpg.getEpgView(inflater, container,
					savedInstanceState);
		}
		if (getPressedButton() == ContentEnum.CHANNELS.ordinal()) {
			view = ContentChannels.getChannelsView(inflater, container,
					savedInstanceState);
		}
		if (getPressedButton() == ContentEnum.RADIO_BOUQUETS.ordinal()) {
			view = ContentRadioBouquets.getRadioBouquetsView(inflater,
					container, savedInstanceState);
		}
		if (getPressedButton() == ContentEnum.RECORDED.ordinal()) {
			view = ContentRecorded.getRecordedView(inflater, container,
					savedInstanceState);
		}
		if (getPressedButton() == ContentEnum.SETUP.ordinal()) {
			// Intent intent = new Intent();
			// intent.setClass(getActivity(), PreferencesFragment.class);
			// startActivity(intent);
		}
		if (getPressedButton() == ContentEnum.MESSAGE.ordinal()) {
			view = ContentMessage.getMessageView(inflater, container,
					savedInstanceState);
		}

		return view;
	}

	public static ContentFragment newInstance(int index) {
		ContentFragment cf = new ContentFragment();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("button", index);
		cf.setArguments(args);
		return cf;
	}

	public static ContentFragment getInstance() {
		if (contentFragmentInstance == null) {
			contentFragmentInstance = new ContentFragment();
		}
		return contentFragmentInstance;
	}

	public int getPressedButton() {
		// return getArguments().getInt("button", ContentEnum.REMOTE.ordinal());
		return prefs.getInt(ContentEnum.PRESSEDBUTTON.name(),
				ContentEnum.REMOTE.ordinal());
	}
}