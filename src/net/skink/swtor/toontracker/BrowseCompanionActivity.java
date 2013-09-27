package net.skink.swtor.toontracker;

import java.io.IOException;



import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ListView;
import android.widget.TextView;

public class BrowseCompanionActivity extends MyMenuActivity implements OnClickListener {
	
    // UI Components
    ListView theList;
    TextView companionFilter;
    
	
    private static String TAG = "SwtorCompanion";
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
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companion_main);
        
        // Get the UI Components
        theList = (ListView) findViewById(R.id.listView1);
        companionFilter = (TextView) findViewById(R.id.textView5);

        initDatabase();

        // set the list to all.
        setListViewContents();
        
        
    }

    
    public void setListViewContents() {

    	int numCursorRows;
    	
        // Determine the ingredients to put in the list.
        Cursor c;
        try {
            c = myDbHelper.listCompanions();
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
    
    
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
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
	    c.close();
	    
        try {
            c = myDbHelper.findToonToonClassByCompanionID(companionID);  // this returns 1 or more rows.
            											  // if no name is speicifed it returns all rows
            											  // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    toonClass=c.getString(1); // 0 is toon class id, 1 is toonclass name
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