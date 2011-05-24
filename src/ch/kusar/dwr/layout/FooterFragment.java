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
import ch.kusar.dwr.content.Footer;

public class FooterFragment extends Fragment  {

	private SharedPreferences prefs = null;
	private SharedPreferences.Editor prefsEditor = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefsEditor = prefs.edit();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	/**
	 * This method creates the overview layout.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// View view = inflater.inflate(R.layout.overview, container, false);
		View view = new Footer(getFragmentManager()).getView(inflater,
				container, savedInstanceState);

		return view;
	}
}
