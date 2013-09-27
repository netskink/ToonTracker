package net.skink.swtor.toontracker;

import java.io.IOException;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class ToonSkillsTabActivity extends Activity implements OnTouchListener {

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
	int[] toonSkillIDs = new int[3];
		
	
	// UI components
	TextView[] textViewCrewSkill = new TextView[3];
	TextView[] textViewCrewSkillYields = new TextView[3];
	TextView[] textViewCrewSkillLevel = new TextView[3];
	
    private static String TAG = "ToonSkillsTabActivity";

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
	    setContentView(R.layout.toon_crewskills);
	    // Get the UI components
	    textViewCrewSkill[0] = (TextView) findViewById(R.id.textViewCrewSkill1);	  
	    textViewCrewSkill[1] = (TextView) findViewById(R.id.textViewCrewSkill2);	  
	    textViewCrewSkill[2] = (TextView) findViewById(R.id.textViewCrewSkill3);	  
	    textViewCrewSkillYields[0] = (TextView) findViewById(R.id.textViewYields1);	  
	    textViewCrewSkillYields[1] = (TextView) findViewById(R.id.textViewYields2);	  
	    textViewCrewSkillYields[2] = (TextView) findViewById(R.id.textViewYields3);	
	    textViewCrewSkillLevel[0] = (TextView) findViewById(R.id.textViewCrewSkillLevel1);	  
	    textViewCrewSkillLevel[1] = (TextView) findViewById(R.id.textViewCrewSkillLevel2);	  
	    textViewCrewSkillLevel[2] = (TextView) findViewById(R.id.textViewCrewSkillLevel3);	  
	    for (int i=0;i<3;i++) {
	    	textViewCrewSkillLevel[i].setOnTouchListener(this);
	    }
	    
	    
	    
	    // Get the stuff from the bundle
		theBundle = getIntent().getExtras();


		toonName=theBundle.getString("toonName");
		toonID=theBundle.getInt("toonID");
		toonFactionName=theBundle.getString("toonFactionName");
	    
		toonClassID=theBundle.getInt("toonClassID");
		toonAdvClassID=theBundle.getInt("toonAdvClassID");
		toonAdvClassSpecialID=theBundle.getInt("toonAdvClassSpecialID");
	    
		toonClassName=theBundle.getString("toonClassName");
		toonAdvClassName=theBundle.getString("toonAdvClassName");
		toonAdvClassSpecialName=theBundle.getString("toonAdvClassSpecialName");
		

		


		if (null == myDbHelper)
			initDatabase();
       
		updateCrewSkillNames();
		updateCrewSkillYields();
		updateCrewSkillLevels();

    }



	private void updateCrewSkillNames() {
		
		int numCursorRows;
		int numCursorColumns;
		
		if (null == myDbHelper)
			initDatabase();
		// select foo.goo as X
		
		// Using the database, set the UI component.		
        Cursor c;
        for (int i=0;i<3;i++){
            try {
                c = myDbHelper.findCrewSkillNames(toonID,i+1);  
            }catch(SQLException sqle){
            	throw sqle;
            }
    	    c.moveToFirst();
            numCursorRows = c.getCount();
            numCursorColumns = c.getColumnCount();
    		Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorRows+" columns"); 
    	    c.moveToFirst();
    	    textViewCrewSkill[i].setText(c.getString(0));
    	    toonSkillIDs[i]=c.getInt(1);
    	    c.close();
        	
        }
	    

	}


	private void updateCrewSkillYields() {
		
		int numCursorRows;
		int numCursorColumns;
		
		if (null == myDbHelper)
			initDatabase();
		// select foo.goo as X
		
		// Using the database, set the UI component for yields 1		
        Cursor c;
        for (int i=0;i<3;i++){
	        try {
	            c = myDbHelper.findCrewSkillYields(toonID,i+1);  
	        }catch(SQLException sqle){
	        	throw sqle;
	        }
		    c.moveToFirst();
	        numCursorRows = c.getCount();
	        numCursorColumns = c.getColumnCount();
			Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorRows+" columns"); 
		    c.moveToFirst();
			textViewCrewSkillYields[i].setText("");
		    for (int j=0;j<numCursorRows;j++) {
			    textViewCrewSkillYields[i].append(c.getString(1)+"\n");  // 0 is the crewskill name, 1 is the yields
		    	c.moveToNext();
		    }
		    
		    c.close();
        }
	    
	    
	    
	    
	}



	private void updateCrewSkillLevels() {
		
		int numCursorRows;
		int numCursorColumns;
		
		if (null == myDbHelper)
			initDatabase();
		// select foo.goo as X
		
		// Using the database, set the UI component.		
        Cursor c;
        for (int i=0;i<3;i++){
            try {
                c = myDbHelper.findCrewSkillLevels(toonID,i+1);  
            }catch(SQLException sqle){
            	throw sqle;
            }
            if (null !=c) {
	    	    c.moveToFirst();
	            numCursorRows = c.getCount();
	            numCursorColumns = c.getColumnCount();
	    		Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorRows+" columns"); 
	    		if (0!=numCursorRows) {
		    	    c.moveToFirst();
		    	    textViewCrewSkillLevel[i].setText(c.getString(0));	    			
	    		}
            }
    	    c.close();
        	
        }
	    

	}


	@Override
	public boolean onTouch(View v, MotionEvent arg1) {
	  	Intent intent;
	    intent = new Intent(this,EditValueActivity.class);

		
		switch (v.getId()){
		case R.id.textViewCrewSkillLevel1:
		    startActivityForResult(intent, 1);
			break;
		case R.id.textViewCrewSkillLevel2:
		    startActivityForResult(intent, 2);
			break;
		case R.id.textViewCrewSkillLevel3:
		    startActivityForResult(intent, 3);
			break;
		}
		return false;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String result;
		Bundle theBundle;
		
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==Activity.RESULT_OK) {
			theBundle = data.getExtras();
			result = theBundle.getString("result"); // result is the value of the edit box

			switch(requestCode){
				case(1):{ // is the message id i specified. in skills above I specifed 1, 2 or 3 based on the skill.
		    		Log.d(TAG, "1 is ok with result = "+result); 
				break;
				}
				case(2):{ // is the message id i specified. in skills above I specifed 1, 2 or 3 based on the skill.
		    		Log.d(TAG, "1 is ok with result = "+result); 
				break;
				}
				case(3):{ // is the message id i specified. in skills above I specifed 1, 2 or 3 based on the skill.
		    		Log.d(TAG, "1 is ok with result = "+result); 
				break;
				}
			}
			textViewCrewSkillLevel[requestCode-1].setText(result);
			commitCrewSkillLevels(toonSkillIDs[requestCode-1], Integer.parseInt(result));

		}
	}
	
	private void commitCrewSkillLevels(int skillID, int skillLevel) {
		
		int numCursorRows;
		int numCursorColumns;
		
		if (null == myDbHelper)
			initDatabase();
		// select foo.goo as X
		
		// Using the database, set the UI component.		
        try {
        	myDbHelper.setCrewSkillLevels(toonID,skillID,skillLevel);  
        }catch(SQLException sqle){
        	throw sqle;
        }
	}
	
}
