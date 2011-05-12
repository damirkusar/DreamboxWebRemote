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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import ch.kusar.dwr.preferences.Preferences;

public class RemoteCommands {
	private static URI uri = null;
	private static Credentials credentials = null;
	private static DefaultHttpClient httpClient = null;
	private static HttpResponse httpResponse = null;
	private static HttpGet httpGet = null;
	// path to remote. add ?code to change with remot
	private static final String remotePath = "/cgi-bin/rc";
	// path to epg. gets the epg
	private static final String epgPath = "/getcurrentepg";

	/**
	 * This Method generates and sends the command over http to the DreamBox.
	 * 
	 * @param code
	 *            This is the code for a remote-command.
	 */
	private static void keyCode(String code) {
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
					Integer.parseInt(Preferences.getPort()), remotePath, code,
					null);
			httpGet = new HttpGet(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			Log.e("RemoteCommands.keycode.URISyntaxException.64",
					e.getMessage());
			e.printStackTrace();
		}

		// executes the URI
		try {
			httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.e("RemoteCommands.keycode.ClientProtocolExeption.74",
					e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("RemoteCommands.keycode.IOExeption.79", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This Method gets the EPG over http from the DreamBox.
	 */
	private static void keyEPG() {
		// Creates the credentials with the settet username and password
		credentials = new UsernamePasswordCredentials(Preferences.getUser(),
				Preferences.getPass());

		// Creates a DefaultHttpClient
		httpClient = new DefaultHttpClient();

		// Sets the credentials to the httpClient
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY,
				credentials);

		try {
			// Creates the Uri with the single fragments
			uri = URIUtils.createURI("http", Preferences.getHost(),
					Integer.parseInt(Preferences.getPort()), epgPath,
					"channel", null);
			httpGet = new HttpGet(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			Log.e("RemoteCommands.keycode.URISyntaxException.64",
					e.getMessage());
			e.printStackTrace();
		}

		// executes the URI
		try {
			httpResponse = httpClient.execute(httpGet);
			org.apache.http.Header[] h = httpResponse.getAllHeaders();
			for (int i = 0; i < h.length; i++) {
				Log.e("RemoteCommands.EPG. Headers.name", h[i].getName());
				Log.e("RemoteCommands.EPG. Headers.value", h[i].getValue());
			}
			Log.e("RemoteCommands.EPG. Statusline",
					httpResponse.getStatusLine() + "");
			Log.e("RemoteCommands.EPG. local", httpResponse.getLocale() + "");
			Log.e("RemoteCommands.EPG. entity", httpResponse.getEntity() + "");
			Log.e("RemoteCommands.EPG. params", httpResponse.getParams() + "");
			Log.e("RemoteCommands.EPG. paramschannel", httpResponse.getParams()
					.getParameter("channel") + "");
			Log.e("RemoteCommands.EPG. protocolversion",
					httpResponse.getProtocolVersion() + "");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.e("RemoteCommands.keycode.ClientProtocolExeption.74",
					e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("RemoteCommands.keycode.IOExeption.79", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * switches to channel 1
	 */
	public static void code1() {
		// keyCode(Integer.toString(2));
		keyEPG();
	}
}
/**
 * Remote
 */
// <script>
// function key(code)
// {
// document.location="/cgi-bin/rc?" + code;
// if (1)
// window.setTimeout("osdShot()", 250);
// }
// function osdShot()
// {
// document.location="/cgi-bin/osdshot?display=no";
// window.setTimeout("document.location.reload()", 250);
// }
//
// </script>
// <map name="remotecontrol">
// <area shape="circle" coords="129, 54, 10" href="javascript:key(116)"
// alt="Power">
//
// <area shape="circle" coords="63, 123, 10" href="javascript:key(2)" alt="1">
// <area shape="circle" coords="109, 123, 10" href="javascript:key(3)" alt="2">
// <area shape="circle" coords="153, 123, 10" href="javascript:key(4)" alt="3">
//
// <area shape="circle" coords="63, 148, 10" href="javascript:key(5)" alt="4">
// <area shape="circle" coords="109, 148, 10" href="javascript:key(6)" alt="5">
// <area shape="circle" coords="153, 148, 10" href="javascript:key(7)" alt="6">
//
// <area shape="circle" coords="63, 173, 10" href="javascript:key(8)" alt="7">
// <area shape="circle" coords="109, 173, 10" href="javascript:key(9)" alt="8">
// <area shape="circle" coords="153, 173, 10" href="javascript:key(10)" alt="9">
//
// <area shape="circle" coords="63, 197, 10" href="javascript:key(412)"
// alt="previous">
// <area shape="circle" coords="109, 197, 10" href="javascript:key(11)" alt="0">
// <area shape="circle" coords="153, 197, 10" href="javascript:key(407)"
// alt="next">
//
// <area shape="circle" coords="54, 243, 15" href="javascript:key(115)"
// alt="volume up">
// <area shape="circle" coords="107, 233, 10" href="javascript:key(113)"
// alt="mute">
// <area shape="circle" coords="159, 243, 15" href="javascript:key(402)"
// alt="bouquet up">
//
// <area shape="circle" coords="66, 274, 15" href="javascript:key(114)"
// alt="volume down">
// <area shape="circle" coords="107, 258, 10" href="javascript:key(1)"
// alt="lame">
// <area shape="circle" coords="147, 274, 15" href="javascript:key(403)"
// alt="bouquet down">
//
// <area shape="circle" coords="48, 306, 10" href="javascript:key(358)"
// alt="info">
// <area shape="circle" coords="106, 310, 15" href="javascript:key(103)"
// alt="up">
// <area shape="circle" coords="167, 306, 10" href="javascript:key(141)"
// alt="dream">
//
// <area shape="circle" coords="70, 343, 15" href="javascript:key(105)"
// alt="left">
// <area shape="circle" coords="108, 340, 15" href="javascript:key(352)"
// alt="OK">
// <area shape="circle" coords="146, 343, 15" href="javascript:key(106)"
// alt="right">
//
// <area shape="circle" coords="53, 381, 10" href="javascript:key(392)"
// alt="audio">
// <area shape="circle" coords="106, 374, 15" href="javascript:key(108)"
// alt="down">
// <area shape="circle" coords="162, 381, 10" href="javascript:key(393)"
// alt="video">
//
// <area shape="circle" coords="56, 421, 10" href="javascript:key(398)"
// alt="red">
// <area shape="circle" coords="90, 422, 10" href="javascript:key(399)"
// alt="green">
// <area shape="circle" coords="123, 422, 10" href="javascript:key(400)"
// alt="yellow">
// <area shape="circle" coords="158, 421, 10" href="javascript:key(401)"
// alt="blue">
//
// <area shape="circle" coords="61, 460, 10" href="javascript:key(385)"
// alt="tv">
// <area shape="circle" coords="90, 461, 10" href="javascript:key(377)"
// alt="radio">
// <area shape="circle" coords="123, 461, 10" href="javascript:key(66)"
// alt="text">
// <area shape="circle" coords="153, 460, 10" href="javascript:key(138)"
// alt="help">

/**
 * dhtml.js
 */
// var DHTML = 0, DOM = 0, MS = 0, NS = 0, OP = 0;
//
// function DHTML_init() {
//
// if (window.opera) {
// OP = 1;
// }
// if(document.getElementById) {
// DHTML = 1;
// DOM = 1;
// }
// if(document.all && !OP) {
// DHTML = 1;
// MS = 1;
// }
// if(document.layers && !OP) {
// DHTML = 1;
// NS = 1;
// }
// }
//
// function getElem(p1,p2,p3) {
// var Elem;
// if(DOM) {
// if(p1.toLowerCase()=="id") {
// if (typeof document.getElementById(p2) == "object")
// Elem = document.getElementById(p2);
// else Elem = void(0);
// return(Elem);
// }
// else if(p1.toLowerCase()=="name") {
// if (typeof document.getElementsByName(p2) == "object")
// Elem = document.getElementsByName(p2)[p3];
// else Elem = void(0);
// return(Elem);
// }
// else if(p1.toLowerCase()=="tagname") {
// if (typeof document.getElementsByTagName(p2) == "object" ||
// (OP && typeof document.getElementsByTagName(p2) == "function"))
// Elem = document.getElementsByTagName(p2)[p3];
// else Elem = void(0);
// return(Elem);
// }
// else return void(0);
// }
// else if(MS) {
// if(p1.toLowerCase()=="id") {
// if (typeof document.all[p2] == "object")
// Elem = document.all[p2];
// else Elem = void(0);
// return(Elem);
// }
// else if(p1.toLowerCase()=="tagname") {
// if (typeof document.all.tags(p2) == "object")
// Elem = document.all.tags(p2)[p3];
// else Elem = void(0);
// return(Elem);
// }
// else if(p1.toLowerCase()=="name") {
// if (typeof document[p2] == "object")
// Elem = document[p2];
// else Elem = void(0);
// return(Elem);
// }
// else return void(0);
// }
// else if(NS) {
// if(p1.toLowerCase()=="id" || p1.toLowerCase()=="name") {
// if (typeof document[p2] == "object")
// Elem = document[p2];
// else Elem = void(0);
// return(Elem);
// }
// else if(p1.toLowerCase()=="index") {
// if (typeof document.layers[p2] == "object")
// Elem = document.layers[p2];
// else Elem = void(0);
// return(Elem);
// }
// else return void(0);
// }
// }
//
// DHTML_init();

/**
 * window.js
 */
// function NewWindow(mypage, myname, w, h, scroll, timeout)
// {
// if (screen.width >= 800)
// {
// var winl = (screen.width - w) / 2;
// var wint = (screen.height - h) / 2;
// winprops = 'height='+h+', width='+w+', top='+wint+', left='+winl+',
// scrollbars='+scroll+', resizable'
// win = window.open(mypage, myname, winprops)
// if (parseInt(navigator.appVersion) >= 4)
// win.window.focus();
// if (timeout > 0)
// win.window.setTimeout("close()", timeout);
// }
// else
// document.location = mypage;
// }
//
// function maximizeWindow()
// {
// top.window.moveTo(0, 0);
// if (document.all)
// {
// top.window.resizeTo(screen.availWidth, screen.availHeight);
// }
// else
// {
// top.window.outerHeight = screen.availHeight;
// top.window.outerWidth = screen.availWidth;
// }
// top.window.focus();
// }

/**
 * index.js
 */
// function setVol(volume)
// {
// document.location = "/setVolume?volume=" + volume;
// if (window.screen.width < 800)
// setTimeout("document.location.reload()", 1000);
// else
// {
// headerUpdateVolumeBar(volume, 0);
// data.location.reload();
// }
// }
//
// function toggleMute(xy)
// {
// document.location = "/setVolume?mute="+xy;
// if (window.screen.width < 800)
// setTimeout("document.location.reload()", 1000);
// else
// {
// headerUpdateVolumeBar(data.volume, 0);
// data.location.reload();
// }
// }
//
// function switchChannel(xy, bouquet, channel)
// {
// if (window.screen.width < 800)
// {
// NewWindow('/cgi-bin/zapTo?path='+xy+'&curBouquet='+bouquet+'&curChannel='+channel,
// 'zap', '1', '1', 'no');
// }
// else
// {
// if (zapMode < 4)
// document.location =
// "/cgi-bin/zapTo?path="+xy+"&curBouquet="+bouquet+"&curChannel="+channel;
// else
// document.location = "?path="+xy+"&mode=zap&zapmode=4&zapsubmode=1";
//
// setTimeout("parent.data.location.reload()", 500);
// }
// }
//
// function logging()
// {
// parent.body.document.location = "/log/debug.html";
// }
//
// function remoteControl(box)
// {
// if (box == "dbox2")
// NewWindow("/showRemoteControl", "RC", "165", "500", "no");
// else
// NewWindow("/showRemoteControl", "RC", "1024", "640", "no");
// }
//
// function satFinder(transponder)
// {
// NewWindow("/satFinder?" + transponder, "satfind", "170", "150", "no");
// }
//
// function openEPG(ref)
// {
// NewWindow('/getcurrentepg?type=extended&ref=' + ref, 'EPG', '780',
// screen.height, 'yes');
// }
//
// function openMultiEPG(ref)
// {
// NewWindow('/getMultiEPG?ref=' + ref, 'MultiEPG', screen.width, screen.height,
// 'yes');
// }
//
// function admin(command)
// {
// NewWindow('/cgi-bin/admin?command=' + command + '&requester=webif', 'admin',
// '200', '100', 'no', '3000');
// }
//
// function openSI()
// {
// NewWindow("/xml/streaminfo", "si", "780", "700", "yes");
// }
//
// function openChannelInfo()
// {
// NewWindow("/cgi-bin/channelinfo", "ci", "780", "600", "yes");
// }
//
// function DVRrecord(command)
// {
// document.location = "/cgi-bin/videocontrol?command=" + command;
// setTimeout("document.location.reload()", 500);
// }
//
// function sendMessage2TV()
// {
// NewWindow("/tvMessageWindow", "msg", "780", "175", "no");
// }
//
// function selectAudio()
// {
// NewWindow("/cgi-bin/selectAudio?requester=webif", "audio", "300", "150",
// "no");
// }
//
// function selectSubChannel()
// {
// NewWindow("/cgi-bin/selectSubChannel", "subchannel", "300", "130", "no");
// }
// function setStreamingServiceRef()
// {
// if (parent.data.serviceReference)
// document.location = "/cgi-bin/setStreamingServiceRef?sref=" +
// parent.data.serviceReference;
// else
// setTimeout("setStreamingServiceRef()", 200);
// }
// function vlc()
// {
// document.location = "/video.m3u";
// setTimeout("setStreamingServiceRef()", 200);
// }
