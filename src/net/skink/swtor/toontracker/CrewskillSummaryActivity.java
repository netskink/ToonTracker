package net.skink.swtor.toontracker;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CrewskillSummaryActivity extends MyMenuActivity{

	
    private static String TAG = "CrewskillSummaryActivity";
    String[] stringCellNames; // will store an array.
    int[] toonIDs;
    int[] crewskillIDs;
    int[] crewskillLevels;
    GridView gridview;
    MyBaseAdapter myBaseAdapter;
    int minValue = 400;
    int minPosition = 0;

    
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
        setContentView(R.layout.crewskill_summary);

        gridview = (GridView) findViewById(R.id.gridview);
        myBaseAdapter = new MyBaseAdapter(this,R.array.crewskill_summary_array,screenWidth,screenHeight);
        gridview.setAdapter(myBaseAdapter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	myBaseAdapter.getItem(position);
                Toast.makeText(CrewskillSummaryActivity.this, "" + myBaseAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
 
        // Get the UI Components


        // init the database
        initDatabase();
        
        
        updateGridValues();
    }
    
    private void updateGridValues() {
    	getListofToons();
    	setColumnHeadings();
    	getListofCrewskills();
    	setColumn(0);   // range is 0 to 6

    	
    	
    	for (int i=0;i<6;i++){
     	    for (int j=0;j<14;j++) {
     	    	stringCellNames[j]="";
     	    }
    		getCrewskillLevelsByID(toonIDs[i],1,i);
    		getCrewskillLevelsByID(toonIDs[i],2,i);
    		getCrewskillLevelsByID(toonIDs[i],3,i);
        	setColumn(i+1);   // range is 0 to 6

    	}
//        Toast.makeText(CrewskillSummaryActivity.this, "" + minValue, Toast.LENGTH_LONG).show();
//        Toast.makeText(CrewskillSummaryActivity.this, "" + minPosition, Toast.LENGTH_LONG).show();
        myBaseAdapter.minPosition=minPosition;
    	
    }
    
    private void setColumnHeadings() {
    	for(int i=0;i<6;i++){
    		// the i+1 is because first column name is set to blank.
    		myBaseAdapter.mGridViewArrayStrings[i+1] = stringCellNames[i];  // i+1 since (0,0) is a blank
    	}
	}
    private void setColumn(int col) { // the first column is 0
    	// We are updating the table for all rows for a particular column.
    	// There are 7 columns. one for the skill name and 6 for the toon name.
    	// There are 14 trade skills so 15 rows. one for the toon names and one for each of the trade skills
    	// Assume the cellnames array is complete for one toon or trade skill list.
    	int cellcount = col;
    	for(int i=0;i<14;i++){
    		cellcount=cellcount+7;  // Each new row is bumped by the numeber of columns to skip.
    		if (col == cellcount%(7*(i+1))) {
        		myBaseAdapter.mGridViewArrayStrings[cellcount] = stringCellNames[i];  // i+1 since (0,0) is a blank    			
    		}
    	}
		
	}


	private void getListofToons() {
		int toonID;
		int numCursorRows;
		int numCursorColumns;

		if (null == myDbHelper)
			initDatabase();
		
		
		// Determine the info for this name and start the detail activity for it.
        Cursor c;
        try {
            c = myDbHelper.getListOfToons();  
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
// 		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
 	    stringCellNames = new String[numCursorRows]; // create an array of proper size.
 	    toonIDs = new int[numCursorRows];
 	    c.moveToFirst();
 	    for (int i=0;i<numCursorRows;i++) {
 	    	stringCellNames[i]=c.getString(0); 
 	    	toonIDs[i] = c.getInt(2);
 	    	c.moveToNext();
 	    }
 	    c.close();


    }
	
	private void getListofCrewskills() {
		int toonID;
		int numCursorRows;
		int numCursorColumns;

		if (null == myDbHelper)
			initDatabase();
		
		
		// Determine the info for this name and start the detail activity for it.
        Cursor c;
        try {
            c = myDbHelper.getListOfCrewSkillNames(); // id, name  
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
// 		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
 	    stringCellNames = new String[numCursorRows]; // create an array of proper size.
 	    crewskillIDs = new int[numCursorRows];
 	    c.moveToFirst();
 	    for (int i=0;i<numCursorRows;i++) {
 	    	crewskillIDs[i] = c.getInt(0);
 	    	stringCellNames[i]=c.getString(1); 
 	    	c.moveToNext();
 	    }
 	    c.close();
    }
	
	private void getCrewskillLevelsByID(int toonID,int crewskillNum, int position) {
		int numCursorRows;
		int numCursorColumns;
		int crewskillID;
		int crewskillLevel;

		if (null == myDbHelper)
			initDatabase();
		
		
		// Determine the info for this name and start the detail activity for it.
        Cursor c;
        try {
            c = myDbHelper. findCrewSkillLevels(toonID, crewskillNum); // level, id  
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
// 		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
 	    crewskillLevels = new int[14];
 	    c.moveToFirst();
    	crewskillLevel=c.getInt(0); 
    	crewskillID=c.getInt(1);
 	    c.close();
 	    for (int i=0;i<14;i++) {
 	    	if (crewskillID==crewskillIDs[i]) {
 		    	stringCellNames[i]=Integer.toString(crewskillLevel); 
 		    	// how to calculate minPosition.
 		    	// The minPosition corresponds to the minimum cell.  The cells count from 0 at 
 		    	// position 0,0 in the grid and go left to right, top to bottom.
 		    	// The first ros is 0-7.  
 		    	// i in the outer loop in the caller varies from 0-5 which correspond to the colunmns.
 		    	// however, it omits the first column.
 		    	// so cell is 7 (first row) + 1 (first column)+ i*7+position
 		    	if (crewskillLevel < minValue) {
 		    		minValue = crewskillLevel;
 		    		minPosition = 7+1+(i*7)+position;
 		    	}
 	    	}
 	    }


    }

}