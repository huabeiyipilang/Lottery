package cn.kli.lottery;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Global {
	public final static int NO = 0;
	public final static int YES = 1;
	public final static int ERROR = 2;
	
	//give how many chances one day.
	private final static int CHANCES_ONE_DAY = 5;
	
	//preference.xml
	private final static String CACHE_FILE = "global_data";
	private final static String PREF_TOTAL_PRIZE = "PREF_TOTAL_PRIZE";
	private final static String PREF_TODAY_CHANCE = "PREF_TODAY_CHANCE";
	private final static String PREF_LAST_DAY = "PREF_LAST_DAY";
	private final static String PREF_HAS_BKG = "PREF_HAS_BKG";
	
	public final static int LOG_CAUSE_GET_MONEY = -1;
	
	public final static String BACKGROUND_FILE_NAME = "background.jpg";
	
	public static int sTotalPrize;
	public static int sTodayChance;
	public static long sLastDay;
	public static int sBkgOn;
	
	private static Global sInstance = null;
	private Context mContext;
	private SharedPreferences mPref;
	
	private final DbHelper mHelper;
	private SQLiteDatabase mDb;
	
	private Global(Context context){
		mContext = context;
		mPref = mContext.getSharedPreferences(CACHE_FILE, Context.MODE_PRIVATE);
		mHelper = DbHelper.getInstance(mContext);
		mDb = mHelper.getWritableDatabase();
	}
	
	public static Global getInstance(Context context){
		if(sInstance == null){
			Log.i("klilog","new Global()");
			sInstance = new Global(context);
		}
		return sInstance;
	}
	
	public int checkOut(){
		sLastDay = mPref.getLong(PREF_LAST_DAY,0);
		//If sLastDay is 0, that means has not prefrence.xml,
		//so initGlobal.
		if(sLastDay == 0){
			initGlobal();
			sLastDay = mPref.getLong(PREF_LAST_DAY,0);
		}
		//check the chance is need to update.
		int result = isToday(sLastDay);
		if(result == NO){
			Log.i("klilog","is not last day");
			updateLastDay();
			updateChance(CHANCES_ONE_DAY);
		}else if(result == ERROR){
			return ERROR;
		}
		
		sTotalPrize = mPref.getInt(PREF_TOTAL_PRIZE, 0);
		sTodayChance = mPref.getInt(PREF_TODAY_CHANCE, 3);
		return YES;
	}
	
	public void updateLastDay(){
		SharedPreferences.Editor editor = mPref.edit();
		editor.putLong(PREF_LAST_DAY, System.currentTimeMillis()).commit();
	}
	
	public void updateChance(int chance) {
		sTodayChance = chance;
		SharedPreferences.Editor editor = mPref.edit();
		editor.putInt(PREF_TODAY_CHANCE, chance).commit();
	}
	
	public void updatePrize(int prize) {
		sTotalPrize = prize;
		SharedPreferences.Editor editor = mPref.edit();
		editor.putInt(PREF_TOTAL_PRIZE, prize).commit();
	}

	private void initGlobal() {
		SharedPreferences.Editor editor = mPref.edit();
		editor.putLong(PREF_LAST_DAY, System.currentTimeMillis());
		editor.putInt(PREF_TOTAL_PRIZE, 0);
		editor.putInt(PREF_TODAY_CHANCE, CHANCES_ONE_DAY);
		editor.commit();
	}
	
	private int isToday(long last){
		Date lastDate = new Date(last);
		Date todayDate = new Date();
		Log.i("klilog",lastDate+"");
		Log.i("klilog",lastDate.getDate()+ ","+lastDate.getMonth()+","+lastDate.getYear());
		Log.i("klilog",todayDate+"");
		Log.i("klilog",todayDate.getDate()+","+todayDate.getMonth()+","+todayDate.getYear());
		if(lastDate.getDate() == todayDate.getDate()
				&& lastDate.getMonth() == todayDate.getMonth()
				&& lastDate.getYear() == todayDate.getYear()){
			return YES;
		}
		if(last > System.currentTimeMillis()){
			return ERROR;
		}
		return NO;
	}
	
	public void setBackground(int on){
		SharedPreferences.Editor editor = mPref.edit();
		editor.putInt(PREF_HAS_BKG, on);
		editor.commit();
		sBkgOn = on;
	}
	
	public int isBackgroundOn(){
		sBkgOn = mPref.getInt(PREF_HAS_BKG,0);
		return sBkgOn;
	}
	
	public long addLog(int prize,int cause){
		ContentValues cv = new ContentValues();
		cv.put(DbHelper.LOG_PRIZE, prize);
		cv.put(DbHelper.LOG_ROLL, cause);
		cv.put(DbHelper.LOG_TIME, System.currentTimeMillis());
		return mDb.insert(DbHelper.TABLE_LOG, "", cv);
	}
	
	public Cursor getLotterHistory(){
		return mHelper.getLottryHistoryCursor();
	}
	
}
