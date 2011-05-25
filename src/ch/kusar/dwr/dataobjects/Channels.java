/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 24.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dataobjects;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Channels {

	private int bouquetID;
	private String ref;
	private String channelName;
	private int channelNr;
	private ArrayList<Bouquets> bouquetList = new ArrayList<Bouquets>();
	private static ArrayList<Channels> channelList = new ArrayList<Channels>();

	public Channels() {
	}

	public Channels(int bouquetID, int channelNr, String ref, String channelName) {
		super();
		this.channelNr = channelNr;
		this.bouquetID = bouquetID;
		this.ref = ref;
		this.channelName = channelName;
	}
	
	public Channels(int bouquetID, String ref, String channelName) {
		super();
		this.bouquetID = bouquetID;
		this.ref = ref;
		this.channelName = channelName;
	}

	public int getBouquetID() {
		return bouquetID;
	}

	public void setBouquetID(int bouquetID) {
		this.bouquetID = bouquetID;
	}

	public int getChannelNr() {
		return channelNr;
	}

	public void setChannelNr(int channelNr) {
		this.channelNr = channelNr;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public ArrayList<Bouquets> getBouquetList() {
		return bouquetList;
	}

	public void setBouquetList(ArrayList<Bouquets> bouquetList) {
		this.bouquetList = bouquetList;
	}

	public static ArrayList<Channels> getChannelList() {
		return channelList;
	}

	public static void setChannelList(ArrayList<Channels> channelList) {
		Channels.channelList = channelList;
	}

	@Override
	public String toString() {
		String msg = MessageFormat.format(
				"Bouquet: {0}, ChannelNr {1}, Ref: {2}, ChannelName: {3}",
				getBouquetID(), getChannelNr(), getRef(), getChannelName());
		return msg;
	}
}
