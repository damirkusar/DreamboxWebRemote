/**
 * This class creates the preferences content.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 10.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.preferences;

import ch.kusar.dwr.R;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.util.Log;

public class PreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("PreferencesActivity.onCreate", "onCreate");
		setPreferenceScreen(createPreferenceHierarchy());
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("PreferencesActivity.onStop", "onStop");
	}

	private PreferenceScreen createPreferenceHierarchy() {
		// Root
		PreferenceScreen root = getPreferenceManager().createPreferenceScreen(
				this);

		// Dialog based preferences
		PreferenceCategory dialogBasedPrefCat = new PreferenceCategory(this);
		dialogBasedPrefCat.setTitle(R.string.pref_title);
		root.addPreference(dialogBasedPrefCat);

		// Edit text preference
		EditTextPreference editTextPHost = new EditTextPreference(this);
		editTextPHost.setDialogTitle(R.string.pref_host_dialog_title);
		editTextPHost.setTitle(R.string.pref_host_title);
		editTextPHost
				.setSummary(R.string.pref_host_summary);
		editTextPHost.setDefaultValue("192.168.2.30");
		editTextPHost.setPersistent(true);
		editTextPHost.setKey(PreferencesEnum.HOST.name());
		dialogBasedPrefCat.addPreference(editTextPHost);

		// Edit text preference
		EditTextPreference editTextPrefPort = new EditTextPreference(this);
		editTextPrefPort.setDialogTitle(R.string.pref_port_dialog_title);
		editTextPrefPort.setTitle(R.string.pref_port_title);
		editTextPrefPort
				.setSummary(R.string.pref_port_summary);
		editTextPrefPort.setDefaultValue("8030");
		editTextPrefPort.setPersistent(true);
		editTextPrefPort.setKey(PreferencesEnum.PORT.name());
		dialogBasedPrefCat.addPreference(editTextPrefPort);

		// Edit text preference
		EditTextPreference editTextPrefUser = new EditTextPreference(this);
		editTextPrefUser.setDialogTitle(R.string.pref_user_dialog_title);
		editTextPrefUser.setTitle(R.string.pref_user_title);
		editTextPrefUser
				.setSummary(R.string.pref_user_summary);
		editTextPrefUser.setDefaultValue("root");
		editTextPrefUser.setPersistent(true);
		editTextPrefUser.setKey(PreferencesEnum.USERNAME.name());
		dialogBasedPrefCat.addPreference(editTextPrefUser);

		// Edit text preference
		EditTextPreference editTextPrefPass = new EditTextPreference(this);
		editTextPrefPass.setDialogTitle(R.string.pref_pass_dialog_title);
		editTextPrefPass.setTitle(R.string.pref_pass_title);
		editTextPrefPass
				.setSummary(R.string.pref_pass_summary);
		editTextPrefPass.setDefaultValue("dreambox");
		editTextPrefPass.setPersistent(true);
		editTextPrefPass.setKey(PreferencesEnum.PASSWORD.name());
		dialogBasedPrefCat.addPreference(editTextPrefPass);

		return root;
	}
}
