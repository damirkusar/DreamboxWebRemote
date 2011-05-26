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
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import ch.kusar.dwr.R;
import ch.kusar.dwr.preferences.Preferences;

public class ContentEpgWebView implements View.OnClickListener{
	private static ContentEpgWebView instance = null;

	public static ContentEpgWebView getInstance() {
		if (instance == null) {
			instance = new ContentEpgWebView();
		}
		return instance;
	}

	/**
	 * Generates a view from the epg layout.
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return View. The generated View.
	 */
	public View getView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.content_epg, container, false);

		final WebView webViewEPG = (WebView) view.findViewById(R.id.webView1);
		webViewEPG.setWebViewClient(new MyWebClient());
		webViewEPG.getSettings().setJavaScriptEnabled(true);
		webViewEPG.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		//webViewEPG.loadUrl(new EpgCommands().getEp);

		return view;
	}

	static class MyWebClient extends WebViewClient {

		@Override
		public void onReceivedHttpAuthRequest(WebView view,
				HttpAuthHandler handler, String host, String realm) {
			handler.proceed(Preferences.getUser(), Preferences.getPass());
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
