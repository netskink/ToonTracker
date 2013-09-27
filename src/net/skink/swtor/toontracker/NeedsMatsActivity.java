package net.skink.swtor.toontracker;

import java.io.IOException;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class NeedsMatsActivity 
	extends MyMenuActivity 
	implements OnClickListener, OnItemLongClickListener {
	
    // UI Components	
    GridView gridview;
    MyNeedsMatsBaseAdapter myNeedsMatsBaseAdapter;
    Button buttonNew;

    
    // Data
    String[] stringCellNames; // will store an array.

	
    private static String TAG = "NeedsMatsActivity";

    
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
	protected void onResume() {
		updateGridValues();
	    myNeedsMatsBaseAdapter.notifyDataSetChanged();
	    super.onResume();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.needs_mats);
        
        // Get the UI Components
        gridview = (GridView) findViewById(R.id.gridView1);
        myNeedsMatsBaseAdapter = new MyNeedsMatsBaseAdapter(this,R.array.needs_mats_array);
        gridview.setAdapter(myNeedsMatsBaseAdapter);
		gridview.setOnItemLongClickListener(this);
    	buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(this);    	    	

        

        initDatabase();
        
        updateGridValues();


	}

    public void updateGridValues() {
    	getListofNeeds();

    	// Erase everything from first row on.  We get the size of the array form the resource
    	// This needs to be fixed, its still using prototype stuff.
    	// Also the gridviewarraystrings is just the stuff from the database and it does not include the
    	// column headings.
    	for(int i=5;i<myNeedsMatsBaseAdapter.mGridViewArrayStrings.length;i++){
    		// the i+1 is because first column name is set to blank.
    		myNeedsMatsBaseAdapter.mGridViewArrayStrings[i] = "";  
    	}
 	    
    	for(int i=0;i<stringCellNames.length;i++){
    		// the i+5 is because first row is the header.
    		myNeedsMatsBaseAdapter.mGridViewArrayStrings[i+5] = stringCellNames[i];  
    	}
    	
    }
	

	private void getListofNeeds() {
		int toonID;
		int numCursorRows;
		int numCursorColumns;

		if (null == myDbHelper)
			initDatabase();
		

		// String [] columns=new String[]{"toon","yield","grade","count","name"}; 

		
		// Determine the info for this name and start the detail activity for it.
        Cursor c;
        try {
            c = myDbHelper.getListOfCrewSkillNeeds();  
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
// 		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
 	    stringCellNames = new String[numCursorRows*5]; // create an array of proper size.
 	    c.moveToFirst();
 	    int j=0;
 	    for (int i=0;i<numCursorRows;i++) {
 	    	stringCellNames[j]=c.getString(0); // toon 
 	    	stringCellNames[j+1]=c.getString(1); // yield
 	    	stringCellNames[j+2]=c.getString(2); // grade
 	    	stringCellNames[j+3]=c.getString(3); // count
 	    	stringCellNames[j+4]=c.getString(4); // yield
 	    	j=j+5;
 	    	c.moveToNext();
 	    }
 	    c.close();

 	    

    }
    
    
	@Override
	public void onClick(View v) {
	  	Intent intent;

	  	switch(v.getId()) {
	       	case R.id.buttonNew:
	    	    intent = new Intent(this,NewNeedsActivity.class);
//	    	    intent.putExtra("toonClass", toonClass);
	    	    startActivity(intent);
	       		break;
	       }			
	}


	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
		String toon="",yield="", grade="", name="", count = "";
		int column,row;
		column = position%5;
		row = position/5;
		toon = myNeedsMatsBaseAdapter.mGridViewArrayStrings[row*5];
		yield = myNeedsMatsBaseAdapter.mGridViewArrayStrings[row*5+1];
		grade = myNeedsMatsBaseAdapter.mGridViewArrayStrings[row*5+2];
		count = myNeedsMatsBaseAdapter.mGridViewArrayStrings[row*5+3];
		name = myNeedsMatsBaseAdapter.mGridViewArrayStrings[row*5+4];
 //		Log.d(TAG, "long click position "+ position); 
 //		Log.d(TAG, "long click row "+ row); 
 //		Log.d(TAG, "long click on toon "+ toon); 
 //		Log.d(TAG, "long click on yield "+ yield); 
 //		Log.d(TAG, "long click on grade "+ grade); 
 //		Log.d(TAG, "long click on count "+ count); 
 //		Log.d(TAG, "long click on name "+ name); 
 		
 		deleteRow(toon,yield,grade,count,name);
		updateGridValues();
	    myNeedsMatsBaseAdapter.notifyDataSetChanged();
		return true;
	}


	private void deleteRow(String toon, String yield, String grade,String count, String name) {
		if (null == myDbHelper)
			initDatabase();
		

		// String [] columns=new String[]{"toon","yield","grade","count","name"}; 

		
		// Determine the info for this name and start the detail activity for it.
        try {
            myDbHelper.deleteCrewSkillNeedsRow(toon, yield, grade, count, name);  
        }catch(SQLException sqle){
        	throw sqle;
        }
	}

	
	
	
}
