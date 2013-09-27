package net.skink.swtor.toontracker;

import android.util.Log;

public class ArmorString {
	public String name;
	public String color;
    private static String TAG = "ArmorString";
	
	
	public ArmorString() {
//		Log.d(TAG, "constructor hit"); 
		
		name = "not specified";
		color = "not specified";
	}
	public String toString() {
		return name;
	}
}
