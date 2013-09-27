package net.skink.swtor.toontracker;

import java.io.IOException;



import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ToonGeneral extends Activity {

	Bundle theBundle;
	
	// Stuff from the bundle
	String toonName;
	String toonFactionName;
	String toonClassName;
	String toonAdvClassName;
	String toonAdvClassSpecialName;
	int toonClassID;
	int toonAdvClassID;
	int toonAdvClassSpecialID;
	int toonID;
		
	
	// UI components
	TextView textViewAdvClassName;
	TextView textViewRole;
	TextView textViewPrimaryStat;
	TextView textViewSecondaryStat;
	TextView textViewArmor;
	TextView[] textViewWeapons = new TextView[3];
	TextView textViewNotes;
	
    private static String TAG = "ToonGeneral";

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
		
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.toon_general);
	    // Get the UI components
	    textViewAdvClassName = (TextView) findViewById(R.id.textViewAdvClassName);	  
	    textViewRole = (TextView) findViewById(R.id.textViewRole);	  
	    textViewPrimaryStat = (TextView) findViewById(R.id.textViewPrimaryStat);	  
	    textViewSecondaryStat = (TextView) findViewById(R.id.textViewSecondaryStat);	  
	    textViewArmor = (TextView) findViewById(R.id.textViewArmor);	  
	    textViewWeapons[0] = (TextView) findViewById(R.id.textViewWeapons1);	  
	    textViewWeapons[1] = (TextView) findViewById(R.id.textViewWeapons2);	  
	    textViewWeapons[2] = (TextView) findViewById(R.id.textViewWeapons3);	  
	    textViewNotes = (TextView) findViewById(R.id.textViewNotes);	  
	    
	    
	    
	    // Get the stuff from the bundle
		theBundle = getIntent().getExtras();


		toonName=theBundle.getString("toonName");
		toonFactionName=theBundle.getString("toonFactionName");
	    
		toonClassID=theBundle.getInt("toonClassID");
		toonAdvClassID=theBundle.getInt("toonAdvClassID");
		toonAdvClassSpecialID=theBundle.getInt("toonAdvClassSpecialID");
	    
		toonClassName=theBundle.getString("toonClassName");
		toonAdvClassName=theBundle.getString("toonAdvClassName");
		toonAdvClassSpecialName=theBundle.getString("toonAdvClassSpecialName");
		

		
		// Using the bundle, set the UI components.		
		textViewAdvClassName.setText(toonAdvClassName+" - "+toonAdvClassSpecialName);


		if (null == myDbHelper)
			initDatabase();
       
		updateRoleAndStats();
		updateArmor();
		updateWeapons();

    }



	private void updateWeapons() {
		int numCursorRows;
		
		if (null == myDbHelper)
			initDatabase();
		// select foo.goo as X
		
		// Using the database, set the UI component.		
        Cursor c;
        try {
            c = myDbHelper.findAdvClassWeapons(toonAdvClassID);  
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
        numCursorRows = c.getCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
		    textViewWeapons[i].setText(c.getString(i));
	    	c.moveToNext();
	    }

	    for (int i=numCursorRows;i<3;i++){
		    textViewWeapons[i].setText("");	    	
	    }
	    
	    c.close();

	}



	private void updateArmor() {
		if (null == myDbHelper)
			initDatabase();
		// select foo.goo as X
		
		// Using the database, set the UI component.		
        Cursor c;
        try {
            c = myDbHelper.findAdvClassArmor(toonAdvClassID);  
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    textViewArmor.setText(c.getString(0));
//	    textViewWeapons1.setText(c.getString(1));
//	    textViewWeapons2.setText(c.getString(2));
//	    textViewWeapons3.setText(c.getString(3));
	    c.close();

	}



	private void updateRoleAndStats() {
		
		if (null == myDbHelper)
			initDatabase();
		
		
		// Using the database, set the UI component.		
        Cursor c;
        try {
            c = myDbHelper.findAdvClassSpecialRoleAndStats(toonAdvClassSpecialID);  
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
		textViewRole.setText(c.getString(0));
		textViewPrimaryStat.setText(c.getString(1));
		textViewSecondaryStat.setText(c.getString(2));
	    c.close();
		
	}

		
}
