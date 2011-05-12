/**
 * This class is to start the Preferences as a Fragment. Works only since API Level 11.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.preferences;

import ch.kusar.dwr.R;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Demonstration of PreferenceFragment, showing a single fragment in an
 * activity.
 */
public class PreferencesFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.content, new PrefsFragment()); // /hereChanged
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
		
//		getFragmentManager().beginTransaction()
//				.replace(R.id.content, new PrefsFragment()).commit();
	}

	public static class PrefsFragment extends PreferenceFragment {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.preferencesfragment);
		}
	}
}
