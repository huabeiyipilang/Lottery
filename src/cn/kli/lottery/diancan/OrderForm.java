package cn.kli.lottery.diancan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.kli.lottery.R;

public class OrderForm extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.order_form);
	    findViewById(R.id.send).setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, "just for text");
				intent.putExtra(Intent.EXTRA_SUBJECT, "just for subject");
				intent.putExtra(Intent.EXTRA_TITLE, "just for title");
				startActivity(intent);
			}
	    	
	    });
	}

}
