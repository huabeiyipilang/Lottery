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
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;

public class GalleryAdapter extends BaseAdapter {
	private Context mContext;
	private DishMenu mDishList;
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
	
	public void selectCurrentDish(){
		mDishList.get(mCurrentPosition).select();
		this.notifyDataSetChanged();
	}
	
	public ArrayList<Dish> getSelectedList(){
		return mDishList.getSelectedDishList();
	}
	
	/*
	public Dish getAndRemoveCurrentDish(){
		Dish dish = mDishList.get(mCurrentPosition);
		if(dish == null){
			return null;
		}
		mDishList.remove(dish);
		this.notifyDataSetChanged();
		return dish;
	}*/
	
	private DishMenu getImageList(){
		return new DishMenu(mContext);
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
		GalleryItemView view = new GalleryItemView(mContext);
		/*
		view.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
			}
			
		});
		view.setOnLongClickListener(new OnLongClickListener(){

			public boolean onLongClick(View arg0) {
				selectCurrentDish();
				return true;
			}
			
		});*/
		InputStream input = null;
		AssetManager am = mContext.getResources().getAssets();
		try {
			input = am.open(mDishList.get(pos).d_pic);
			Bitmap bm = BitmapFactory.decodeStream(input);
			Log.i("klilog","load file = "+mDishList.get(pos).d_pic);
			view.setImageBitmap(bm);
			String tag = mDishList.get(pos).d_name;
			if(mDishList.get(pos).isSelected()){
				tag += "  selected";
			}
			view.setTag(tag);
			
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
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return view;
	}
	
}
