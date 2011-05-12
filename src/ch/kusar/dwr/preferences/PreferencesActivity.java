/**
 * This class creates the preferences content.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 10.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.preferences;

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
        PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);

        // Dialog based preferences
        PreferenceCategory dialogBasedPrefCat = new PreferenceCategory(this);
        dialogBasedPrefCat.setTitle("Dreambox settings");
        root.addPreference(dialogBasedPrefCat);

        // Edit text preference
        EditTextPreference editTextPHost = new EditTextPreference(this);
        editTextPHost.setDialogTitle("Set IP or Hostname");
        editTextPHost.setTitle("Hostname or IP");
        editTextPHost.setDefaultValue("192.168.2.30");
        editTextPHost.setSummary("Set here the IP or hostname from the Dreambox");
        editTextPHost.setPersistent(true);
        editTextPHost.setKey(PreferencesEnum.HOST.name());
        dialogBasedPrefCat.addPreference(editTextPHost);
        
     // Edit text preference
        EditTextPreference editTextPrefPort = new EditTextPreference(this);
        editTextPrefPort.setDialogTitle("Set Port");
        editTextPrefPort.setTitle("Port");
        editTextPrefPort.setDefaultValue("8030");
        editTextPrefPort.setSummary("Set here the port from the Dreambox (default 80)");
        editTextPrefPort.setPersistent(true);
        editTextPrefPort.setKey(PreferencesEnum.PORT.name());
        dialogBasedPrefCat.addPreference(editTextPrefPort);
        
     // Edit text preference
        EditTextPreference editTextPrefUser = new EditTextPreference(this);
        editTextPrefUser.setDialogTitle("Set Username");
        editTextPrefUser.setTitle("Username");
        editTextPrefUser.setDefaultValue("root");
        editTextPrefUser.setSummary("Set the username of the Dreambox (default root)");
        editTextPrefUser.setPersistent(true);
        editTextPrefUser.setKey(PreferencesEnum.USERNAME.name());
        dialogBasedPrefCat.addPreference(editTextPrefUser);
        
     // Edit text preference
        EditTextPreference editTextPrefPass = new EditTextPreference(this);
        editTextPrefPass.setDialogTitle("Set Password");
        editTextPrefPass.setTitle("Password");
        editTextPrefPass.setDefaultValue("dreambox");
        editTextPrefPass.setSummary("Set the password of the Dreambox (default dreambox)");
        editTextPrefPass.setPersistent(true);
        editTextPrefPass.setKey(PreferencesEnum.PASSWORD.name());
        dialogBasedPrefCat.addPreference(editTextPrefPass);

        return root;
    }
}
