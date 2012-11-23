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
import android.widget.TextView;

public class GalleryItemView extends LinearLayout {

	private Context mContext;
	private ImageView mImage;
	private ImageView mMark;
	private TextView mTag;
	
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
		mTag = (TextView)view.findViewById(R.id.dish_name);
		mMark = (ImageView)view.findViewById(R.id.dish_mark);
	}
	
	public void setTag(String tag){
		mTag.setText(tag);
	}
	
	public void setImageBitmap(Bitmap bitmap){
		mImage.setImageBitmap(bitmap);
	}
	
	public void setImageURI(Uri uri){
		mImage.setImageURI(uri);
	}
	
	public void mark(boolean mark){
		mMark.setVisibility(mark ? View.VISIBLE : View.GONE);
	}
}
