package net.skink.swtor.toontracker;



import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter<T> extends ArrayAdapter<T> {

    private static String TAG = "MyAdapter";
    ToonOrCompanionName[] myobjects;

	public MyAdapter(Context context, int textViewResourceId, T[] objects) {
		super(context, textViewResourceId, objects);
		myobjects = (ToonOrCompanionName[]) objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = super.getView(position, convertView, parent);
//		Log.d(TAG, "GetView with position "+position); 
	    TextView text = (TextView) row.findViewById(android.R.id.text1);
	    
	    if (null != text) {
	   		if (myobjects[position].faction.equals("Imperial")) {
			    text.setTextColor(MyColors.ImperialRedBright);	  // red
	   		} else {
			    text.setTextColor(MyColors.RepublicBlue);	  // blue  		   			
	   		}	    	
	    }
		return row;
	}
//	Color.parseColor(MyColors.RepublicBlue)
	
	private ColorStateList MyColors(String string) {
		return null;
	}
	

	
	// This will set the list to small text and it will be in blue.
/*	
	 public View getView(int position, View convertView, ViewGroup parent) {
         TextView text = new TextView(getContext());
         text.setText("Hi, I am position " + position);
         text.setTextColor(Color.parseColor("#64788e"));
         return  text;
	}
*/	
}
