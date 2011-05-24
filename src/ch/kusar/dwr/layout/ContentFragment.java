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
import ch.kusar.dwr.content.ContentMEpg;
import ch.kusar.dwr.content.ContentEpg;
import ch.kusar.dwr.content.ContentRadio;
import ch.kusar.dwr.content.ContentRecorded;
import ch.kusar.dwr.content.ContentRemote;
import ch.kusar.dwr.content.ContentTV;
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
		Preferences.setPreferences(getActivity());
		Log.d("ContentFragment.onCreate", "onCreate");
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("ContentFragment.onPause", "onPause");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("ContentFragment.onStop", "onStop");
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
		Log.d("ContentFragment.onCreateView", "onCreateView");
		// TODO Auto-generated method stub
		if (container == null) {
			// // We have different layouts, and in one of them this
			// // fragment's containing frame doesn't exist. The fragment
			// // may still be created from its saved state, but there is
			// // no reason to try to create its view hierarchy because it
			// // won't be displayed. Note this is not needed -- we could
			// // just run the code below, where we would create and return
			// // the view hierarchy; it would just never be used.
			// return null;
		}

		if (getPressedButton() == ButtonCommandEnum.REMOTE.ordinal()) {
			view = ContentRemote.getInstance().getView(inflater,
					container, savedInstanceState);
		}
		if (getPressedButton() == ButtonCommandEnum.TV.ordinal()) {
			view = ContentTV.getInstance().getView(inflater,
					container, savedInstanceState);
		}
		if (getPressedButton() == ButtonCommandEnum.EPG.ordinal()) {
			view = ContentEpg.getInstance().getView(inflater, container,
					savedInstanceState);
		}
		if (getPressedButton() == ButtonCommandEnum.MEPG.ordinal()) {
			view = ContentMEpg.getInstance().getView(inflater,
					container, savedInstanceState);
		}
		if (getPressedButton() == ButtonCommandEnum.RADIO.ordinal()) {
			view = ContentRadio.getInstance().getView(
					inflater, container, savedInstanceState);
		}
		if (getPressedButton() == ButtonCommandEnum.RECORDED.ordinal()) {
			view = ContentRecorded.getInstance().getView(inflater,
					container, savedInstanceState);
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
		return prefs.getInt(ButtonCommandEnum.PRESSEDBUTTON.name(),
				ButtonCommandEnum.REMOTE.ordinal());
	}
}