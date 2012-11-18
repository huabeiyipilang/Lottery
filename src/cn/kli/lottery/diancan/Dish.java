package cn.kli.lottery.diancan;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
	String d_name;
	String d_pic;
	int d_selected; //1 selected, 0 otherwise
	
	private Dish(){
		
	}
	
	public Dish(String name, String pic){
		d_name = name;
		d_pic = pic;
		d_selected = 0;
	}
	
	public void select(){
		d_selected = (d_selected+1)%2;
	}
	
	public boolean isSelected(){
		boolean res = false;
		switch(d_selected){
		case 0:
			res = false;
			break;
		case 1:
			res = true;
			break;
		}
		return res;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeString(d_name);
		parcel.writeString(d_pic);
		parcel.writeInt(d_selected);
	}
	
	public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>(){

		public Dish createFromParcel(Parcel source) {
			Dish res = new Dish();
			res.d_name = source.readString();
			res.d_pic = source.readString();
			res.d_selected = source.readInt();
			return res;
		}

		public Dish[] newArray(int size) {
			return new Dish[size];
		}
		
	} ;
}
