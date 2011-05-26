/**
 * This class is to send end receive data to and from Dreambox. 
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 * 
 */

package ch.kusar.dwr.commands;

import ch.kusar.dwr.dataobjects.Bouquets;
import ch.kusar.dwr.dataobjects.Channels;
import ch.kusar.dwr.dataobjects.Recorded;

public class ZapCommands extends Commands {
	private boolean isBouquet = false;
	private boolean isRecorded = false;

	public void loadTVBouquetsData() {
		loadZapData(getTvBouquetsPath());
	}

	public void loadTVData() {
		loadZapData(getTvAllPath());
	}

	public void loadRadioBouquetsData() {
		loadZapData(getRadioBouquetsPath());
	}

	public void loadRadioData() {
		loadZapData(getRadioAllPath());
	}

	public void loadRecordedData() {
		loadZapData(getRecordedPath());
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
		httpGetDreamBox(url, null);
		zapParser(httpGetExecuteResult());
	}

	private void zapParser(String htmlPage) {
		String[] semiColonSplit = htmlPage.split(";");

		if (isRecorded) {
			setRecorded(semiColonSplit);
		} else {
			setBouquets(semiColonSplit);
			setChannels(semiColonSplit);
		}

		// for (int i = 0; i < semiColonSplit.length; i++) {
		// System.out.println(MessageFormat.format("id: {0}", i));
		// System.out.println(MessageFormat.format("Data: {0}",
		// semiColonSplit[i]));
		// }
	}

	private void setBouquets(String[] semiColonSplit) {
		Bouquets.getBouquetList().clear();
		Bouquets bouquet = null;

		String[] bouquetName = semiColonSplit[3].split("\"");
		String[] bouquetRef = semiColonSplit[4].split("\"");

		for (int i = 1; i < bouquetName.length; i += 2) {
			bouquet = new Bouquets(Bouquets.getBouquetList().size(),
					bouquetRef[i], bouquetName[i]);
			Bouquets.getBouquetList().add(bouquet);
		}

		for (int i = 0; i < Bouquets.getBouquetList().size(); i++) {
			System.out.println(Bouquets.getBouquetList().get(i).toString());
		}
	}

	private void setChannels(String[] semiColonSplit) {
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
					channel = new Channels(i - startChannelNames, Channels
							.getChannelList().size() + 1, channelRef[y],
							channelName[y]);
				} else {
					channel = new Channels(i - startChannelNames,
							channelRef[y], channelName[y]);
				}
				Channels.getChannelList().add(channel);
			}
		}

		for (int i = 0; i < Channels.getChannelList().size(); i++) {
			System.out.println(Channels.getChannelList().get(i).toString());
		}
	}

	private void setRecorded(String[] semiColonSplit) {
		Recorded.getRecordedList().clear();
		Recorded recorded = null;
		int size = 1;
		int startRecordedNames = 10;
		int startRecordedRefs = startRecordedNames + size;

		for (int i = startRecordedNames; i < startRecordedRefs; i++) {
			String[] recordedName = semiColonSplit[i].split("\"");
			String[] recordedRef = semiColonSplit[i + size].split("\"");

			for (int y = 1; y < recordedName.length; y += 2) {
				// recordedName[y] = recordedName[y].split("-")[0];
				recordedName[y] = recordedName[y].split("]")[1];
				recorded = new Recorded(recordedRef[y], recordedName[y],
						Recorded.getRecordedList().size() + 1);
				Recorded.getRecordedList().add(recorded);
			}
		}

		for (int i = 0; i < Recorded.getRecordedList().size(); i++) {
			System.out.println(Recorded.getRecordedList().get(i).toString());
		}
	}
}