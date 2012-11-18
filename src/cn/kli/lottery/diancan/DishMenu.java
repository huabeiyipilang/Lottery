package cn.kli.lottery.diancan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;

public class DishMenu{
	private Context mContext;
	private ArrayList<Dish> mDishMenu = new ArrayList<Dish>();
	private final static String DISH_LIST_FILE = "dish_list.xml";
	private final static String DISH_TAG = "dish";
	private final static String DISH_NAME = "name";
	private final static String DISH_PIC= "pic";
	
	public DishMenu() {
		
	}
	
	public DishMenu(Context context) {
		mContext = context;
		update();
	}


	public boolean update(){
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			AssetManager am = mContext.getResources().getAssets();
			InputStream is = am.open(DISH_LIST_FILE); 
			document = builder.parse(is);
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName(DISH_TAG);
			for(int i = 0; i < nodes.getLength(); i++){
				Element dish = (Element) nodes.item(i);
				mDishMenu.add(new Dish(dish.getAttribute(DISH_NAME), dish.getAttribute(DISH_PIC)));
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public int size() {
		return mDishMenu.size();
	}
	
	public Dish get(int pos){
		try {
			return mDishMenu.get(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Dish> getSelectedDishList(){
		ArrayList<Dish> list = new ArrayList<Dish>();
		for(Dish dish:mDishMenu){
			if(dish.isSelected()){
				list.add(dish);
			}
		}
		return list;
	}

}
