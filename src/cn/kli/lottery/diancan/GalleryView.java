package cn.kli.lottery.diancan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class GalleryView extends Gallery {

	public GalleryView(Context context) {
		super(context);
	}
	
	public GalleryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int keycode = KeyEvent.KEYCODE_DPAD_RIGHT;
		if(e2.getX() > e1.getX()){
			keycode = KeyEvent.KEYCODE_DPAD_LEFT;
		}
		onKeyDown(keycode, null);
		return true;
	}

	
}
