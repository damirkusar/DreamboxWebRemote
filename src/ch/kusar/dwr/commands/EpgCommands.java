/**
 * This class is to send end receive data to and from Dreambox. 
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 * 
 */

package ch.kusar.dwr.commands;

import ch.kusar.dwr.dataobjects.Epg;

public class EpgCommands extends Commands {

	/**
	 * This Method gets the EPG over http from the DreamBox.
	 */
	public void loadCurrentEPG() {
		httpGetDreamBox(getEpgPath(), null);
		Epg.getEpgList().clear();
		epgStringParser(httpGetExecuteResult());
	}
	
	public void loadRefEPG(String ref) {
		httpGetDreamBox(getEpgPath(), ref);
		Epg.getEpgList().clear();
		epgStringParser(httpGetExecuteResult());
	}

	private void epgStringParser(String htmlpage) {
		Epg epg = null;
		String[] middleSplit = htmlpage.split("middle");
		String[] recordLink = null;
		String[] description = null;
		String[] timeDate = null;

		for (int i = 1; i < middleSplit.length; i++) {
			epg = new Epg();
			recordLink = middleSplit[i].split("'");

			timeDate = middleSplit[i].split("time");
			timeDate = timeDate[1].split(">");
			timeDate = timeDate[1].split("<");
			timeDate = timeDate[0].split(" ");

			description = middleSplit[i].split(">");
			description = description[description.length - 4].split("<");

			if (recordLink[7].contains("\\")) {
				System.out.println("NOOOOOO");
				System.out.println(recordLink[7]);
				epg.setShow(recordLink[7].replaceAll("\\\\", "")
						+ recordLink[8]);
				epg.setChannel(recordLink[10]);
				epg.setTimerendaction(recordLink[12]);
			} else {
				epg.setShow(recordLink[7]);
				epg.setChannel(recordLink[9]);
				epg.setTimerendaction(recordLink[11]);
			}
			epg.setRef(recordLink[1]);
			epg.setStart(recordLink[3]);
			epg.setDuration(recordLink[5]);
			epg.setDescription(description[0]);
			epg.setDate(timeDate[0]);
			epg.setTime(timeDate[1]);
			Epg.getEpgList().add(epg);
		}
		System.out.println("Start desc Damir Kusar");
		for (int i = 0; i < Epg.getEpgList().size(); i++) {
			System.out.println(Epg.getEpgList().get(i).toString());
		}
		System.out.println("Ende Damir Kusar");
	}

//	public String getEPGURI() {
//		try {
//			// Creates the Uri with the single parts
//			// Creates the Uri with the single parts
//			setUri(URIUtils.createURI("http", Preferences.getHost(),
//					Integer.parseInt(Preferences.getPort()), getEpgPath(),
//					null, null));
//		} catch (URISyntaxException e) {
//			Log.e("RemoteCommands.getEPGURI.URISyntaxException", e.getMessage());
//			e.printStackTrace();
//		}
//		return getUri().toString();
//	}
}