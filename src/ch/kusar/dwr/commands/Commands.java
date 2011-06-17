/**
 * This class is to send end receive data to and from Dreambox. 
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 * 
 */

package ch.kusar.dwr.commands;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.util.Log;
import ch.kusar.dwr.preferences.Preferences;

public class Commands {
	private URI uri = null;
	private Credentials credentials = null;
	private DefaultHttpClient httpClient = null;
	private HttpResponse httpResponse = null;
	private HttpPut httpPut = null;
	private HttpGet httpGet = null;
	// path to remote. add ?code to change with remot
	private final String remotePath = "/cgi-bin/rc";
	// path to epg. gets the epg
	private final String epgPath = "/getcurrentepg";
	// path to epg. gets the epg
	private final String epgRefPath = "/getcurrentepg";
	// path to send a message to the Dreambox
	private final String messagePath = "/cgi-bin/message";
	// path to bouquets TV channels
	private final String tvBouquetsAndChannelsPath = "/body?mode=zap&zapmode=0&zapsubmode=4";
	// path to all TV channels
	private final String tvAllPath = "/body?mode=zap&zapmode=0&zapsubmode=5";
	// path to bouquet radio channels
	private final String radioBouquetsAndChannelsPath = "/body?mode=zap&zapmode=1&zapsubmode=4";
	// path to all radio channels
	private final String radioAllPath = "/body?mode=zap&zapmode=1&zapsubmode=5";
	// path to recorded channels
	private final String recordedPath = "/body?mode=zap&zapmode=3&zapsubmode=1";
	// path to zapTopath
	private final String zapToPath = "/cgi-bin/zapTo"; // ?path=

	private ResponseHandler<String> responseHandler = new BasicResponseHandler();

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public DefaultHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(DefaultHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public HttpPut getHttpPut() {
		return httpPut;
	}

	public void setHttpPut(HttpPut httpPut) {
		this.httpPut = httpPut;
	}

	public HttpGet getHttpGet() {
		return httpGet;
	}

	public void setHttpGet(HttpGet httpGet) {
		this.httpGet = httpGet;
	}

	public ResponseHandler<String> getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler<String> responseHandler) {
		this.responseHandler = responseHandler;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public String getEpgPath() {
		return epgPath;
	}

	public String getMessagePath() {
		return messagePath;
	}

	public String getTvBouquetsPath() {
		return tvBouquetsAndChannelsPath;
	}

	public String getTvAllPath() {
		return tvAllPath;
	}

	public String getRadioBouquetsPath() {
		return radioBouquetsAndChannelsPath;
	}

	public String getRadioAllPath() {
		return radioAllPath;
	}

	public String getRecordedPath() {
		return recordedPath;
	}

	/**
	 * This Method sends data to the Dreambox.
	 * 
	 * @param url
	 * @param data
	 */
	public void connectToDreamBox(String url, String data) {
		// Creates the credentials with the settet username and password
		credentials = new UsernamePasswordCredentials(Preferences.getUser(),
				Preferences.getPass());

		// Creates a DefaultHttpClient
		httpClient = new DefaultHttpClient();

		// Sets the credentials to the httpClient
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY,
				credentials);

		try {
			// Creates the Uri with the single parts
			uri = URIUtils.createURI("http", Preferences.getHost(),
					Integer.parseInt(Preferences.getPort()), url,
					Uri.encode(data), null);
			httpGet = new HttpGet(uri);
		} catch (URISyntaxException e) {
			Log.e("RemoteCommands.sendMessage.URISyntaxException",
					e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * This Method sends data to the Dreambox.
	 * 
	 * @param url
	 * @param data
	 */
	public void connectToDreamBoxRefEPG(String data) {
		// Creates the credentials with the settet username and password
		credentials = new UsernamePasswordCredentials(Preferences.getUser(),
				Preferences.getPass());

		// Creates a DefaultHttpClient
		httpClient = new DefaultHttpClient();

		// Sets the credentials to the httpClient
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY,
				credentials);

		try {
			// Creates the Uri with the single parts
			uri = URIUtils.createURI("http", Preferences.getHost(),
					Integer.parseInt(Preferences.getPort()), "/getcurrentepg?ref="+data,
					null, null);
			httpGet = new HttpGet(uri);
		} catch (URISyntaxException e) {
			Log.e("RemoteCommands.sendMessage.URISyntaxException",
					e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Executes a Command. Used to send only a command without a need of get the
	 * HTML page.
	 */
	public void httpGetExecute() {
		try {
			httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			Log.e("RemoteCommands.sendMessage.ClientProtocolExeption",
					e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("RemoteCommands.sendMessage.IOExeption", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Executes a Command and saves the result in a string. This result is
	 * returned for parsing.
	 */
	public String httpGetExecuteResult() {
		String result = null;
		try {
			result = getHttpClient()
					.execute(getHttpGet(), getResponseHandler());
		} catch (ClientProtocolException e) {
			Log.e("RemoteCommands.getEPG.ClientProtocolExeption",
					e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("RemoteCommands.getEPG.IOExeption", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}