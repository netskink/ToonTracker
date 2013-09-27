package net.skink.swtor.toontracker;



import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArmorAdapter<T> extends ArrayAdapter<T> {

    private static String TAG = "MyArmorAdapter";
    ArmorString[] myobjects;

	public MyArmorAdapter(Context context, int textViewResourceId, T[] objects) {
		super(context, textViewResourceId, objects);
		myobjects = (ArmorString[]) objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = super.getView(position, convertView, parent);
//		Log.d(TAG, "GetView with position "+position); 
	    TextView text = (TextView) row.findViewById(android.R.id.text1);
	    
	    if (null != text) {
	   		if (myobjects[position].color.equals("Green")) {
			    text.setTextColor(MyColors.GiftGreen);	  // green
	   		} else if (myobjects[position].color.equals("Yellow")) {
			    text.setTextColor(MyColors.GiftYellow);	  // yellow  		   			
	   		} else if (myobjects[position].color.equals("Red")) {
			    text.setTextColor(MyColors.GiftRed);	  // red  		   			
	   		} else {
	   			// will text default to white?
	   			// yes
	   		}	    	
	    }
		return row;
	}
//	Color.parseColor(MyColors.RepublicBlue)
	
	private ColorStateList MyColors(String string) {
		return null;
	}
	
}
