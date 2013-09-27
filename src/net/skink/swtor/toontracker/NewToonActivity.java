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

public class NewToonActivity extends Activity implements OnClickListener {
	
	// UI components
	Button buttonOK;
	Button buttonCancel;
	EditText editTextName;
	Spinner spinnerFaction;
	Spinner spinnerClass;
	Spinner spinnerCrewSkill1;
	Spinner spinnerCrewSkill2;
	Spinner spinnerCrewSkill3;
	
	// arrays for the spinners
	/* if you want to use a hard coded array, use this */
	//String[] arrayAdvClassSpecialNames = {"assassin","Commando","Juggernaut","Marauder","Mercenary","Operative","Powertech","Sage",	"Shadow","Sniper","Sorcerer","Vanguard"};
	String[] arrayAdvClassSpecialNames;
	int[] arrayAdvClassSpecialID;
	String[] arrayCrewSkillNames;
	int[] arrayCrewSkillID;

	// data from the UI
	String toonName;
	int factionID;
	int advClassID;
	int crewSkillID1;
	int crewSkillID2;
	int crewSkillID3;

	
    private static String TAG = "NewToonActivity";	
	
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
    	setContentView(R.layout.new_toon);
    	
        buttonOK = (Button) findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(this);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextToonName);

        spinnerFaction = (Spinner) findViewById(R.id.spinnerFaction);
        spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        spinnerCrewSkill1 = (Spinner) findViewById(R.id.spinnerCrewSkill1);
        spinnerCrewSkill2 = (Spinner) findViewById(R.id.spinnerCrewSkill2);
        spinnerCrewSkill3 = (Spinner) findViewById(R.id.spinnerCrewSkill3);

        // Init the database
        initDatabase();

        // load the arrays based upon the database
        populateAdvClassSpecialArray();
        populateCrewSkillArray();
                
        ArrayAdapter<CharSequence> factionAdapter = ArrayAdapter.createFromResource(
                this, R.array.faction_array, android.R.layout.simple_spinner_item);
        factionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaction.setAdapter(factionAdapter);    	
        spinnerFaction.setOnItemSelectedListener(new MyOnItemSelectedListener(this));

        
//        MyGiftAdapter<GiftString> adapter = new MyGiftAdapter<GiftString>(this,android.R.layout.simple_list_item_1, stringGifts);
              
// This is the way to use a string array.        
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayAdvClassSpecialNames);
// This is the way to use a resource in res/values/strings.xml
//        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(classAdapter);    	
        spinnerClass.setOnItemSelectedListener(new MyOnItemSelectedListener(this));

        ArrayAdapter<String> crewSkill1Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayCrewSkillNames);
        crewSkill1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCrewSkill1.setAdapter(crewSkill1Adapter);    	
        spinnerCrewSkill1.setOnItemSelectedListener(new MyOnItemSelectedListener(this));


        ArrayAdapter<String> crewSkill2Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayCrewSkillNames);
        crewSkill2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCrewSkill2.setAdapter(crewSkill2Adapter);    	
        spinnerCrewSkill2.setOnItemSelectedListener(new MyOnItemSelectedListener(this));
        

        ArrayAdapter<String> crewSkill3Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayCrewSkillNames);
        crewSkill3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCrewSkill3.setAdapter(crewSkill3Adapter);    	
        spinnerCrewSkill3.setOnItemSelectedListener(new MyOnItemSelectedListener(this));
        
        
	}



	private void populateAdvClassSpecialArray() {
				
		int numCursorRows;
		int numCursorColumns;
		       		
		Cursor c;
        try {
            c = myDbHelper.getListOfAdvClassSpecialNames();
        }catch(SQLException sqle){
        	throw sqle;
        }
		
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorColumns+" columns"); 
        arrayAdvClassSpecialNames = new String[numCursorRows]; // create arrays of proper size.
        arrayAdvClassSpecialID = new int [numCursorRows]; // create arrays of proper size.
        
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	arrayAdvClassSpecialID[i]=c.getInt(0); 
	    	arrayAdvClassSpecialNames[i] = new String();
	    	arrayAdvClassSpecialNames[i]=c.getString(1); 
	    	c.moveToNext();
	    }
	    c.close();
                    
	}


	private void populateCrewSkillArray() {
				
		int numCursorRows;
		int numCursorColumns;
		       		
		Cursor c;
        try {
            c = myDbHelper.getListOfCrewSkillNames();
        }catch(SQLException sqle){
        	throw sqle;
        }
		
        numCursorRows = c.getCount();
        numCursorColumns = c.getColumnCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+numCursorColumns+" columns"); 
        arrayCrewSkillNames = new String[numCursorRows]; // create arrays of proper size.
        arrayCrewSkillID = new int [numCursorRows]; // create arrays of proper size.
        
	    c.moveToFirst();
	    for (int i=0;i<numCursorRows;i++) {
	    	arrayCrewSkillID[i]=c.getInt(0); 
	    	arrayCrewSkillNames[i] = new String();
	    	arrayCrewSkillNames[i]=c.getString(1); 
	    	c.moveToNext();
	    }
	    c.close();
                    
	}


	@Override
	public void onClick(View v) {
        switch(v.getId()) {
        case R.id.buttonOK:   
        	addNewToon();
        	this.finish();
    		break;
        case R.id.buttonCancel:
        	this.finish();
    		break;
        }
	}



	private void addNewToon() {

		

		toonName = editTextName.getText().toString();
		Log.d(TAG, "toonName is "+toonName); 
		Log.d(TAG, "advClassID is "+advClassID); 
		Log.d(TAG, "factionID is "+factionID); 
		Log.d(TAG, "crewSkillID1 is "+crewSkillID1); 
		Log.d(TAG, "crewSkillID2 is "+crewSkillID2); 
		Log.d(TAG, "crewSkillID3 is "+crewSkillID3); 
		

//    	Toast.makeText(getBaseContext(), faction, Toast.LENGTH_SHORT).show();
		
		
		
        // add to database
        Cursor c;
        try {
            c = myDbHelper.addToon(toonName,factionID,advClassID,crewSkillID1,crewSkillID2,crewSkillID3);
        }catch(SQLException sqle){
        	throw sqle;
        }
    	
		
	}

}
