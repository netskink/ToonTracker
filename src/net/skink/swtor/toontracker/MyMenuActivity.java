package net.skink.swtor.toontracker;

import java.io.IOException;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ListView;
import android.app.Activity;

public class MyMenuActivity extends Activity {
	// menu stuff below here
	DatabaseHelper myDbHelper = null;
	
    private static String TAG = "MyMenuActivity";

    int screenWidth;
    int screenHeight;	                

	
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
	protected void onCreate(Bundle savedInstanceState) {
		Bundle theBundle;
		theBundle = getIntent().getExtras();

		screenWidth=theBundle.getInt("screenWidth");
		screenHeight=theBundle.getInt("screenHeight");

		
		super.onCreate(savedInstanceState);

        initDatabase();

	    // Get the stuff from the bundle
//		theBundle = getIntent().getExtras();


	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.my_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  	Intent intent;
	   	switch (item.getItemId()) {
	   	case R.id.menuItemAbout:
	       	intent = new Intent(this,AboutActivity.class);
			startActivity(intent);
	   		return true;
	   	case R.id.menuItemHelp:
	       	intent = new Intent(this,HelpActivity.class);
			startActivity(intent);
	   		return true;
	   	case R.id.menuItemEraseDatabase:
	   		myDbHelper.close();
	   		try {
				myDbHelper.eraseDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return true;
	   	
	   	case R.id.menuItemRestoreDatabase:
	   		myDbHelper.close();
			myDbHelper.restoreDataBase();
	   		return true;
	   	case R.id.menuItemBackupDatabase:
	   		myDbHelper.close();
			try {
				myDbHelper.backupDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		return true;
	   	default:
	   		return super.onOptionsItemSelected(item);
	   	}
	
	}

}
