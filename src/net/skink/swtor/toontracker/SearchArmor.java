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

public class SearchArmor extends Activity {

    // UI Components
    ListView listViewArmors;
    TextView textViewArmor;
	
    private static String TAG = "SearchArmor";
    ArmorString[] stringArmors; // will store an array.

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
    	setContentView(R.layout.search_armor);	
    	
        listViewArmors = (ListView) findViewById(R.id.listView1);
  //      textViewArmor = (TextView) findViewById(R.id.textViewArmor);

        if (null == myDbHelper)
        	initDatabase();

        // set the gifts list to all
	    setArmorsList(null);
    	
		
	}


	private void setArmorsList(Object object) {
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
	    stringArmors = new ArmorString[numCursorRows]; // create an array of proper size.
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	stringArmors[i]=new ArmorString();
	    	stringArmors[i].name=c.getString(0); // This cursor has two columns companion name and faction side
	    	c.moveToNext();
	    }

    	    	
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
		MyArmorAdapter<ArmorString> adapter = new MyArmorAdapter<ArmorString>(this,android.R.layout.simple_list_item_1, stringArmors);

		listViewArmors.setAdapter(adapter);
        listViewArmors.setOnItemClickListener(new MyArmorsOnItemClickListener(this));
	}


	public void findArmors(String armor) {
    	int numCursorRows;
        String[] highArmors = null; // will store an array.
        int numHigh=0;
    	int newSize = 0;
    	
    	textViewArmor.setText(armor);
    	
    	
        // Determine the gifts to put in the list.
        Cursor c;
        try {
            c = myDbHelper.searchArmors(armor);
        }catch(SQLException sqle){
        	throw sqle;
        }
        if (null != c) {
	        numCursorRows = c.getCount();
//			Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
		    highArmors = new String[numCursorRows]; // create an array of proper size.
		    c.moveToFirst();
		    for (int i=0;i<numCursorRows;i++) {
		    	highArmors[i]=c.getString(0); // This cursor has two columns companion name and faction side
		    	c.moveToNext();
		    }
	        newSize += numCursorRows;
	        numHigh = numCursorRows;
        }
        
	    stringArmors = new ArmorString[newSize]; // create an array of proper size.
	    if (numHigh!=0){
	    	for (int i=0;i<numHigh;i++){
		    	stringArmors[i] = new ArmorString();
	    		stringArmors[i].name=highArmors[i];
	    		stringArmors[i].color="Green";
	    	}
	    }
	    
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
		MyArmorAdapter<ArmorString> adapter = new MyArmorAdapter<ArmorString>(this,android.R.layout.simple_list_item_1, stringArmors);

		listViewArmors.setAdapter(adapter);
		BrowseCompanionActivity swtorCompanionActivity = new BrowseCompanionActivity();
        listViewArmors.setOnItemClickListener(new MyOnItemClickListener(this));
		
	}


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
            c = myDbHelper.findToonClass(companionID);  // this returns 1 or more rows.
            											  // if no name is speicifed it returns all rows
            											  // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    toonClass=c.getString(0); // This cursor has one column, the base class name for the toon who has this companion.
		
		
		
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
