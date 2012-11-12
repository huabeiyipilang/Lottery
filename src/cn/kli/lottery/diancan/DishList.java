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

public class DishList{
	private Context mContext;
	private ArrayList<Dish> mDishList = new ArrayList<Dish>();
	private final static String DISH_LIST_FILE = "dish_list.xml";
	private final static String DISH_TAG = "dish";
	private final static String DISH_NAME = "name";
	private final static String DISH_PIC= "pic";
	
	public class Dish{
		String d_name;
		String d_pic;
		public Dish(String name, String pic){
			d_name = name;
			d_pic = pic;
		}
	}
	
	
	public DishList(Context context) {
		mContext = context;
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
				mDishList.add(new Dish(dish.getAttribute(DISH_NAME), dish.getAttribute(DISH_PIC)));
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
		return mDishList.size();
	}
	
	public Dish get(int pos){
		return mDishList.get(pos);
	}

}