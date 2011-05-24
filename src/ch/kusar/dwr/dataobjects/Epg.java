/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 24.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dataobjects;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Epg {

	private String ref;
	private String channel;
	private String show;
	private String start;
	private String duration;
	private String timerendaction;
	private String description;
	private String date;
	private String time;
	private static ArrayList<Epg> epgList = new ArrayList<Epg>();

	public Epg() {
	}

	public Epg(String ref, String channel, String show, String description,
			String start, String duration, String timerendaction) {
		super();
		this.ref = ref;
		this.channel = channel;
		this.show = show;
		this.description = description;
		this.start = start;
		this.duration = duration;
		this.timerendaction = timerendaction;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTimerendaction() {
		return timerendaction;
	}

	public void setTimerendaction(String timerendaction) {
		this.timerendaction = timerendaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static ArrayList<Epg> getEpgList() {
		return epgList;
	}

	public static void setEpgList(ArrayList<Epg> epgList) {
		Epg.epgList = epgList;
	}

	@Override
	public String toString() {
		String msg = MessageFormat.format(
				"Ref: {0}, Channel: {1}, Show: {2}, Description: {3}, "
						+ "Start: {4}, Duration: {5}, TimerEndAction: {6}, "
						+ "Date: {7}, Time: {8}", getRef(), getChannel(),
				getShow(), getDescription(), getStart(), getDuration(),
				getTimerendaction(), getDate(), getTime());
		return msg;
	}
}
