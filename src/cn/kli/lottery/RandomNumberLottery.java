package cn.kli.lottery;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RandomNumberLottery extends Activity implements OnClickListener{
	private Rule mRule;
	private Button mStartBtn;
	private boolean mStart = false;
	private int mTime = 100;
	private int mFirst = 0;
	private ImageView mFirstNumber;
	private ImageView mSecondNumber;
	private AnimationDrawable anim1;
	private AnimationDrawable anim2;
	private Global mGlobal;

	private static int[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.random_number_ui);
		mGlobal = Global.getInstance(this);
		
		mStartBtn = (Button)findViewById(R.id.button1);
		mFirstNumber = (ImageView)findViewById(R.id.first_num);
		mSecondNumber = (ImageView)findViewById(R.id.second_num);
		
		mStartBtn.setText(R.string.start);
		mStartBtn.setOnClickListener(this);
		mRule = RuleFactory.creator(RuleFactory.RULE_RANDOM_NUMBER);

		anim1 = (AnimationDrawable)mFirstNumber.getDrawable();
		anim2 = (AnimationDrawable)mSecondNumber.getDrawable();
		anim1.selectDrawable(0);
		anim2.selectDrawable(9);
	}
	
	@Override
	public void onClick(View arg0) {
		if(anim1.isRunning()){
			mStartBtn.setText(R.string.start);
			Rule.Result result = mRule.execute();
			mGlobal.addLog(result.prize, result.roll);
			mGlobal.updatePrize(Global.sTotalPrize + result.prize);
			int num1 = result.prize/10;
			int num2 = result.prize%10;
			anim1.stop();
			anim2.stop();
			anim1.selectDrawable(num1);
			anim2.selectDrawable(9-num2);
		}else{
			if(Global.sTodayChance <= 0){
				Toast.makeText(this, R.string.no_chance, Toast.LENGTH_SHORT).show();
				return;
			}
			mGlobal.updateChance(Global.sTodayChance - 1);
			mStartBtn.setText(R.string.stop);
			anim1.stop();
			anim1.start();
			anim2.stop();
			anim2.start();
		}
	}
	
}
