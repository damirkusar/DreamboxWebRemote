/**
 * Creates the epg content.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import ch.kusar.dwr.R;
import ch.kusar.dwr.commands.RemoteCommands;
import ch.kusar.dwr.preferences.Preferences;

public class ContentEpg {

	/**
	 * Generates a view from the epg layout.
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return View. The generated View.
	 */
	public static View getEpgView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.epg, container, false);

		final WebView webViewEPG = (WebView) view.findViewById(R.id.webView1);
		webViewEPG.getSettings().setJavaScriptEnabled(true);
		webViewEPG.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webViewEPG.setHttpAuthUsernamePassword(RemoteCommands.getEPGURI(), "",
				Preferences.getUser(), Preferences.getPass());
		webViewEPG.savePassword(Preferences.getHost(), Preferences.getUser(),
				Preferences.getPass());
		webViewEPG.loadUrl(RemoteCommands.getEPGURI());

		return view;
	}
}
