package net.skink.swtor.toontracker;

import java.io.IOException;


import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewNeedsActivity extends Activity implements OnClickListener {
	
	// UI components
	Button buttonOK;
	Button buttonCancel;
	EditText editTextName;
	Spinner spinnerToon;
	Spinner spinnerYield;
	Spinner spinnerGrade;
	Spinner spinnerCount;
	
	// arrays for the spinners
	/* if you want to use a hard coded array, use this */
	//String[] arrayAdvClassSpecialNames = {"assassin","Commando","Juggernaut","Marauder","Mercenary","Operative","Powertech","Sage",	"Shadow","Sniper","Sorcerer","Vanguard"};
	String[] arrayToonNames;
	int[] arrayToonIDs;
	String[] arrayYields;
	int[] arrayYieldIDs;

	// data from the UI
	String toonName;
	String yield;
	String grade;
	String count;
	String name;

	
    private static String TAG = "NewNeedsActivity";	
	
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
    	setContentView(R.layout.new_need);
    	
        buttonOK = (Button) findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(this);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextNeedName);

        spinnerToon = (Spinner) findViewById(R.id.spinnerToon);
        spinnerYield = (Spinner) findViewById(R.id.spinnerYield);
        spinnerGrade = (Spinner) findViewById(R.id.spinnerGrade);
        spinnerCount = (Spinner) findViewById(R.id.spinnerCount);

        // Init the database
        initDatabase();

        // load the arrays based upon the database
        populateToonArray();
        populateYieldsArray();

        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayToonNames);
        namesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToon.setAdapter(namesAdapter);    	
        spinnerToon.setOnItemSelectedListener(new MyOnItemSelectedListener(this));
        
        ArrayAdapter<String> yieldsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayYields);
        yieldsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYield.setAdapter(yieldsAdapter);    	
        spinnerYield.setOnItemSelectedListener(new MyOnItemSelectedListener(this));

        // This is the way to use a resource in res/values/strings.xml
        ArrayAdapter<CharSequence> gradeAdapter = ArrayAdapter.createFromResource(
    		 this, R.array.mygrade_array, android.R.layout.simple_spinner_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(gradeAdapter);    	
        spinnerGrade.setOnItemSelectedListener(new MyOnItemSelectedListener(this));        

        // This is the way to use a resource in res/values/strings.xml
        ArrayAdapter<CharSequence> countAdapter = ArrayAdapter.createFromResource(
     		 this, R.array.mycount_array, android.R.layout.simple_spinner_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCount.setAdapter(countAdapter);    	
        spinnerCount.setOnItemSelectedListener(new MyOnItemSelectedListener(this));        
	
	
	}



	private void populateToonArray() {
				
		int numCursorRows;
		int numCursorColumns;
		       		
		Cursor c;
        try {
            c = myDbHelper.getListOfToons(); // name, faction, id
        }catch(SQLException sqle){
        	throw sqle;
        }
		
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorColumns+" columns"); 
        arrayToonNames = new String[numCursorRows]; // create arrays of proper size.
        arrayToonIDs = new int [numCursorRows]; // create arrays of proper size.
        
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	arrayToonNames[i] = new String();
	    	arrayToonNames[i]=c.getString(0); 
	    	arrayToonIDs[i]=c.getInt(2); 
	    	c.moveToNext();
	    }
	    c.close();
                    
	}


	private void populateYieldsArray() {
				
		int numCursorRows;
		int numCursorColumns;
		       		
		Cursor c;
        try {
            c = myDbHelper.findCrewSkillYields();
        }catch(SQLException sqle){
        	throw sqle;
        }
		
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorColumns+" columns"); 
        arrayYields = new String[numCursorRows]; // create arrays of proper size.
        arrayYieldIDs = new int [numCursorRows]; // create arrays of proper size.
        
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	arrayYieldIDs[i]=c.getInt(0); 
	    	arrayYields[i] = new String();
	    	arrayYields[i]=c.getString(1); 
	    	c.moveToNext();
	    }
	    c.close();
                    
	}


	@Override
	public void onClick(View v) {
        switch(v.getId()) {
        case R.id.buttonOK:   
        	addNewYieldNeed();
        	this.finish();
    		break;
        case R.id.buttonCancel:
        	this.finish();
    		break;
        }
	}



	private void addNewYieldNeed() {


		name = editTextName.getText().toString();

		
		
		Log.d(TAG, "toonName is "+toonName); 
		Log.d(TAG, "yield is "+yield); 
		Log.d(TAG, "grade is "+grade); 
		Log.d(TAG, "count is "+count); 
		Log.d(TAG, "name is "+name); 
		

//    	Toast.makeText(getBaseContext(), faction, Toast.LENGTH_SHORT).show();
		
		
		
        // add to database
        Cursor c;
        try {
            c = myDbHelper.addCrewSkillNeed(toonName,yield,grade,count,name);
        }catch(SQLException sqle){
        	throw sqle;
        }
    	

	}

}
