package net.skink.swtor.toontracker;

import java.io.IOException;



import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CompanionGifts extends Activity {
    TextView prefersTextView;
    TextView likesTextView;
    TextView acceptableTextView;
    
	int companionID;
	Bundle theBundle;

	
    private static String TAG = "CompanionGeneral";
    String[] preferValues; // values for the likes list
    String[] likesValues; // values for the dislikes list
    String[] acceptableValues; // values for the likes list

	DatabaseHelper myDbHelper;
	
	@Override
	protected void onDestroy() {
		myDbHelper.close();
		super.onDestroy();
	}

	
	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.gifts);
   	
    	
        // Get the UI Components
        prefersTextView = (TextView) findViewById(R.id.textViewPrefers);
        likesTextView = (TextView) findViewById(R.id.textViewLikes);
        acceptableTextView = (TextView) findViewById(R.id.textViewAcceptable);

	    // the name
		theBundle = getIntent().getExtras();
		companionID = theBundle.getInt("id");

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


        // set the ingredient list to all.
	    setPrefers(companionID);
	    setLikes(companionID);
	    setAcceptable(companionID);
    }

	private void setPrefers(int companionID) {    	
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        int numRows;
        try {
            c = myDbHelper.listGiftsByPrefs(companionID,1);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
		numRows = c.getCount();
//		Log.d(TAG, "prefers c has "+numColumns+" columns."); 
	    preferValues = new String[numRows];
	    c.moveToFirst();
	    for (int i=0;i<numRows;i++) {
	    	preferValues[i]=c.getString(0); 	    		
	    	c.moveToNext();
	    }

    	    	
    	// Build the TextView
	    prefersTextView.setText("");
	    for (int i=0;i<numRows;i++) {
	    	if (null != preferValues[i]) {
	    		prefersTextView.append(preferValues[i]);
	    		prefersTextView.append("\n");
	    	}
	    }
		
	    c.close();

	}

	private void setLikes(int companionID) {
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        int numRows;

        try {
            c = myDbHelper.listGiftsByPrefs(companionID,2);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
		numRows = c.getCount();
//		Log.d(TAG, "likes c has "+numColumns+" columns."); 
	    likesValues = new String[numRows];
	    c.moveToFirst();
	    for (int i=0;i<numRows;i++) {
	    	likesValues[i]=c.getString(0); 
	    	c.moveToNext();
	    }

    	    	
    	// Build the TextView
	    likesTextView.setText("");
	    for (int i=0;i<numRows;i++) {
	    	if (null != likesValues[i]) {
	    		likesTextView.append(likesValues[i]);
	    		likesTextView.append("\n");
	    	}
	    }
	    c.close();
		
	}

	private void setAcceptable(int companionID) {

    	
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        int numRows;
      
        try {
            c = myDbHelper.listGiftsByPrefs(companionID,3);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
		numRows = c.getCount();
//		Log.d(TAG, "acceptable c has "+numColumns+" columns."); 
	    acceptableValues = new String[numRows];
	    c.moveToFirst();
	    for (int i=0;i<numRows;i++) {
	    	acceptableValues[i]=c.getString(0); 	    		
	    	c.moveToNext();
	    }

    	    	
    	// Build the TextView
	    acceptableTextView.setText("");
	    for (int i=0;i<numRows;i++) {
	    	if (null != acceptableValues[i]) {
	    		acceptableTextView.append(acceptableValues[i]);
	    		acceptableTextView.append("\n");
	    	}
	    }
	    c.close();
		
	}
}
