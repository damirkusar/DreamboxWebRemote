/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 26.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dbpersistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DreamBoxDB extends SQLiteOpenHelper {
	private static final int DWRDB_DATABASE_VERSION = 1;
	private static final String DWRDB_DATABASE_NAME = "dwrdb";

	private static final String DWRDB_TABLE_TVCHANNELS = "tvchannels";
	private static final String DWRDB_TABLE_TVCHANNELS_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_TVCHANNELS + " ("
			+ " _id INTEGER PRIMARY KEY autoincrement," + " channelnr TEXT,"
			+ " channelref TEXT," + " channelname TEXT,"
			+ " channelbouquetid INTEGER REFERENCES tvbouquets(id));";

	private static final String DWRDB_TABLE_RADIOCHANNELS = "radiochannels";
	private static final String DWRDB_TABLE_RADIOCHANNELS_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RADIOCHANNELS
			+ " ("
			+ " _id INTEGER PRIMARY KEY autoincrement, "
			+ " channelnr TEXT,"
			+ " channelref TEXT,"
			+ " channelname TEXT,"
			+ " channelbouquetid INTEGER REFERENCES radiobouquets(id));";

	private static final String DWRDB_TABLE_TVBOUQUETS = "tvbouquets";
	private static final String DWRDB_TABLE_TVBOUQUETS_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_TVBOUQUETS + " ("
			+ " _id INTEGER PRIMARY KEY autoincrement, " + " bouquetref TEXT,"
			+ " bouquetname TEXT);";

	private static final String DWRDB_TABLE_RADIOBOUQUETS = "radiobouquets";
	private static final String DWRDB_TABLE_RADIOBOUQUETS_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RADIOBOUQUETS
			+ " ("
			+ " _id INTEGER PRIMARY KEY autoincrement, "
			+ " bouquetref TEXT,"
			+ " bouquetname TEXT);";

	private static final String DWRDB_TABLE_RECORDED = "recorded";
	private static final String DWRDB_TABLE_RECORDED_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RECORDED + " ("
			+ " _id INTEGER PRIMARY KEY autoincrement," + " recordednr TEXT,"
			+ " recordedref TEXT," + " recordedname TEXT,"
			+ " recordedbouquetid TEXT);";

	private static final String DWRDB_TABLE_EPG = "epg";
	private static final String DWRDB_TABLE_EPG_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_EPG + " ("
			+ " _id INTEGER PRIMARY KEY autoincrement,"
			+ " channelid INTEGER REFERENCES tvchannels(id)," + " show TEXT,"
			+ " description TEXT," + " start TEXT," + " duration TEXT,"
			+ " timerendaction TEXT," + " date TEXT," + " time TEXT);";

	public DreamBoxDB(Context context) {
		super(context, DWRDB_DATABASE_NAME, null, DWRDB_DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("DreamBoxDB", "onCreate");
		db.execSQL(DWRDB_TABLE_TVCHANNELS_CREATE);
		db.execSQL(DWRDB_TABLE_TVBOUQUETS_CREATE);
		db.execSQL(DWRDB_TABLE_RADIOCHANNELS_CREATE);
		db.execSQL(DWRDB_TABLE_RADIOBOUQUETS_CREATE);
		db.execSQL(DWRDB_TABLE_RECORDED_CREATE);
		db.execSQL(DWRDB_TABLE_EPG_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("DreamBoxDB", "onUpgrade");
		db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_TVCHANNELS);
		db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RADIOCHANNELS);
		db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_TVBOUQUETS);
		db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RADIOBOUQUETS);
		db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RECORDED);
		db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_EPG);
		onCreate(db);
	}

}
