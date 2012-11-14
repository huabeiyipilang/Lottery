package cn.kli.lottery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.kli.lottery.diancan.DishMenu;

public class TestActivity extends Activity {
	private TextView display;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.test);
	    display = (TextView)findViewById(R.id.test_display);
	    DishMenu list = new DishMenu(this);
	    list.update();
	}

}
