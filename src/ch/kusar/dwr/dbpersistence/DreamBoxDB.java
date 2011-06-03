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
import static android.provider.BaseColumns._ID;

public class DreamBoxDB extends SQLiteOpenHelper {
	private static final int DWRDB_DATABASE_VERSION = 1;
	private static final String DWRDB_DATABASE_NAME = "dwrdb";

	public static final String DWRDB_ROW_NUMBER = "NR";
	public static final String DWRDB_ROW_BOUQUETID = "BOUQUET_ID";
	public static final String DWRDB_ROW_CHANNELID = "CHANNEL_ID";
	public static final String DWRDB_ROW_CHANNEL_ID = _ID;
	public static final String DWRDB_ROW_REF = "REF";
	public static final String DWRDB_ROW_NAME = "NAME";
	public static final String DWRDB_ROW_SHOW = "SHOW";
	public static final String DWRDB_ROW_DESCRIPTION = "DESCRIPTION";
	public static final String DWRDB_ROW_START = "START";
	public static final String DWRDB_ROW_DURATION = "DURATION";
	public static final String DWRDB_ROW_TIMERENDACTION = "TIMERENDACTION";
	public static final String DWRDB_ROW_DATE = "DATE";
	public static final String DWRDB_ROW_TIME = "TIME";

	public static final String DWRDB_TYPE_VARCHAR = "varchar(90)";
	public static final String DWRDB_TYPE_VARCHARLARGE = "varchar(1000)";

	public static final String DWRDB_TABLE_TVBOUQUETS = "tvB";
	private static final String DWRDB_TABLE_TVBOUQUETS_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_TVBOUQUETS + " (" + _ID
			+ " INTEGER PRIMARY KEY autoincrement, " + DWRDB_ROW_REF + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_NAME + " "
			+ DWRDB_TYPE_VARCHAR + ");";

	public static final String DWRDB_TABLE_RADIOBOUQUETS = "radioB";
	private static final String DWRDB_TABLE_RADIOBOUQUETS_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RADIOBOUQUETS
			+ " ("
			+ _ID
			+ " INTEGER PRIMARY KEY autoincrement, "
			+ DWRDB_ROW_REF
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_NAME
			+ " "
			+ DWRDB_TYPE_VARCHAR + ");";

	public static final String DWRDB_TABLE_TVBCH = "tvBCh";
	private static final String DWRDB_TABLE_TVBCH_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_TVBCH + " (" + DWRDB_ROW_CHANNEL_ID
			+ " INTEGER PRIMARY KEY autoincrement, " + DWRDB_ROW_NUMBER + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_REF + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_NAME + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_BOUQUETID
			+ " INTEGER REFERENCES " + DWRDB_TABLE_TVBOUQUETS + "(_ID));";

	public static final String DWRDB_TABLE_RADIOBCH = "radioBCh";
	private static final String DWRDB_TABLE_RADIOBCH_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RADIOBCH
			+ " ("
			+ DWRDB_ROW_CHANNEL_ID
			+ " INTEGER PRIMARY KEY autoincrement, "
			+ DWRDB_ROW_NUMBER
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_REF
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_NAME
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_BOUQUETID
			+ " INTEGER REFERENCES " + DWRDB_TABLE_RADIOBOUQUETS + "(_ID));";
	
	public static final String DWRDB_TABLE_TVALLCH = "tvAllCh";
	private static final String DWRDB_TABLE_TVALLCH_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_TVALLCH + " (" + DWRDB_ROW_CHANNEL_ID
			+ " INTEGER PRIMARY KEY autoincrement, " + DWRDB_ROW_NUMBER + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_REF + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_NAME + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_BOUQUETID
			+ " INTEGER REFERENCES " + DWRDB_TABLE_TVBOUQUETS + "(_ID));";

	public static final String DWRDB_TABLE_RADIOALLCH = "radioAllCh";
	private static final String DWRDB_TABLE_RADIOALLCH_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RADIOALLCH
			+ " ("
			+ DWRDB_ROW_CHANNEL_ID
			+ " INTEGER PRIMARY KEY autoincrement, "
			+ DWRDB_ROW_NUMBER
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_REF
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_NAME
			+ " "
			+ DWRDB_TYPE_VARCHAR
			+ ","
			+ DWRDB_ROW_BOUQUETID
			+ " INTEGER REFERENCES " + DWRDB_TABLE_RADIOBOUQUETS + "(_ID));";

	public static final String DWRDB_TABLE_RECORDED = "recorded";
	private static final String DWRDB_TABLE_RECORDED_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_RECORDED + " (" + _ID
			+ " INTEGER PRIMARY KEY autoincrement, " + DWRDB_ROW_NUMBER + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_REF + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_NAME + " "
			+ DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_BOUQUETID + " "
			+ DWRDB_TYPE_VARCHAR + ");";

	public static final String DWRDB_TABLE_EPG = "epg";
	private static final String DWRDB_TABLE_EPG_CREATE = "CREATE TABLE "
			+ DWRDB_TABLE_EPG + " (" + _ID
			+ " INTEGER PRIMARY KEY autoincrement, " + DWRDB_ROW_CHANNELID
			+ " INTEGER REFERENCES " + DWRDB_TABLE_TVBCH + "(_ID),"
			+ DWRDB_ROW_SHOW + " " + DWRDB_TYPE_VARCHAR + ","
			+ DWRDB_ROW_DESCRIPTION + " " + DWRDB_TYPE_VARCHARLARGE + ","
			+ DWRDB_ROW_START + " " + DWRDB_TYPE_VARCHAR + ","
			+ DWRDB_ROW_DURATION + " " + DWRDB_TYPE_VARCHAR + ","
			+ DWRDB_ROW_TIMERENDACTION + " " + DWRDB_TYPE_VARCHAR + ","
			+ DWRDB_ROW_DATE + " " + DWRDB_TYPE_VARCHAR + "," + DWRDB_ROW_TIME
			+ " " + DWRDB_TYPE_VARCHAR + ");";

	public DreamBoxDB(Context context) {
		super(context, DWRDB_DATABASE_NAME, null, DWRDB_DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DWRDB_TABLE_TVBCH_CREATE);
			db.execSQL(DWRDB_TABLE_TVALLCH_CREATE);
			db.execSQL(DWRDB_TABLE_TVBOUQUETS_CREATE);
			db.execSQL(DWRDB_TABLE_RADIOBCH_CREATE);
			db.execSQL(DWRDB_TABLE_RADIOALLCH_CREATE);
			db.execSQL(DWRDB_TABLE_RADIOBOUQUETS_CREATE);
			db.execSQL(DWRDB_TABLE_RECORDED_CREATE);
			db.execSQL(DWRDB_TABLE_EPG_CREATE);
		} catch (Exception e) {
			Log.e("DreamBoxDB.onCreate()", e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_TVBCH);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RADIOBCH);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_TVALLCH);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RADIOALLCH);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_TVBOUQUETS);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RADIOBOUQUETS);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_RECORDED);
			db.execSQL("DROP TABLE IF EXISTS " + DWRDB_TABLE_EPG);
			onCreate(db);
		} catch (Exception e) {
			Log.e("DreamBoxDB.onUpgrade()", e.getMessage());
		}
	}
}