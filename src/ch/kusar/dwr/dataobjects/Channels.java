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

	private String ref;
	private String channelName;
	private static ArrayList<Bouquets> bouquetList = new ArrayList<Bouquets>();
	private static ArrayList<Channels> channelList = new ArrayList<Channels>();

	public Channels() {
	}

	public Channels(String ref, String channelName) {
		super();
		this.ref = ref;
		this.channelName = channelName;		
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

	public static ArrayList<Bouquets> getBouquetList() {
		return bouquetList;
	}

	public static void setBouquetList(ArrayList<Bouquets> bouquetList) {
		Channels.bouquetList = bouquetList;
	}

	public static ArrayList<Channels> getChannelList() {
		return channelList;
	}

	public static void setChannelList(ArrayList<Channels> channelList) {
		Channels.channelList = channelList;
	}

	@Override
	public String toString() {
		String msg = MessageFormat.format("Ref: {0}, ChannelName: {1}",
				getRef(), getChannelName());
		return msg;
	}
}
