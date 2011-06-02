/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 02.06.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dbpersistence;

import java.text.MessageFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import static android.provider.BaseColumns._ID;

public class DreamBoxDBHandler {
	private static DreamBoxDB db = null;
	private static DreamBoxDBHandler dbh = null;
	private static Context context = null;

	public DreamBoxDBHandler(Context context) {
		this.context = context;
	}

	public static DreamBoxDBHandler getInstance(Context context) {
		if (dbh == null || context == null) {
			dbh = new DreamBoxDBHandler(context);
		}
		return dbh;
	}

	public static DreamBoxDBHandler getInstance() {
		if (dbh == null || context == null) {
			dbh = new DreamBoxDBHandler(context);
		}
		return dbh;
	}

	public SQLiteDatabase getWriteableDatabase() {
		if (db == null) {
			db = new DreamBoxDB(context);
		}
		return db.getWritableDatabase();
	}

	public SQLiteDatabase getReadableDatabase() {
		if (db == null) {
			db = new DreamBoxDB(context);
		}
		return db.getReadableDatabase();
	}

	public void updateDB() {
		putTVBouquets("0:1", "TVBOUQUET");
		putRadioBouquets("1:2", "RADIOBOUQUET");
		putTVChannels("0", "0:1:2", "TVCHANNEL", 1);
		putRadioChannels("0", "1:2:3", "RADIOCHANNEL", 1);
		putRecorded("0", "2:3:4", "RECORDED", 1);
		putEPG(0, "HOW I MET YOUR MOTHER", "One", "10:00", "3600", "0",
				"22.9.2011", "20:00");
		putEPG(1, "HOW I MET YOUR MOTHER", "Two", "10:00", "3600", "0",
				"22.9.2011", "20:00");
		getBouquetID();
	}

	public void getALLDBDATA() {
		Cursor c = getWriteableDatabase().query(db.DWRDB_TABLE_EPG, 
				null, 
				null,
				null, 
				null, 
				null, 
				null);
		c.moveToFirst();
		for (int y = 0; y < c.getCount(); y++) {
			c.moveToPosition(y);
			for (int i = 0; i < c.getColumnCount(); i++) {
				Log.e("getALLDBDATA", c.getColumnName(i));
				Log.e("getALLDBDATA", c.getString(i));
			}
		}
	}

	public void getBouquetID() {
		Cursor c = getReadableDatabase().query(db.DWRDB_TABLE_EPG,
				new String[]{db.DWRDB_ROW_DESCRIPTION}, 
				db.DWRDB_ROW_CHANNELID + "=?", 
				new String[]{"0"},
				null, 
				null, 
				null);
		c.moveToFirst();
		for (int y = 0; y < c.getCount(); y++) {
			c.moveToPosition(y);
			for (int i = 0; i < c.getColumnCount(); i++) {
				Log.e("getALLDBDATA", c.getColumnName(i));
				Log.e("getALLDBDATA", c.getString(i));
			}
		}
	}

	private void putTVBouquets(String ref, String name) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_REF, ref);
		cv.put(db.DWRDB_ROW_NAME, name);
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_TVBOUQUETS, null,
				cv);
		getWriteableDatabase().close();
	}

	private void putRadioBouquets(String ref, String name) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_REF, ref);
		cv.put(db.DWRDB_ROW_NAME, name);
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_RADIOBOUQUETS,
				null, cv);
		getWriteableDatabase().close();
	}

	private void putTVChannels(String channelNr, String channelRef,
			String name, int bouquetID) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_NUMBER, channelNr);
		cv.put(db.DWRDB_ROW_REF, channelRef);
		cv.put(db.DWRDB_ROW_NAME, name);
		cv.put(db.DWRDB_ROW_BOUQUETID, bouquetID);
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_TVCHANNELS, null,
				cv);
		getWriteableDatabase().close();
	}

	private void putRadioChannels(String channelNr, String channelRef,
			String name, int bouquetID) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_NUMBER, channelNr);
		cv.put(db.DWRDB_ROW_REF, channelRef);
		cv.put(db.DWRDB_ROW_NAME, name);
		cv.put(db.DWRDB_ROW_BOUQUETID, bouquetID);
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_RADIOCHANNELS,
				null, cv);
		getWriteableDatabase().close();
	}

	private void putRecorded(String recNr, String recRef, String name,
			int bouquetID) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_NUMBER, recNr);
		cv.put(db.DWRDB_ROW_REF, recRef);
		cv.put(db.DWRDB_ROW_NAME, name);
		cv.put(db.DWRDB_ROW_BOUQUETID, bouquetID);
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_RECORDED, null, cv);
		getWriteableDatabase().close();
	}

	private void putEPG(int tvChannelsID, String show, String description,
			String start, String duration, String timerEndAction, String date,
			String time) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_CHANNELID, tvChannelsID);
		cv.put(db.DWRDB_ROW_SHOW, show);
		cv.put(db.DWRDB_ROW_DESCRIPTION, description);
		cv.put(db.DWRDB_ROW_START, start);
		cv.put(db.DWRDB_ROW_DURATION, duration);
		cv.put(db.DWRDB_ROW_TIMERENDACTION, timerEndAction);
		cv.put(db.DWRDB_ROW_DATE, date);
		cv.put(db.DWRDB_ROW_TIME, time);
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_EPG, null, cv);
		getWriteableDatabase().close();
	}

}
