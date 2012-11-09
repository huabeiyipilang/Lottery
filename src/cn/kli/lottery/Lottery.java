package cn.kli.lottery;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Lottery extends Activity implements OnClickListener {
	private Button mChouJiang;
	private Button mDuiJiang;
	private Button mDianCan;
	private TextView mTotalPrize;
	private TextView mTodayChance;
	private Global mGlobal;
	private LinearLayout mMainLayout;
	
	private final static int MENU_CHANGE_BACKGROUND = 1;
	private final static int MENU_CANCEL_BACKGROUND = 2;
	private final static int MENU_LOTTERY_HISTORY 	= 3;
	
	private final static int REQUEST_CODE_PHOTO_PICKED_WITH_DATA = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("klilog","Lottery onCreate()");
        setContentView(R.layout.main);
    	
        mDuiJiang = (Button)findViewById(R.id.duijiang);
        mChouJiang = (Button)findViewById(R.id.choujiang);
        mDianCan = (Button)findViewById(R.id.diancan);
        mTotalPrize = (TextView)findViewById(R.id.total_prize);
        mTodayChance = (TextView)findViewById(R.id.today_chance);
        mMainLayout = (LinearLayout)findViewById(R.id.main_layout);
        mChouJiang.setOnClickListener(this);
        mDuiJiang.setOnClickListener(this);
        mDianCan.setOnClickListener(this);
    }
    
    

    @Override
	protected void onStart() {
		super.onStart();
		if(mGlobal == null){
	        mGlobal = Global.getInstance(this);
		}
		
		int check = mGlobal.checkOut();
		if(check == Global.ERROR){
			Toast.makeText(this, R.string.time_error, Toast.LENGTH_SHORT).show();
		}
        updateDisplay();
	}

    
    
	private void updateDisplay(){
    	mTotalPrize.setText(getResources().getString(R.string.total_prize, Global.sTotalPrize));
    	mTodayChance.setText(getResources().getString(R.string.today_chance, Global.sTodayChance));
    	if(mGlobal.isBackgroundOn() == 1){
    		setBackground();
    	}
    }
    
	
	
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.choujiang:
			if(Global.sTodayChance <= 0){
				Toast.makeText(this, R.string.no_chance, Toast.LENGTH_SHORT).show();
				return;
			}
			gotoActivity(RandomNumberLottery.class);
			break;
		case R.id.duijiang:
			gotoActivity(GetMoney.class);
			break;
			
		case R.id.diancan:
			gotoActivity(cn.kli.lottery.diancan.SlideShowActivity.class);
			break;
		}
	}

	private void gotoActivity(Class cls){
		Intent intent = new Intent();
		intent.setClass(this, cls);
		this.startActivity(intent);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
		case REQUEST_CODE_PHOTO_PICKED_WITH_DATA:
			if(resultCode != Activity.RESULT_OK){
				return;
			}
			Bitmap pic = data.getParcelableExtra("data");
			int result = savePic(pic);
			if(result == Global.ERROR){
				Toast.makeText(this, R.string.error_to_save_pic, Toast.LENGTH_SHORT).show();
			}
			result = setBackground();
			if(result == Global.ERROR){
				Toast.makeText(this, R.string.error_to_set_background, Toast.LENGTH_SHORT).show();
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	
	
	private int savePic(Bitmap bitmap){
		try {
			FileOutputStream os = openFileOutput(Global.BACKGROUND_FILE_NAME, Context.MODE_PRIVATE);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
			os.close();
			return Global.YES;
		} catch (Exception e) {
			e.printStackTrace();
			return Global.ERROR;
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_CHANGE_BACKGROUND, 0, R.string.change_background);
		menu.add(0, MENU_CANCEL_BACKGROUND, 0, R.string.cancel_background);
		menu.add(0, MENU_LOTTERY_HISTORY, 0, R.string.lotter_history);
		return super.onCreateOptionsMenu(menu);
	}

	
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_CHANGE_BACKGROUND:
			pickPicFromGallery();
			break;
		case MENU_CANCEL_BACKGROUND:
			cancelBackground();
			break;
		case MENU_LOTTERY_HISTORY:
			startActivity(new Intent(this,LotteryHistory.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private void pickPicFromGallery(){
		final Intent intent = getPhotoPickIntent();
        try {
			startActivityForResult(intent, REQUEST_CODE_PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, R.string.error_to_open_gallery, Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
    private Intent getPhotoPickIntent() {
    	final DisplayMetrics dm = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(dm);
    	int w = dm.widthPixels;
    	int h = dm.heightPixels;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 5);
        intent.putExtra("outputX", 240);
        intent.putExtra("outputY", 400);
        intent.putExtra("return-data", true);
        return intent;
    }
    
    private int setBackground(){
    	FileInputStream is;
		try {
			is = openFileInput(Global.BACKGROUND_FILE_NAME);
	    	mMainLayout.setBackgroundDrawable(Drawable.createFromStream(is, Global.BACKGROUND_FILE_NAME));
	    	mGlobal.setBackground(1);
	    	return Global.YES;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Global.ERROR;
		}
    }
    
    private int cancelBackground(){
    	mMainLayout.setBackgroundDrawable(null);
    	mGlobal.setBackground(0);
    	return Global.YES;
    }
	
}