package net.skink.swtor.toontracker;

import java.io.IOException;


import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CompanionSkills extends Activity {
    TextView armorTextView;
    TextView weaponsTextView;
    TextView primaryStatTextView;
    TextView crewSkillTextView;
    TextView notesTextView;
    
	int companionID;
	Bundle theBundle;

	
    private static String TAG = "CompanionSkills";
    String[] armorValues; // values for the likes list
    String[] weaponsValues; // values for the dislikes list
    String[] primaryStatValues; // values for the likes list
    String[] crewSkillValues; // values for the dislikes list
    String[] notesValues; // values for the dislikes list

	DatabaseHelper myDbHelper;	
	
	@Override
	protected void onDestroy() {
		myDbHelper.close();
		super.onDestroy();
	}

	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.skills);
   	
    	
        // Get the UI Components
        armorTextView = (TextView) findViewById(R.id.textViewArmor);
        weaponsTextView = (TextView) findViewById(R.id.textViewWeapons);
        primaryStatTextView = (TextView) findViewById(R.id.textViewPrimaryStat);
        crewSkillTextView = (TextView) findViewById(R.id.textViewCrewSkillBonus);
        notesTextView = (TextView) findViewById(R.id.textViewNotes);

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
	    setCrewSkill(companionID);
	    setStats(companionID);
	    setNotes(companionID);
    }
    
	private void setCrewSkill(int companionID) {
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        try {
            c = myDbHelper.listCrewSkill(companionID);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
//		Log.d(TAG, "prefers c has "+numColumns+" columns."); 
	    crewSkillValues = new String[numColumns];
	    c.moveToFirst();
	    for (int i=0;i<numColumns;i++) {
	    	crewSkillValues[i]=c.getString(i); 	    		
	    }

    	    	
    	// Build the TextView
	    crewSkillTextView.setText("");
	    for (int i=0;i<numColumns;i++) {
	    	if (null != crewSkillValues[i]) {
	    		crewSkillTextView.append(crewSkillValues[i]);
	    		crewSkillTextView.append("\n");
	    	}
	    }
	    c.close();

	}
    
	private void setStats(int companionID) {
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        try {
            c = myDbHelper.listStats(companionID);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
//		Log.d(TAG, "prefers c has "+numColumns+" columns."); 
	    primaryStatValues = new String[numColumns];
	    c.moveToFirst();
	    for (int i=0;i<numColumns;i++) {
	    	primaryStatValues[i]=c.getString(i); 	    		
	    }

    	    	
    	// Build the TextView
	    // it should store the values in the loop above to primary stat, armor and weapons
	    primaryStatTextView.setText(primaryStatValues[0]);
	    armorTextView.setText(primaryStatValues[1]);
	    weaponsTextView.setText(primaryStatValues[2]);

	    c.close();

	    
	}
	   
	private void setNotes(int companionID) {
        // Determine the ingredients to put in the list.
        Cursor c;
        int numColumns;
        try {
            c = myDbHelper.listNotes(companionID);
        }catch(SQLException sqle){
        	throw sqle;
        }
		numColumns = c.getColumnCount();
//		Log.d(TAG, "notes c has "+numColumns+" columns."); 
	    notesValues = new String[numColumns];
	    c.moveToFirst();
	    for (int i=0;i<numColumns;i++) {
	    	notesValues[i]=c.getString(i); 	    		
	    }

    	    	
    	// Build the TextView
	    notesTextView.setText("");
	    for (int i=0;i<numColumns;i++) {
	    	if (null != notesValues[i]) {
	    		notesTextView.append(notesValues[i]);
	    		notesTextView.append("\n");
	    	}
	    }

	    c.close();

	}


}
