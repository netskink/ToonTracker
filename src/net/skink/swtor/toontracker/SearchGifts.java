package net.skink.swtor.toontracker;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchGifts extends Activity {

    // UI Components
    ListView listViewGifts;
    TextView textViewGift;
	
    private static String TAG = "SearchGifts";
    ColorString[] stringGifts; // will store an array.

	DatabaseHelper myDbHelper = null;

	
	@Override
	protected void onDestroy() {
		myDbHelper.close();
		super.onDestroy();
	}

	
	
	private void initDatabase() {
		myDbHelper = new DatabaseHelper(this);

        try {
        	myDbHelper.createDataBase();
        } catch (IOException ioe) { 
        	throw new Error("Unable to create database");
        }	
		
        try {
        	myDbHelper.openDataBase();
        }catch(SQLException sqle){
			Log.d(TAG, "openfailed"); 
        	throw sqle;
        }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.search_gifts);	
    	
        listViewGifts = (ListView) findViewById(R.id.listView1);
        textViewGift = (TextView) findViewById(R.id.textViewGift);

        if (null == myDbHelper)
        	initDatabase();

        // set the gifts list to all
	    setGiftsList(null);
    	
		
	}


	private void setGiftsList(Object object) {
    	int numCursorRows;
    	
    	
        // Determine the gifts to put in the list.
        Cursor c;
        try {
            c = myDbHelper.listGifts();
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
	    stringGifts = new ColorString[numCursorRows]; // create an array of proper size.
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	stringGifts[i]=new ColorString();
	    	stringGifts[i].name=c.getString(0); // This cursor has two columns companion name and faction side
	    	c.moveToNext();
	    }
        
        c.close();

    	    	
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
		MyColorAdapter<ColorString> adapter = new MyColorAdapter<ColorString>(this,android.R.layout.simple_list_item_1, stringGifts);

		listViewGifts.setAdapter(adapter);
        listViewGifts.setOnItemClickListener(new MyGiftsOnItemClickListener(this));
	}


	public void findGifts(String gift) {
    	int numCursorRows;
        String[] highGifts = null; // will store an array.
        String[] mediumGifts = null; // will store an array.
        String[] lowGifts = null; // will store an array.
        int numHigh=0;
        int numMedium=0;
        int numLow=0;
    	int newSize = 0;
    	
    	textViewGift.setText(gift);
    	
    	
        // Determine the gifts to put in the list.
        Cursor c;
        try {
            c = myDbHelper.searchGifts(gift,1);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
//			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    highGifts = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	highGifts[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numHigh = numCursorRows;
        }
        c.close();
        
        // Determine the gifts to put in the list.
        try {
            c = myDbHelper.searchGifts(gift,2);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
//			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    mediumGifts = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	mediumGifts[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numMedium = numCursorRows;
        }
    	c.close();
        
        // Determine the gifts to put in the list.
        try {
            c = myDbHelper.searchGifts(gift,3);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
//			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    lowGifts = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	lowGifts[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numLow = numCursorRows;
        }
        c.close();
        
	    stringGifts = new ColorString[newSize]; // create an array of proper size.
	    if (numHigh!=0){
	    	for (int i=0;i<numHigh;i++){
		    	stringGifts[i] = new ColorString();
	    		stringGifts[i].name=highGifts[i];
	    		stringGifts[i].color="Green";
	    	}
	    }
	    if (numMedium!=0){
	    	for (int i=0;i<numMedium;i++){
		    	stringGifts[i+numHigh] = new ColorString();
	    		stringGifts[i+numHigh].name=mediumGifts[i];
	    		stringGifts[i+numHigh].color="Yellow";
	    	}
	    }
	    if (numLow!=0){
	    	for (int i=0;i<numLow;i++){
	    		stringGifts[i+numHigh+numMedium]=new ColorString();
	    		stringGifts[i+numHigh+numMedium].name=lowGifts[i];
	    		stringGifts[i+numHigh+numMedium].color="Red";
	    	}
	    }
	    
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
		MyColorAdapter<ColorString> adapter = new MyColorAdapter<ColorString>(this,android.R.layout.simple_list_item_1, stringGifts);

		listViewGifts.setAdapter(adapter);
		BrowseCompanionActivity swtorCompanionActivity = new BrowseCompanionActivity();
        listViewGifts.setOnItemClickListener(new MyOnItemClickListener(this));
		
	}

	/*
	public void findGiftsOriginal(String gift) {
    	int numCursorRows;
        String[] highGifts = null; // will store an array.
        String[] mediumGifts = null; // will store an array.
        String[] lowGifts = null; // will store an array.
        int numHigh=0;
        int numMedium=0;
        int numLow=0;
    	int newSize = 0;
    	int adj = 0;
    	
        // Determine the gifts to put in the list.
        Cursor c;
        try {
            c = myDbHelper.searchGifts(gift,1);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    highGifts = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	highGifts[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numHigh = numCursorRows;
        }
    	
        // Determine the gifts to put in the list.
        try {
            c = myDbHelper.searchGifts(gift,2);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    mediumGifts = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	mediumGifts[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numMedium = numCursorRows;
        }
    	
        // Determine the gifts to put in the list.
        try {
            c = myDbHelper.searchGifts(gift,3);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    lowGifts = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	lowGifts[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numLow = numCursorRows;
        }
        
        if (numHigh>0) newSize+=1;
        if (numMedium>0) newSize+=1;
        if (numLow>0) newSize+=1;
	    stringGifts = new GiftString[newSize]; // create an array of proper size.
	    if (numHigh!=0){
	    	stringGifts[0] = new GiftString();
	    	stringGifts[0].name = "High";
	    	adj+=1;
	    	for (int i=0;i<numHigh;i++){
		    	stringGifts[i+adj] = new GiftString();
	    		stringGifts[i+adj].name=highGifts[i];
	    		stringGifts[i+adj].color="Green";
	    	}
	    }
	    if (numMedium!=0){
	    	stringGifts[numHigh+adj] = new GiftString();
	    	stringGifts[numHigh+adj].name = "Medium";
	    	adj+=1;
	    	for (int i=0;i<numMedium;i++){
		    	stringGifts[i+adj+numHigh] = new GiftString();
	    		stringGifts[i+adj+numHigh].name=mediumGifts[i];
	    		stringGifts[i+adj+numHigh].color="Yellow";
	    	}
	    }
	    if (numLow!=0){
	    	stringGifts[numHigh+numMedium+adj] = new GiftString();
	    	stringGifts[numHigh+numMedium+adj].name = "Low";
	    	adj+=1;
	    	for (int i=0;i<numLow;i++){
	    		stringGifts[i+adj+numHigh+numMedium]=new GiftString();
	    		stringGifts[i+adj+numHigh+numMedium].name=lowGifts[i];
	    		stringGifts[i+adj+numHigh+numMedium].color="Red";
	    	}
	    }
	    
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
		MyGiftAdapter<GiftString> adapter = new MyGiftAdapter<GiftString>(this,android.R.layout.simple_list_item_1, stringGifts);

		listViewGifts.setAdapter(adapter);
        listViewGifts.setOnItemClickListener(null);
		
	}
*/
	
	public void selectCompanion(String companionName) {

		int numCursorRows;
		String faction;
		String toonClass;
		int companionID;

		if (null == myDbHelper)
			initDatabase();
		
		
		// Determine the info for this name and start the detail activity for it.
        Cursor c;
        try {
            c = myDbHelper.findCompanionID(companionName);  // this returns 1 or more rows.
            											  // if no name is speicifed it returns all rows
            											  // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    companionID=c.getInt(0);
	    faction=c.getString(1); // This cursor has three columns id, faction and pic for given name.

        try {
            c = myDbHelper.findToonToonClassByCompanionID(companionID);  // this returns 1 or more rows.
            											  // if no name is speicifed it returns all rows
            											  // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    toonClass=c.getString(1); // 0 is toonclassid, 1 is toonclassname
	    c.close();

		
		
	  	Intent intent;
	    intent = new Intent(this,CompanionOverviewActivity.class);
	    intent.putExtra("name",companionName);
	    intent.putExtra("id",companionID);
	    intent.putExtra("faction",faction);
	    intent.putExtra("toonClass", toonClass);
	    startActivity(intent);
	    
		return;
			
	}
	
	
}
