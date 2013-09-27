package net.skink.swtor.toontracker;

import android.graphics.Color;


public class MyColors {	

	public final static int ImperialRed = Color.parseColor("#7b2325");
	public final static int ImperialRedBright = Color.parseColor("#fb2325");
	public final static int RepublicBlue = Color.parseColor("#64788e");

	public final static int GiftGreen = Color.parseColor("#6df486");
	public final static int GiftYellow = Color.parseColor("#ddf60c");
	public final static int GiftRed = Color.parseColor("#f64448");
	
	public static int getColor(String color){
		return Color.parseColor(color);
		
	}
	
}
