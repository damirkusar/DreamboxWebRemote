/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 02.06.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dbpersistence;

import java.util.ArrayList;

import ch.kusar.dwr.commands.WebDataHandler;
import ch.kusar.dwr.dataobjects.Bouquets;
import ch.kusar.dwr.dataobjects.Channels;
import ch.kusar.dwr.dataobjects.Epg;
import ch.kusar.dwr.dataobjects.Recorded;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DreamBoxDBHandler {
	private static DreamBoxDB db = null;
	private static DreamBoxDBHandler dbh = null;
	private static Context context = null;
	private WebDataHandler wdh = null;

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
		new Thread(new Runnable() {
			@Override
			public void run() {
				wdh = new WebDataHandler();
				DreamBoxDB db = new DreamBoxDB(context);
				db.onUpgrade(getInstance().getWriteableDatabase(), 1, 1);
				db.close();
				try {
					updateBouquets();
					updateAllChannels();
					updateAllBouquetChannels();
					updateRecorded();
					updateEPG();
					((Activity) context).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Log.e("DreamBoxDBHandler.updateDB", "Update Done");
							Toast.makeText(context, "UPDATE DONE",
									Toast.LENGTH_LONG).show();
						}
					});
				} catch (Exception e) {
					Log.e("DreamBoxDBHandler.updateDB", e.getMessage());
					((Activity) context).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Log.e("DreamBoxDBHandler.updateDB", "Update Failed");
							Toast.makeText(context, "UPDATE FAILED",
									Toast.LENGTH_LONG).show();
						}
					});
				} finally {
				}
			}
		}).start();
	}

	private void updateBouquets() {
		Log.i("DreamBoxDBHandler.updateBouquets", "started");
		ArrayList<Bouquets> tb = wdh.getTVBouquetsList();
		ArrayList<Bouquets> rb = wdh.getRadioBouquetsList();

		for (int i = 0; i < tb.size(); i++) {
			putTVBouquets(tb.get(i).getRef(), tb.get(i).getBouquetName());
		}

		for (int i = 0; i < rb.size(); i++) {
			putRadioBouquets(rb.get(i).getRef(), rb.get(i).getBouquetName());
		}
	}

	private void updateAllBouquetChannels() {
		Log.i("DreamBoxDBHandler.updateAllBouquetChannels", "started");
		ArrayList<Channels> r = wdh.getRadioBouquetsChannelsList();
		ArrayList<Channels> t = wdh.getTVBouquetsChannelsList();

		for (int i = 0; i < r.size(); i++) {
			putRadioBCh(r.get(i).getChannelNr(), r.get(i).getRef(), r.get(i)
					.getChannelName(), r.get(i).getBouquetID());
		}

		for (int i = 0; i < t.size(); i++) {
			putTVBCh(t.get(i).getChannelNr(), t.get(i).getRef(), t.get(i)
					.getChannelName(), t.get(i).getBouquetID());
		}
	}

	private void updateAllChannels() {
		Log.i("DreamBoxDBHandler.updateAllChannels", "started");
		ArrayList<Channels> r = wdh.getAllRadioChannelsList();
		ArrayList<Channels> t = wdh.getAllTVChannelsList();

		for (int i = 0; i < r.size(); i++) {
			putRadioAllCh(r.get(i).getChannelNr(), r.get(i).getRef(), r.get(i)
					.getChannelName(), r.get(i).getBouquetID());
		}

		for (int i = 0; i < t.size(); i++) {
			putTVAllCh(t.get(i).getChannelNr(), t.get(i).getRef(), t.get(i)
					.getChannelName(), t.get(i).getBouquetID());
		}
	}

	private void updateRecorded() {
		Log.i("DreamBoxDBHandler.updateRecorded", "started");
		ArrayList<Recorded> r = wdh.getRecordedList();
		for (int i = 0; i < r.size(); i++) {
			putRecorded(r.get(i).getRecordedNr(), r.get(i).getRef(), r.get(i)
					.getRecordedName(), r.get(i).getBouquetID());
		}
	}

	private void updateEPG() {
		Log.i("DreamBoxDBHandler.updateEPG", "started");
		ArrayList<Epg> e = wdh.getEPGList();
		for (int i = 0; i < e.size(); i++) {
			putEPG(getTvChannelID(e.get(i).getRef()), e.get(i).getShow(), e
					.get(i).getDescription(), e.get(i).getStart(), e.get(i)
					.getDuration(), e.get(i).getTimerendaction(), e.get(i)
					.getDate(), e.get(i).getTime());
		}
	}

	public int getTvChannelID(String ref) {
		Cursor c = getReadableDatabase()
				.query(db.DWRDB_TABLE_TVBCH,
						new String[] { db.DWRDB_ROW_CHANNEL_ID },
						db.DWRDB_ROW_REF + "=?", new String[] { ref }, null,
						null, null);
		c.moveToFirst();
		return Integer.valueOf(c.getString(0));
	}

	public void TESTgetALLDBDATA() {
		Cursor c = getWriteableDatabase().query(db.DWRDB_TABLE_EPG, null, null,
				null, null, null, null);
		c.moveToFirst();
		for (int y = 0; y < c.getCount(); y++) {
			c.moveToPosition(y);
			for (int i = 0; i < c.getColumnCount(); i++) {
				Log.e("getALLDBDATA", c.getColumnName(i));
				Log.e("getALLDBDATA", c.getString(i));
			}
		}
	}

	public void TESTgetBouquetID() {
		Cursor c = getReadableDatabase().query(db.DWRDB_TABLE_EPG,
				new String[] { db.DWRDB_ROW_DESCRIPTION },
				db.DWRDB_ROW_CHANNELID + "=?", new String[] { "0" }, null,
				null, null);
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

	private void putTVBCh(int channelNr, String channelRef, String name,
			int bouquetID) {
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_TVBCH, null,
				getContentValues(channelNr, channelRef, name, bouquetID));
		getWriteableDatabase().close();
	}

	private void putTVAllCh(int channelNr, String channelRef, String name,
			int bouquetID) {
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_TVALLCH, null,
				getContentValues(channelNr, channelRef, name, bouquetID));
		getWriteableDatabase().close();
	}

	private void putRadioBCh(int channelNr, String channelRef, String name,
			int bouquetID) {
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_RADIOBCH, null,
				getContentValues(channelNr, channelRef, name, bouquetID));
		getWriteableDatabase().close();
	}

	private void putRadioAllCh(int channelNr, String channelRef, String name,
			int bouquetID) {
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_RADIOALLCH, null,
				getContentValues(channelNr, channelRef, name, bouquetID));
		getWriteableDatabase().close();
	}

	private void putRecorded(int recNr, String recRef, String name,
			int bouquetID) {
		getWriteableDatabase().insertOrThrow(db.DWRDB_TABLE_RECORDED, null,
				getContentValues(recNr, recRef, name, bouquetID));
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

	private ContentValues getContentValues(int channelNr, String channelRef,
			String name, int bouquetID) {
		ContentValues cv = new ContentValues();
		cv.put(db.DWRDB_ROW_NUMBER, channelNr);
		cv.put(db.DWRDB_ROW_REF, channelRef);
		cv.put(db.DWRDB_ROW_NAME, name);
		cv.put(db.DWRDB_ROW_BOUQUETID, bouquetID);
		return cv;
	}

}
