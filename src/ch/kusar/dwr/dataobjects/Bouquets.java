/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 24.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dataobjects;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Bouquets {

	private int id;
	private String ref;
	private String bouquetName;
	private static ArrayList<Bouquets> bouquetList = new ArrayList<Bouquets>();

	public Bouquets() {
	}

	public Bouquets(int id, String ref, String bouquetName) {
		super();
		this.id = id;
		this.ref = ref;
		this.bouquetName = bouquetName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getBouquetName() {
		return bouquetName;
	}

	public void setBouquetName(String bouquetName) {
		this.bouquetName = bouquetName;
	}

	public static ArrayList<Bouquets> getBouquetList() {
		return bouquetList;
	}

	public static void setBouquetList(ArrayList<Bouquets> bouquetList) {
		Bouquets.bouquetList = bouquetList;
	}

	@Override
	public String toString() {
		String msg = MessageFormat.format("Ref: {0}, BouquetName: {1}",
				getRef(), getBouquetName());
		return msg;
	}
}
