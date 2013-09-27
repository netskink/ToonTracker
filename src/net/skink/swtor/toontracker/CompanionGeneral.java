package net.skink.swtor.toontracker;

import java.io.IOException;



import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CompanionGeneral extends Activity {
    TextView theLikesTextView;
    TextView theDislikesTextView;
    
	String companionName;
	int companionID;
	Bundle theBundle;

	
    private static String TAG = "CompanionGeneral";
    String[] likeValues; // values for the likes list
    String[] dislikeValues; // values for the dislikes list

	DatabaseHelper myDbHelper;

	
	@Override
	protected void onDestroy() {
		myDbHelper.close();
		super.onDestroy();
	}
	
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.companion_general);

        // Get the UI Components
        theLikesTextView = (TextView) findViewById(R.id.textViewLikes);
        theDislikesTextView = (TextView) findViewById(R.id.textViewDislikes);

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
	    setLikes(companionID);
	    setDislikes(companionID);
        

    }

	private void setDislikes(int companionID) {

    	
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        int numRows;
        try {
            c = myDbHelper.listOpinionsByPreference(companionID,0);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
		numRows = c.getCount();
//		Log.d(TAG, "dislikes c has "+numColumns+" columns."); 
	    dislikeValues = new String[numRows];
	    c.moveToFirst();
	    for (int i=0;i<numRows;i++) {
	    	dislikeValues[i]=c.getString(0); 
	    	c.moveToNext();
	    }

    	    	
    	// Build the TextView
	    theDislikesTextView.setText("");
	    for (int i=0;i<numRows;i++) {
	    	if (null != dislikeValues[i]) {
	    		theDislikesTextView.append(dislikeValues[i]);
	    		theDislikesTextView.append("\n");
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
            c = myDbHelper.listOpinionsByPreference(companionID,1);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
		numRows = c.getCount();
//		Log.d(TAG, "likes c has "+numColumns+" columns.");
	    likeValues = new String[numRows];
	    c.moveToFirst();
	    for (int i=0;i<numRows;i++) {
    	    likeValues[i]=c.getString(0);
    	    c.moveToNext();
	    }

    	    	
    	// Build the TextView
    	theLikesTextView.setText("");
	    for (int i=0;i<numRows;i++) {
	    	if (null != likeValues[i]) {
	    		theLikesTextView.append(likeValues[i]);
	    		theLikesTextView.append("\n");
	    	}
	    }    	
	    
	    c.close();
		
	}
}
