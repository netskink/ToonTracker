package net.skink.swtor.toontracker;

import android.util.Log;

public class ColorString {
	public String name;
	public String color;
	public int currentValue;
    private static String TAG = "ColorString";
	
	
	public ColorString() {
//		Log.d(TAG, "constructor hit"); 
		
		name = "not specified";
		color = "not specified";
		currentValue=0;
	}
	public String toString() {
		return name;
	}
}
