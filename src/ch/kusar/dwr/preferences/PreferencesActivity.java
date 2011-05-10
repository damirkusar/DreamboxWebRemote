/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.kusar.dwr.preferences;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import ch.kusar.dwr.setup.SettingsEnum;

public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPreferenceScreen(createPreferenceHierarchy());
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
        editTextPHost.setSummary("Set here the IP or hostname from the Dreambox");
        editTextPHost.setPersistent(true);
        editTextPHost.setKey(SettingsEnum.HOST.name());
        dialogBasedPrefCat.addPreference(editTextPHost);
        
     // Edit text preference
        EditTextPreference editTextPrefPort = new EditTextPreference(this);
        editTextPrefPort.setDialogTitle("Set Port");
        editTextPrefPort.setTitle("Port");
        editTextPrefPort.setDefaultValue("80");
        editTextPrefPort.setSummary("Set here the port from the Dreambox (default 80)");
        editTextPrefPort.setPersistent(true);
        editTextPrefPort.setKey(SettingsEnum.PORT.name());
        dialogBasedPrefCat.addPreference(editTextPrefPort);
        
     // Edit text preference
        EditTextPreference editTextPrefUser = new EditTextPreference(this);
        editTextPrefUser.setDialogTitle("Set Username");
        editTextPrefUser.setTitle("Username");
        editTextPrefUser.setDefaultValue("root");
        editTextPrefUser.setSummary("Set the username of the Dreambox (default root)");
        editTextPrefUser.setPersistent(true);
        editTextPrefUser.setKey(SettingsEnum.USERNAME.name());
        dialogBasedPrefCat.addPreference(editTextPrefUser);
        
     // Edit text preference
        EditTextPreference editTextPrefPass = new EditTextPreference(this);
        editTextPrefPass.setDialogTitle("Set Password");
        editTextPrefPass.setTitle("Password");
        editTextPrefPass.setSummary("Set the password of the Dreambox");
        editTextPrefPass.setPersistent(true);
        editTextPrefPass.setKey(SettingsEnum.PASSWORD.name());
        dialogBasedPrefCat.addPreference(editTextPrefPass);

        return root;
    }
}
