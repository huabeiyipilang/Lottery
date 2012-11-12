package cn.kli.lottery.diancan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;

public class GalleryAdapter extends BaseAdapter {
	private Context mContext;
	private DishList mDishList;
	private int mCurrentPosition;
	
	public GalleryAdapter(Context context){
		mContext = context;
		mDishList = getImageList();
	}

	public int getCount() {
		return mDishList.size();
	}

	public Object getItem(int arg0) {
		return arg0;
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return getImageView(arg0);
	}
	
	public String getCurrentPicName(){
		return mDishList.get(mCurrentPosition).d_pic;
	}
	
	private DishList getImageList(){
		return new DishList(mContext);
	}
	/* use DishList instead
	private ArrayList<String> getImageList(){
		AssetManager am = mContext.getResources().getAssets();
		ArrayList<String> list = new ArrayList<String>();
		try {
			String[] files = am.list("");
			for(String file : files){
				if(picFilter(file)){
					list.add(file);
					Log.i("klilog","add file = "+file);
				}
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	private boolean picFilter(String fileName){
		String[] types = {".jpg", ".png"};
		String tmp = fileName.toLowerCase();
		for(String type : types){
			if(tmp.endsWith(type)){
				return true;
			}
		}
		return false;
	}
	
	private GalleryItemView getImageView(int pos){
		mCurrentPosition = pos;
		GalleryItemView image = new GalleryItemView(mContext);
		InputStream input = null;
		AssetManager am = mContext.getResources().getAssets();
		try {
			input = am.open(mDishList.get(pos).d_pic);
			Bitmap bm = BitmapFactory.decodeStream(input);
			Log.i("klilog","load file = "+mDishList.get(pos).d_pic);
			image.setImageBitmap(bm);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(input != null){
					input.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return image;
	}
	
}
