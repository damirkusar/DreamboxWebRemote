/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 24.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dataobjects;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Recorded {

	private String ref;
	private String recordedName;
	private int recordedNr;
	private static ArrayList<Recorded> recordedList = new ArrayList<Recorded>();

	public Recorded() {
	}

	public Recorded(String ref, String recordedName, int recordedNr) {
		super();
		this.recordedNr = recordedNr;
		this.ref = ref;
		this.recordedName = recordedName;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getRecordedName() {
		return recordedName;
	}

	public void setRecordedName(String channelName) {
		this.recordedName = channelName;
	}

	public int getRecordedNr() {
		return recordedNr;
	}

	public void setRecordedNr(int recordedNr) {
		this.recordedNr = recordedNr;
	}

	public static ArrayList<Recorded> getRecordedList() {
		return recordedList;
	}

	public static void setRecordedList(ArrayList<Recorded> recordedList) {
		Recorded.recordedList = recordedList;
	}

	@Override
	public String toString() {
		String msg = MessageFormat.format(
				"RecordedNr: {0}, Ref: {1}, RecordedName: {2}",
				getRecordedNr(), getRef(), getRecordedName());
		return msg;
	}
}
