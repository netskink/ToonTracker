package net.skink.swtor.toontracker;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class BrowseToonsActivity extends MyMenuActivity {
	
    // UI Components
    ListView theList;
    
	
    private static String TAG = "BrowseToonsActivity";
    ToonOrCompanionName[] newValues; // will store an array.

    
	@Override
	protected void onDestroy() {
		myDbHelper.close();
		super.onDestroy();
	}

	
	private void initDatabase() {

		if (null == myDbHelper) {
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
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.browse_toons);
        
        // Get the UI Components
        theList = (ListView) findViewById(R.id.listView1);

        initDatabase();

        // set the ingredient list to all.
	    setListViewContents();

	}

    public void setListViewContents() {

    	int numCursorRows;
    	
        // Determine the ingredients to put in the list.
        Cursor c;
        try {
            c = myDbHelper.getListOfToons();
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
	    newValues = new ToonOrCompanionName[numCursorRows]; // create an array of proper size.
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	// todo: look into this html style later.
	    	//myTextView.setText(Html.fromHtml("<p style="color:red">" + someText + "</p>"));
//	    	values[i]=Html.fromHtml("<p style="color:red">" + "foo" + "</p>");
	    	newValues[i] = new ToonOrCompanionName();
	    	newValues[i].name=c.getString(0); // This cursor has two columns companion name and faction side
	    	newValues[i].faction=c.getString(1); // This cursor has two columns companion name and faction side
	    	c.moveToNext();
	    }

    	    	
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
		MyAdapter<ToonOrCompanionName> adapter = new MyAdapter<ToonOrCompanionName>(this,android.R.layout.simple_list_item_1, newValues);

		theList.setAdapter(adapter);
        theList.setOnItemClickListener(new MyOnItemClickListener(this));
	    c.close();

    }


	public void selectToon(String toonName) {

		int numCursorRows;
		String toonFactionName;
		String toonClassName;
		String toonAdvClassName;
		String toonAdvClassSpecialName;
		int toonClassID;
		int toonAdvClassID;
		int toonAdvClassSpecialID;
		int toonID;

		if (null == myDbHelper)
			initDatabase();
		
		
		// Determine the info for this name and start the detail activity for it.
        Cursor c;
        try {
            c = myDbHelper.findToon(toonName);  // this returns 1 or more rows.
            											  // if no name is speicifed it returns all rows
            											  // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    toonID=c.getInt(0);
	    toonFactionName=c.getString(1); // This cursor has three columns id, faction and pic for given name.
	    c.close();
	    
	    
	    
	    
        try {
            c = myDbHelper.findToonClass(toonID);  // this returns 1 or more rows.
            									   // if no name is speicifed it returns all rows
            									   // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    toonClassID=c.getInt(0); // This cursor has three columns, base class, adv class, advclass special.
	    toonAdvClassID=c.getInt(1); // This cursor has three columns, base class, adv class, advclass special.
	    toonAdvClassSpecialID=c.getInt(2); // This cursor has three columns, base class, adv class, advclass special.
	    toonClassName=c.getString(3); // This cursor has three columns, base class, adv class, advclass special.
	    toonAdvClassName=c.getString(4); // This cursor has three columns, base class, adv class, advclass special.
	    toonAdvClassSpecialName=c.getString(5); // This cursor has three columns, base class, adv class, advclass special.
	    c.close();
		
		
		
	  	Intent intent;
	    intent = new Intent(this,ToonOverviewActivity.class);
	    intent.putExtra("toonName",toonName);
	    intent.putExtra("toonID",toonID);
	    intent.putExtra("toonFactionName",toonFactionName);
	    
	    intent.putExtra("toonClassID", toonClassID);
	    intent.putExtra("toonAdvClassID", toonAdvClassID);
	    intent.putExtra("toonAdvClassSpecialID", toonAdvClassSpecialID);
	    
	    intent.putExtra("toonClassName", toonClassName);
	    intent.putExtra("toonAdvClassName", toonAdvClassName);
	    intent.putExtra("toonAdvClassSpecialName", toonAdvClassSpecialName);

	    startActivity(intent);
	    
		return;
	}    
    
	
	
	
	
}
