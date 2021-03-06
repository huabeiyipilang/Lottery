package cn.kli.lottery.diancan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.kli.lottery.R;

public class OrderForm extends Activity {

	ClipboardManager mClipboardManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_form);
		Bundle bundle = getIntent().getBundleExtra("Bundle");
		ArrayList<Dish> dishList = bundle.getParcelableArrayList("dishes");
		final EditText ps = (EditText)findViewById(R.id.postscript);
		final String text = prepareOrderForm(dishList);
		mClipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

		findViewById(R.id.send).setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, text+"\n"+ps.getText().toString());
				startActivity(intent);
			}

		});

		findViewById(R.id.copy).setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				try {
					mClipboardManager.setText(text+"\n"+ps.getText().toString());
					Toast.makeText(OrderForm.this, OrderForm.this.getString(R.string.copy_success),
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}});
	}

	private String prepareOrderForm(ArrayList<Dish> list){
		TextView form = (TextView)findViewById(R.id.order_form);
		String formContent = this.getString(R.string.order_form)+"\n"
				+ this.getString(R.string.order_form_dishes,getDishNames(list))+"\n"
				+ this.getString(R.string.order_form_rice)+"\n"
				+ this.getString(R.string.order_form_date, getCurrentDate());
		form.setText(formContent);
		return formContent;
	}

	private String getDishNames(ArrayList<Dish> list){
		String names = "";
		for(Dish dish: list){
			names += dish.d_name + " ";
		}
		return names;
	}

	private String getCurrentDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}
}
