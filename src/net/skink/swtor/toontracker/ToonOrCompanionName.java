package net.skink.swtor.toontracker;

import android.util.Log;

public class ToonOrCompanionName {
	public String name;
	public String faction;
	public int toonID;
    private static String TAG = "ToonOrCompanionName";
	
	
	public ToonOrCompanionName() {
//		Log.d(TAG, "constructor hit"); 
		
		name = "not specified";
		faction = "not specified";
	}
	public String toString() {
		return name;
	}
}
