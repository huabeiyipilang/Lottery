package cn.kli.lottery.diancan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.kli.lottery.R;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SlideShowActivity extends Activity {
	
	private final static String PIC_SAVE_PATH = "BeautyGallery";
	private LinearLayout mImageContainer;
	private GalleryView mGallery;
	private GalleryAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_show);
		mImageContainer = (LinearLayout)findViewById(R.id.image_container);
		
		mAdapter = new GalleryAdapter(this);
		
		mGallery = (GalleryView)findViewById(R.id.gallery1);
		mGallery.setAdapter(mAdapter);
		mGallery.setSpacing(40);
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				saveAssetPic2Sd(mAdapter.getCurrentPicName());
			}
			
		});
	}
	
	
	//save pic from asset to sdcard
	private boolean saveAssetPic2Sd(String picName){
		boolean result = false;
		AssetManager am = getAssets();
		FileOutputStream fos = null;
		InputStream is = null;
		String outputFileName = randomFileName(picName);
		//is sdcard enable
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File appDir = Environment.getExternalStoragePublicDirectory(PIC_SAVE_PATH);
			File file = new File(appDir, outputFileName);
			try {
				//Make sure app directory exists
				appDir.mkdirs();
				
				//read buffer from source pic
				is = am.open(picName);
				byte[] buffer = new byte[is.available()];
				is.read(buffer);
				
				//write buffer to sdcard
				fos = new FileOutputStream(file);
				fos.write(buffer);
				
				result = true;
				Toast.makeText(this, getResources().getString(R.string.save_to_sd,
						file.toString()), Toast.LENGTH_SHORT).show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			if(!result){
				Toast.makeText(this, R.string.save_error, Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(this, R.string.no_sdcard, Toast.LENGTH_SHORT).show();
		}
		return result;
	}
	
	private String randomFileName(String origin){
		int lastPoint = origin.lastIndexOf(".");
		String newName = origin.substring(lastPoint);
		return System.currentTimeMillis() + newName;
	}

}
