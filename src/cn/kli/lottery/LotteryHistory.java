package cn.kli.lottery;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LotteryHistory extends Activity {
	ListView mHistoryList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lottery_history);
		mHistoryList = (ListView)findViewById(R.id.history_list);
		Cursor cursor = Global.getInstance(this).getLotterHistory();
		LotteryHistoryAdapter adapter = new LotteryHistoryAdapter(this,cursor);
		mHistoryList.setAdapter(adapter);
	}
	
	private class LotteryHistoryAdapter extends CursorAdapter{
		LayoutInflater inflater;
		SimpleDateFormat format = new SimpleDateFormat("yyyyÄêMMÔÂddÈÕ HH:mm:ss");

		public LotteryHistoryAdapter(Context context, Cursor c) {
			super(context, c);
			inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View view = inflater.inflate(R.layout.history_item, null);
			return view;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView logPrize = (TextView)view.findViewById(R.id.log_prize);
			int prize = cursor.getInt(cursor.getColumnIndex(DbHelper.LOG_PRIZE));
			int color = Color.WHITE;
			if(prize > 0){
				color = Color.GREEN;
			}else if(prize < 0){
				color = Color.RED;
			}
			logPrize.setTextColor(color);
			logPrize.setText(prize+"");
			
			TextView logRoll = (TextView)view.findViewById(R.id.log_roll);
			String tag = "";
			int roll = cursor.getInt(cursor.getColumnIndex(DbHelper.LOG_ROLL));
			if(roll == Global.LOG_CAUSE_GET_MONEY){
				tag = getResources().getString(R.string.history_item_get_money);
			}else if(roll >= 0){
				tag = getResources().getString(R.string.history_item_roll, roll);
			}
			logRoll.setText(tag);
			
			TextView logTime = (TextView)view.findViewById(R.id.log_time);
			String time = cursor.getString(cursor.getColumnIndex(DbHelper.LOG_TIME));
			Date date = new Date(Long.parseLong(time));
			logTime.setText(format.format(date));
			
		}
		
	}
	
}
