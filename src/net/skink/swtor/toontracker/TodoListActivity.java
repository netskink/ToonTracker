package net.skink.swtor.toontracker;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListActivity extends MyMenuActivity {
	
    // UI Components
    ListView theList;
    TextView textViewTasks;
    MyColorAdapter<ColorString> adapter;
    
    // Data
    int todoID;
    
	
    private static String TAG = "TodoListActivity";
    ColorString[] newValues; // will store an array.

    
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.todo_list);
        
        // Get the UI Components
        theList = (ListView) findViewById(R.id.listView1);
        textViewTasks = (TextView) findViewById(R.id.textViewTasks);
        

        initDatabase();

        // set the ingredient list to all.
	    setListViewContents();

	}

    public void setListViewContents() {

    	int numCursorRows;
    	
        // Determine the ingredients to put in the list.
        Cursor c;
        try {
            c = myDbHelper.getListOfTodos(); // toonName,isDone,todoName,_todoID
        }catch(SQLException sqle){
        	throw sqle;
        }
        numCursorRows = c.getCount();
//		Log.d(TAG, "c has "+numCursorRows+" rows and "+c.getColumnCount()+" columns"); 
	    newValues = new ColorString[numCursorRows]; // create an array of proper size.
	    c.moveToFirst();
        textViewTasks.setText(c.getString(2)+" green:= complete");
	    for (int i=0;i<numCursorRows;i++) {
	    	// todo: look into this html style later.
	    	//myTextView.setText(Html.fromHtml("<p style="color:red">" + someText + "</p>"));
//	    	values[i]=Html.fromHtml("<p style="color:red">" + "foo" + "</p>");
	    	newValues[i] = new ColorString();
	    	newValues[i].name=c.getString(0); // todo name
	    	newValues[i].currentValue=c.getInt(1); // todo name
	    	if (1 == c.getInt(1)) { // todo is done flag
		    	newValues[i].color="Green";	    		
	    	} else {
		    	newValues[i].color="Red";
	    		
	    	}
	    	c.moveToNext();
	    }
	    c.close();

    	    	
    	// Build the listview
	    // The adapter determines how to draw on screen.  The listview will ask the adapter to draw the view
	    // with adapter's getView method.
	    adapter = new MyColorAdapter<ColorString>(this,android.R.layout.simple_list_item_1, newValues);

		theList.setAdapter(adapter);
        theList.setOnItemClickListener(new MyOnItemClickListener(this));

    }


	public void selectToDo(String toonName, int listPosition) {
		
		int numCursorRows;
		int toonID;
		int newValue;
		
		if (null == myDbHelper)
			initDatabase();
		
		
		// Determine the ID for this name
        Cursor c;
        try {
            c = myDbHelper.findToon(toonName);  // this returns 1 or more rows.
            											  // if no name is speicifed it returns all rows
            											  // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
	    c.moveToFirst();
	    toonID=c.getInt(0);
	    c.close();
		
	    newValue = newValues[listPosition].currentValue%1;
	    if (1==newValues[listPosition].currentValue) {
	    	newValue=0;
	    	newValues[listPosition].currentValue=0;
	    	newValues[listPosition].color="Red";	 
	    } else {
	    	newValue=1;	    	
	    	newValues[listPosition].currentValue=1;
	    	newValues[listPosition].color="Green";	 
	    }
	    
	    // Now for this ID, toggle the todo list item for this toon.
        try {
            myDbHelper.toggleToonsTodo(toonID,todoID,newValue);  // this returns 1 or more rows.
            									   // if no name is speicifed it returns all rows
            									   // for each row it returns, faction and picutre
        }catch(SQLException sqle){
        	throw sqle;
        }
        // no need to close this query because it does not return anything.
        
        adapter.notifyDataSetChanged();
	}
	
	
	
	
}
