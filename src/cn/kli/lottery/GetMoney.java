package cn.kli.lottery;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetMoney extends Activity implements OnClickListener {
	EditText mMoneyNumber;
	Button mGetMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_money_ui);
		setBackground();
		mMoneyNumber = (EditText)findViewById(R.id.set_number);
		mGetMoney = (Button)findViewById(R.id.get_money);
		mGetMoney.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(mMoneyNumber == null){
			return;
		}
		int money = 0;
		try {
			money = Integer.parseInt(mMoneyNumber.getText().toString());
		} catch (NumberFormatException e) {
			Log.i("klilog","input error!");
		}
		if(money > Global.sTotalPrize){
			Toast.makeText(this, R.string.no_enough_money, Toast.LENGTH_SHORT).show();
		}else{
			Global.getInstance(this).addLog(-money, Global.LOG_CAUSE_GET_MONEY);
			Global.getInstance(this).updatePrize(Global.sTotalPrize - money);
			finish();
		}
		
	}
	
    private int setBackground(){

    	if(Global.getInstance(this).isBackgroundOn() != 1){
    		return Global.NO;
    	}
    	FileInputStream is;
		try {
			is = openFileInput(Global.BACKGROUND_FILE_NAME);
			findViewById(R.id.main_layout).setBackgroundDrawable(Drawable.createFromStream(is, Global.BACKGROUND_FILE_NAME));
	    	return Global.YES;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Global.ERROR;
		}
    }
	
}
