/**
 * This class is to send end receive data to and from Dreambox. 
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 * 
 */

package ch.kusar.dwr.commands;

import java.util.ArrayList;

import ch.kusar.dwr.dataobjects.Bouquets;
import ch.kusar.dwr.dataobjects.Channels;
import ch.kusar.dwr.dataobjects.Epg;
import ch.kusar.dwr.dataobjects.Recorded;

public class WebDataHandler extends Commands {
	private boolean isBouquet = false;
	private boolean isRecorded = false;

	public ArrayList<Bouquets> getTVBouquetsList() {
		loadZapData(getTvBouquetsPath());
		return Bouquets.getBouquetList();
	}

	public ArrayList<Channels> getTVBouquetsChannelsList() {
		loadZapData(getTvBouquetsPath());
		return Channels.getChannelList();
	}

	public ArrayList<Channels> getAllTVChannelsList() {
		loadZapData(getTvAllPath());
		return Channels.getChannelList();
	}

	public ArrayList<Bouquets> getRadioBouquetsList() {
		loadZapData(getRadioBouquetsPath());
		return Bouquets.getBouquetList();
	}

	public ArrayList<Channels> getRadioBouquetsChannelsList() {
		loadZapData(getRadioBouquetsPath());
		return Channels.getChannelList();
	}

	public ArrayList<Channels> getAllRadioChannelsList() {
		loadZapData(getRadioAllPath());
		return Channels.getChannelList();
	}

	public ArrayList<Recorded> getRecordedList() {
		loadZapData(getRecordedPath());
		return Recorded.getRecordedList();
	}

	public ArrayList<Epg> getEPGList() {
		return loadEpg();
	}

	/**
	 * This Method gets the current EPG over http from the DreamBox.
	 * 
	 * @return ArrayList<Epg>
	 */
	public ArrayList<Epg> loadEpg() {
		connectToDreamBox(getEpgPath(), null);
		return epgStringParser(httpGetExecuteResult());
	}

	/**
	 * This Method gets the EPG from a specified channel over http from the
	 * DreamBox.
	 * 
	 * @return ArrayList<Epg>
	 */
	public ArrayList<Epg> loadEpg(String ref) {
		connectToDreamBox(getEpgPath(), ref);
		return epgStringParser(httpGetExecuteResult());
	}

	/**
	 * This Method gets the EPG over http from the DreamBox.
	 */
	private void loadZapData(String url) {
		if (url.equals(getTvBouquetsPath())
				|| url.equals(getRadioBouquetsPath())) {
			isBouquet = true;
			isRecorded = false;
		} else if (url.equals(getRecordedPath())) {
			isBouquet = false;
			isRecorded = true;
		} else {
			isBouquet = false;
			isRecorded = false;
		}
		connectToDreamBox(url, null);
		zapParser(httpGetExecuteResult());
	}

	private void zapParser(String htmlPage) {
		String[] semiColonSplit = htmlPage.split(";");

		if (isRecorded) {
			loadRecorded(semiColonSplit);
		} else {
			loadBouquets(semiColonSplit);
			loadChannels(semiColonSplit);
		}
	}

	private ArrayList<Bouquets> loadBouquets(String[] semiColonSplit) {
		Bouquets.getBouquetList().clear();
		Bouquets bouquet = null;

		String[] bouquetName = semiColonSplit[3].split("\"");
		String[] bouquetRef = semiColonSplit[4].split("\"");

		for (int i = 1; i < bouquetName.length; i += 2) {
			bouquet = new Bouquets(Bouquets.getBouquetList().size(),
					bouquetRef[i], bouquetName[i]);
			Bouquets.getBouquetList().add(bouquet);
		}

//		for (int i = 0; i < Bouquets.getBouquetList().size(); i++) {
//			System.out.println(Bouquets.getBouquetList().get(i).toString());
//		}
		return Bouquets.getBouquetList();
	}

	private ArrayList<Channels> loadChannels(String[] semiColonSplit) {
		Channels.getChannelList().clear();
		Channels channel = null;
		int size = Bouquets.getBouquetList().size();
		int startChannelNames = 10;
		int startChannelRefs = startChannelNames + size;

		for (int i = startChannelNames; i < startChannelRefs; i++) {
			String[] channelName = semiColonSplit[i].split("\"");
			String[] channelRef = semiColonSplit[i + size].split("\"");

			for (int y = 1; y < channelName.length; y += 2) {
				channelName[y] = channelName[y].split("-")[0];
				if (isBouquet) {
					channel = new Channels(1+ i - startChannelNames, Channels
							.getChannelList().size() + 1, channelRef[y],
							channelName[y]);
				} else {
					channel = new Channels(i - startChannelNames,
							channelRef[y], channelName[y]);
				}
				Channels.getChannelList().add(channel);
			}
		}

//		for (int i = 0; i < Channels.getChannelList().size(); i++) {
//			System.out.println(Channels.getChannelList().get(i).toString());
//		}
		return Channels.getChannelList();
	}

	private ArrayList<Recorded> loadRecorded(String[] semiColonSplit) {
		Recorded.getRecordedList().clear();
		Recorded recorded = null;
		int size = 1;
		int startRecordedNames = 10;
		int startRecordedRefs = startRecordedNames + size;

		for (int i = startRecordedNames; i < startRecordedRefs; i++) {
			String[] recordedName = semiColonSplit[i].split("\"");
			String[] recordedRef = semiColonSplit[i + size].split("\"");

			for (int y = 1; y < recordedName.length; y += 2) {
				recordedName[y] = recordedName[y].split("]")[1];
				recorded = new Recorded(recordedRef[y], recordedName[y],
						Recorded.getRecordedList().size() + 1);
				Recorded.getRecordedList().add(recorded);
			}
		}

//		for (int i = 0; i < Recorded.getRecordedList().size(); i++) {
//			System.out.println(Recorded.getRecordedList().get(i).toString());
//		}
		return Recorded.getRecordedList();
	}

	/**
	 * This Method parses the returned http string and saves it in the Epg
	 * ArrayList.
	 * 
	 * @return ArrayList<Epg>
	 */
	private ArrayList<Epg> epgStringParser(String htmlpage) {
		Epg.getEpgList().clear();
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
//		System.out.println("Start desc Damir Kusar");
//		for (int i = 0; i < Epg.getEpgList().size(); i++) {
//			System.out.println(Epg.getEpgList().get(i).toString());
//		}
//		System.out.println("Ende Damir Kusar");
		return Epg.getEpgList();
	}
}