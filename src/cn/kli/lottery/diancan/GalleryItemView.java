package cn.kli.lottery.diancan;

import cn.kli.lottery.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GalleryItemView extends LinearLayout {

	private Context mContext;
	private ImageView mImage;
	
	public GalleryItemView(Context context) {
		super(context);
		mContext = context;
		inflateLayout();
	}

	public GalleryItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		inflateLayout();
	}
	
	private void inflateLayout(){
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.gallery_item, this);
		mImage = (ImageView)view.findViewById(R.id.gallery_item_img);
	}
	
	public void setImageBitmap(Bitmap bitmap){
		mImage.setImageBitmap(bitmap);
	}
	
	public void setImageURI(Uri uri){
		mImage.setImageURI(uri);
	}
}
