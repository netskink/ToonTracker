package net.skink.swtor.toontracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/net.skink.swtor.toontracker/databases/";
    private static String DB_PATH2 = "/mnt/sdcard/";
 
    private static String DB_NAME = "swtorlab.db";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
	
    private static String TAG = "DatabaseHelper";
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DatabaseHelper(Context context) {
 
    	super(context, DB_NAME, null, 2); // TODO: this  1 is a version number, look at it
        this.myContext = context;
    }	
 

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * 
     **/
    public void createDataBase() throws IOException{
         	
      	boolean dbExist = checkDataBase();
   
      	if(dbExist){
      		//do nothing - database already exist
      	}else{
   
      		// By calling this method an empty database will be created into the default system path
      		// of your application so we are gonna be able to overwrite that database with our database.
      		// JFD getReadableDatabase() was fine for doing queries.
//      		this.getReadableDatabase();
      		this.getWritableDatabase();	// replaced with writable which is read/write.  Supposedly we should
      									// do this in a thread and not a UI thread.
      		//this.close(); // add this here
   
          	try {
   
      			copyDataBase();
   
      		} catch (IOException e) {
   
          		throw new Error("Error copying database");
   
          	}
      	}
   }
   
    public void eraseDataBase() throws IOException{
 
    	// Path to the just created empty db
    	String fileName = DB_PATH + DB_NAME;
    	
 
        // A File object to represent the filename
        File f = new File(fileName);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists())
          throw new IllegalArgumentException(
              "Delete: no such file or directory: " + fileName);

        if (!f.canWrite())
          throw new IllegalArgumentException("Delete: write protected: "
              + fileName);

        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
          String[] files = f.list();
          if (files.length > 0)
            throw new IllegalArgumentException(
                "Delete: directory not empty: " + fileName);
        }

        // Attempt to delete it
        boolean success = f.delete();

        if (!success)
          throw new IllegalArgumentException("Delete: deletion failed");    	
    }
    
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null) {
    		    myDataBase.close();
    	    }
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade hit");
 
	}
	
	// Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
	
	
	
	 
	public Cursor listCompanions() {

	   String [] columns=new String[]{"companionName","factionName"};
	   Cursor c = null;
	   c=myDataBase.query("Companion_type,Faction", columns, "Companion_type._factionID=Faction._factionID", null, null, null, null);		   		   
	   return c;
		 
	}

	public Cursor getListOfToons() {
	   String [] columns=new String[]{"toonName,factionName,_toonID"};
	   Cursor c = null;
	   c=myDataBase.query("Toon,Faction", columns, "Toon._factionID=Faction._factionID" /* where */, null, null, null, "toonName");		   
	   return c;
	}
	
	 
	public Cursor findToon(String theName) {

	   String [] columns=new String[]{"_toonID", "factionName"};
	   Cursor c = null;
	   c=myDataBase.query("Toon,Faction", columns, "toonName=? and Toon._factionID=Faction._factionID", new String[]{theName}, null, null, null);		   		   
	   return c;
		 
	}
	 
	public Cursor findCompanionID(String theName) {

	   String [] columns=new String[]{"_companionTypeID", "factionName","pic"};
	   Cursor c = null;
	   // query(table,projectioncolumns,selection,selectionargs,groupby,having, orderby)
	   c=myDataBase.query("Companion_type,Faction", columns, "companionName=? and Companion_type._factionID=Faction._factionID", new String[]{theName}, null, null, null);		   		   
	   return c;
		 
	}
	public Cursor findToonToonClassByCompanionID(Integer companionID) {

		   Cursor c = null;
		   Integer classID;

		   // return the toon class id and the toon class name
		   String [] columns=new String[]{"Class._classID,Class.className"}; 
		   c=myDataBase.query("Class_companion_map,Class", 
				   columns, 
				   "Class_companion_map._companionTypeID=? and " +
				   "Class_companion_map._classID=Class._classID",
				   new String[]{companionID.toString()}, null, null, null);		   		   	   
		   return c;
			 
		}

	 
	public Cursor findToonClass(Integer toonID) {

	   Cursor c = null;
	   Integer classID;

	   // relational algebra you do not need to specify Table.class name.  It appears in sqlite3 you need to do so:
	   String [] columns=new String[]{"Class._classID,Adv_class._advClassID,Adv_class_special._advClassSpecialID,Class.className,Adv_class.advClassName,Adv_class_special.advClassSpecialName"}; // then we get the likes for this id.
	   c=myDataBase.query("Toon,Adv_class_special,Adv_class,Class", columns, "_toonID=? and Adv_class_special._advClassSpecialID=Toon._advClassSpecialID and Adv_class_special._advClassID=Adv_class._advClassID and Class._classID=Adv_class._classID", new String[]{toonID.toString()}, null, null, null);		   		   	   
	   return c;
		 
	}
	 	
	 
	public Cursor listOpinionsByPreference(Integer companionID, Integer preference) { // 0 is dislike, need an enum

	   Cursor c = null;

	   String [] columns2=new String[]{"opinion"}; // then we get the likes for this id.
	   c=myDataBase.query("Companion_opinion", columns2, "_companionTypeID=? and likes=?", new String[]{companionID.toString(),preference.toString()}, null, null, null);		   		   
	   
	   return c;
		 
	}
	 

	 
	public Cursor listGiftsByPrefs(Integer companionID, Integer preference) { // 1 is prefers, 2 is likes, 3 is acceptable
	   Cursor c = null;

	   String [] columns=new String[]{"gift_name"}; // then we get the likes for this id.
	   c=myDataBase.query("Companion_gift_prefs,Gifts", columns, "_companionTypeID=? and preference=? and Companion_gift_prefs._giftID=Gifts._giftID", new String[]{companionID.toString(),preference.toString()}, null, null, null);		   		   
	   
	   return c;
		 
	}
	
	 

	public Cursor listCrewSkill(int companionID) {

		   Integer id = companionID;
		   Cursor c = null;

		   String [] columns2=new String[]{"major","minor"}; // then we get the likes for this id.
		   c=myDataBase.query("Companion_crewskill_bonus", columns2, "_companionTypeID=?", new String[]{id.toString()}, null, null, null);		   		   
		   
		   return c;
			 
		}
	

	public Cursor listStats(int companionID) {

		   Integer id = companionID;
		   Cursor c = null;

		   String [] columns2=new String[]{"primarystat","armor","weapon"}; // then we get the likes for this id.
		   c=myDataBase.query("Companion_stats", columns2, "_companionTypeID=?", new String[]{id.toString()}, null, null, null);		   		   
		   
		   return c;
			 
		}
	

	public Cursor listNotes(int companionID) {

		   Integer id = companionID;
		   Cursor c = null;

		   String [] columns2=new String[]{"note1","note2","note3"}; // then we get the likes for this id.
		   c=myDataBase.query("Companion_notes", columns2, "_companionTypeID=?", new String[]{id.toString()}, null, null, null);		   		   
		   
		   return c;
			 
		}
	
	public Cursor listGifts() {
		   Cursor c = null;

		   String [] columns=new String[]{"gift_name"}; // project the gifts name for all gifts.
		   c=myDataBase.query("Gifts", columns, null, null, null, null, null);		   		   
		   
		   return c;
	}
	
	public Cursor searchGifts(String gift,Integer priority) {
		   
		   Cursor c = null;
		   String [] columns=new String[]{"Companion_type.companionName"}; // then we get the likes for this id.

			   c=myDataBase.query("Companion_gift_prefs,Gifts,Companion_type", columns, 
					   "Gifts.gift_name=? and preference=? and Companion_gift_prefs._companionTypeID=Companion_type._companionTypeID and Companion_gift_prefs._giftID=Gifts._giftID", 
					   new String[]{gift,priority.toString()}, null, null, null);		   		   
		   return c;
			 
	}
	

	public Cursor searchArmors(String armor) {
		// TODO Auto-generated method stub
		return null;
	}



	public Cursor getListOfAdvClassSpecialNames() {
		Cursor c = null;
		String [] columns=new String[]{"_advClassSpecialID","advClassSpecialName"}; 

		c=myDataBase.query("Adv_class_special", columns,null,null, null, null,"advClassSpecialName" );		   		   
		return c;
	}

	public Cursor findAdvClassSpecialRoleAndStats(Integer toonAdvSpecialClassID) {

	   Cursor c = null;
	   Integer classID;

	   // relational algebra you do not need to specify Table.class name.  It appears in sqlite3 you need to do so:
	   String [] columns=new String[]{"Role.roleName,Primary_stat.name,Secondary_stat.name"}; 
	   c=myDataBase.query("Role,Adv_class_special,Primary_stat,Secondary_stat", columns, 
			   "Adv_class_special._advClassSpecialID=? and " +
			   "Adv_class_special.roleID=Role._roleID and " +
			   "Adv_class_special.primaryStatID=Primary_stat._pstatID and " +
			   "Adv_class_special.secondaryStatID=Secondary_stat._sstatID", 
			   new String[]{toonAdvSpecialClassID.toString()}, null, null, null);		   		   	   
	   return c;					
	}


	public Cursor findAdvClassArmor(Integer toonAdvClassID) {
	   Cursor c = null;
	   Integer classID;

	   // relational algebra you do not need to specify Table.class name.  It appears in sqlite3 you need to do so:
	   String [] columns=new String[]{"Armor_type.armorType"}; 
	   c=myDataBase.query("Adv_class,Armor_type", columns, "Adv_class._advClassID=? and Adv_class.armorTypeID=Armor_type._armorTypeID", new String[]{toonAdvClassID.toString()}, null, null, null);		   		   	   
//	   c=myDataBase.query("Adv_class,Armor_type,Weapon_type", columns, "Adv_class._advClassID=? and Adv_class.armor_type=Armor_type._armorTypeID as w1 and Adv_class.weaponID1=Weapon_type._weaponTypeID as w2 and Adv_class.weaponID2=Weapon_type._weaponTypeID and Adv_class.weaponID3=Weapon_type._weaponTypeID", new String[]{toonAdvSpecialClassID.toString()}, null, null, null);		   		   	   
	   return c;					
	}

	public Cursor findAdvClassWeapons(Integer toonAdvClassID) {
		   Cursor c = null;
		   Integer classID;

		   // relational algebra you do not need to specify Table.class name.  It appears in sqlite3 you need to do so:
		   String [] columns=new String[]{"Weapon_type.weaponType,Weapon_type.weaponType,Weapon_type.weaponType"}; 
		   c=myDataBase.query("Adv_class,Weapon_type", columns, 
				   "Adv_class._advClassID=? and (" +
				   "Adv_class.weaponID1=Weapon_type._weaponTypeID or " + 
				   "Adv_class.weaponID2=Weapon_type._weaponTypeID or " +
				   "Adv_class.weaponID3=Weapon_type._weaponTypeID)", 
				   new String[]{toonAdvClassID.toString()}, null, null, null);		   		   	   
//		   c=myDataBase.query("Adv_class,Armor_type,Weapon_type", columns, "Adv_class._advClassID=? and Adv_class.armor_type=Armor_type._armorTypeID as w1 and Adv_class.weaponID1=Weapon_type._weaponTypeID as w2 and Adv_class.weaponID2=Weapon_type._weaponTypeID and Adv_class.weaponID3=Weapon_type._weaponTypeID", new String[]{toonAdvSpecialClassID.toString()}, null, null, null);		   		   	   
		   return c;					
		}



	public Cursor findCrewSkillNames(Integer toonID,int crewskillNum) {
		Cursor c = null;
		String [] columns=new String[]{"Crew_skill.crewSkillName,Crew_skill._crewSkillID"}; 
		
		if (1 == crewskillNum) {
			c=myDataBase.query("Toon,Crew_skill", columns,
					"Toon._toonID=? and " +
					"Toon.crewSkill1=Crew_skill._crewSkillID",
					new String[]{toonID.toString()},null, null, null);		   		   
		} else if (2 == crewskillNum) {
			c=myDataBase.query("Toon,Crew_skill", columns,
					"Toon._toonID=? and " +
					"Toon.crewSkill2=Crew_skill._crewSkillID",
					new String[]{toonID.toString()},null, null, null);		   		   
		} else if (3 == crewskillNum) {
			c=myDataBase.query("Toon,Crew_skill", columns,
					"Toon._toonID=? and " +
					"Toon.crewSkill3=Crew_skill._crewSkillID",
					new String[]{toonID.toString()},null, null, null);		   		   

		}
		
		return c;
	}

	// version 1 for a particular toon
	public Cursor findCrewSkillYields(Integer toonID,int crewskillNum) { // 1 is crewksill1, 2 is crewskill 2, ...
		Cursor c = null;
		String [] columns=new String[]{"Crew_skill.crewSkillName,Crew_skill_yields.crewSkillYieldsName"}; 

		
		if (1 == crewskillNum) {
			c=myDataBase.query("Toon,Crew_skill,Crew_skill_yields", columns,
					"Toon._toonID=? and " +
					"Toon.crewSkill1=Crew_skill._crewSkillID and " +
					"Crew_skill_yields.crewSkillID=Toon.crewSkill1",
					new String[]{toonID.toString()},null, null, null);	
			
		} else if (2 == crewskillNum) {
			c=myDataBase.query("Toon,Crew_skill,Crew_skill_yields", columns,
					"Toon._toonID=? and " +
					"Toon.crewSkill2=Crew_skill._crewSkillID and " +
					"Crew_skill_yields.crewSkillID=Toon.crewSkill2",
					new String[]{toonID.toString()},null, null, null);	
			
		} else if (3 == crewskillNum) {
			c=myDataBase.query("Toon,Crew_skill,Crew_skill_yields", columns,
					"Toon._toonID=? and " +
					"Toon.crewSkill3=Crew_skill._crewSkillID and " +
					"Crew_skill_yields.crewSkillID=Toon.crewSkill3",
					new String[]{toonID.toString()},null, null, null);	
			
		}
		
		
		
		return c;
	}
	
	// version 2 for all yields
	public Cursor findCrewSkillYields() { 
		Cursor c = null;
		String [] columns=new String[]{"Crew_skill_yields._crewSkillYieldsID,Crew_skill_yields.crewSkillYieldsName"}; 

		
			c=myDataBase.query("Crew_skill_yields", columns,null,null,null, null, "crewSkillYieldsName");	
			
						
		return c;
	}
	
	public Cursor getListOfCrewSkillNames() {
		Cursor c = null;
		String [] columns=new String[]{"_crewSkillID","crewSkillName"}; 

		c=myDataBase.query("Crew_skill", columns,null,null, null, null,"crewSkillName" );		   		   
		return c;
	}

	public Cursor findCrewSkillLevels(Integer toonID, int crewskillNum) {
		Cursor c = null;
		String [] columns=new String[]{"Toon_crewskill_levels.level,Toon_crewskill_levels._crewSkillID"}; 

//		create table Toon_crewskill_levels(_toonID integer primary key, _crewSkillID integer, level integer);

		
		if (1 == crewskillNum) {
			c=myDataBase.query("Toon,Toon_crewskill_levels", columns,
					"Toon._toonID=? and " +
					"Toon._toonID=Toon_crewskill_levels._toonID and " +
					"Toon.crewSkill1=Toon_crewskill_levels._crewSkillID",
					new String[]{toonID.toString()},null, null, null);	
			
		} else if (2 == crewskillNum) {
			c=myDataBase.query("Toon,Toon_crewskill_levels", columns,
					"Toon._toonID=? and " +
					"Toon._toonID=Toon_crewskill_levels._toonID and " +
					"Toon.crewSkill2=Toon_crewskill_levels._crewSkillID",
					new String[]{toonID.toString()},null, null, null);	
			
		} else if (3 == crewskillNum) {
			c=myDataBase.query("Toon,Toon_crewskill_levels", columns,
					"Toon._toonID=? and " +
					"Toon._toonID=Toon_crewskill_levels._toonID and " +
					"Toon.crewSkill3=Toon_crewskill_levels._crewSkillID",
					new String[]{toonID.toString()},null, null, null);	
			
		}
		return c;
	}
	
	public Cursor setCrewSkillLevels(Integer toonID, Integer skillID, Integer skillLevel) {
		ContentValues cv=new ContentValues();
		cv.put("level", skillLevel);
		
		myDataBase.update("Toon_crewskill_levels",cv, "_toonID=? and _crewSkillID=?", new String[]{toonID.toString(),skillID.toString()});
		return null;
	}
	

	public void deleteCrewSkillNeedsRow(String toon, String yield, String grade, String count, String name) {
		String [] columns=new String[]{"toon","yield","grade","count","name"}; 
		myDataBase.delete("Crew_skill_needs",
				"toon=? and yield=? and grade=? and count=? and name=?", 
				new String [] {toon,yield,grade,count,name});
		
	}



	public Cursor addCrewSkillNeed(String toon, String yield, String grade, String count, String name) {
		ContentValues cv=new ContentValues();
		// since the toonID is primary key it will autoinsert if the field is null when an insert is performed.
		cv.put("toon", toon);
		cv.put("yield", yield);
		cv.put("grade", grade);		
		cv.put("count", count);
		cv.put("name", name);
		
		myDataBase.insert("Crew_skill_needs", "name", cv);
		return null;
	}

	
	
	public Cursor getListOfCrewSkillNeeds() {
		Cursor c = null;
		String [] columns=new String[]{"toon","yield","grade","count","name"}; 

		c=myDataBase.query("Crew_skill_needs", columns,null,null, null, null,"toon" );		   		   
		return c;
	}
	
	
	
	public Cursor addToon(String toonName, Integer factionID, Integer advClassID, Integer crewSkillID1, Integer crewSkillID2, Integer crewSkillID3) {
		int theToonsID; // this is autogenerated.  After we insert it, we need to query it to update the crewskill levels table;
		ContentValues cv=new ContentValues();
		// since the toonID is primary key it will autoinsert if the field is null when an insert is performed.
		cv.put("toonname", toonName);
		cv.put("_factionID", factionID);
		cv.put("_advClassSpecialID", advClassID);		
		cv.put("crewSkill1", crewSkillID1);
		cv.put("crewSkill2", crewSkillID2);
		cv.put("crewSkill3", crewSkillID3);
		
		myDataBase.insert("Toon", "_toonID", cv);
		
		String [] columns=new String[]{"_toonID"};
		Cursor c = null;
		c=myDataBase.query("Toon", columns, "toonName=?", new String[]{toonName}, null, null, null);	
	    c.moveToFirst();
	    theToonsID = c.getInt(0);	
		c.close();
		
		cv=new ContentValues();
		cv.put("_toonID", theToonsID);
		cv.put("_crewSkillID", crewSkillID1);
		cv.put("level", 0);
		myDataBase.insert("Toon_crewskill_levels", null, cv);  

		
		cv=new ContentValues();
		cv.put("_toonID", theToonsID);
		cv.put("_crewSkillID", crewSkillID2);
		cv.put("level", 0);
		myDataBase.insert("Toon_crewskill_levels", null, cv);  
		
		cv=new ContentValues();
		cv.put("_toonID", theToonsID);
		cv.put("_crewSkillID", crewSkillID3);
		cv.put("level", 0);
		myDataBase.insert("Toon_crewskill_levels", null, cv);  
		
		
		return null;
	}


	public Cursor getListOfTodos() {
		Cursor c = null;
		String [] columns=new String[]{"Toon.toonName,Todo_list.isDone,Todo_list_items.todoName,Todo_list._todoID"}; 

		c=myDataBase.query("Toon,Todo_list,Todo_list_items", columns,
				"Toon._toonID=Todo_list._toonID and "+
				"Todo_list._todoID=Todo_list_items._todoID",
				null,null, null, null);		   		   
		return c;
	}
	

	public Cursor toggleToonsTodo(int toonID, int todoID, int newValue) {
/*		
		  SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues cv=new ContentValues();
		   cv.put(colName, emp.getName());
		   cv.put(colAge, emp.getAge());
		   cv.put(colDept, emp.getDept());
		   return db.update(employeeTable, cv, colID+"=?", 
		    new String []{String.valueOf(emp.getID())});  
		
		The update method has the following parameters:

		String Table: The table to update a value in
		ContentValues cv: The content values object that has the new values
		String where clause: The WHERE clause to specify which record to update
		String[] args: The arguments of the WHERE clause 
		    
		    
*/
		ContentValues cv=new ContentValues();
		cv.put("isDone", newValue);
		
		myDataBase.update("Todo_list",cv, "_toonID=?", new String[]{String.valueOf(toonID)});
		
		return null;
		
	}
	
	
	// from
	// http://www.codeproject.com/KB/android/AndroidSQLite.aspx
	//	
//		public Cursor getEmpByDept(String Dept) {
//		   SQLiteDatabase db=this.getReadableDatabase();
//		   String [] columns=new String[]{"_id",colName,colAge,colDeptName};
//		   Cursor c=db.query(viewEmps, columns, colDeptName+"=?", 
//			new String[]{Dept}, null, null, null);
//		   return c;
//		  }
	
	
/* ======================== private stuff below ======================================= */
	
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();

    }
 
    
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
    		//database does't exist yet.
			Log.d(TAG, "check database failed. It doesn't exist yet"); 
    	}
 
    	if(checkDB != null){
     		checkDB.close();
    	}
 
    	return checkDB != null ? true : false;
    }


	public Cursor findAdvClassArmorAndWeapons(int toonAdvSpecialClassID) {
		// TODO Auto-generated method stub
		return null;
	}


	private boolean isExternalStorageAvail() {
	      return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public void restoreDataBase() {
		 if (isExternalStorageAvail()) {
             new ImportDatabaseFileTask().execute();
          } else {
             Toast.makeText(myContext, 
            		 "External storage is not available, unable to import data.",Toast.LENGTH_SHORT).show();
          }		
		
	}
	
	public void backupDataBase() throws IOException {
		 if (isExternalStorageAvail()) {
             new ExportDatabaseFileTask().execute();
          } else {
             Toast.makeText(myContext, 
            		 "External storage is not available, unable to export data.",Toast.LENGTH_SHORT).show();
          }		
	}

	
	///////////////////
	// Export
	private class ExportDatabaseFileTask extends AsyncTask<String, Void, Boolean> {
	      private final ProgressDialog dialog = new ProgressDialog(myContext);
	 
	      // can use UI thread here
	      protected void onPreExecute() {
	         this.dialog.setMessage("Exporting database...");
	         this.dialog.show();
	      }
	 
	      // automatically done on worker thread (separate from UI thread)
	      protected Boolean doInBackground(final String... args) {
	 
	         File dbFile =
	                  new File(Environment.getDataDirectory() + "/data/net.skink.swtor.toontracker/databases/"+DB_NAME);
	 
	         File exportDir = new File(Environment.getExternalStorageDirectory(), "toontracker");
	         if (!exportDir.exists()) {
	            exportDir.mkdirs();
	         }
	         File file = new File(exportDir, dbFile.getName());
	 
	         try {
	            file.createNewFile();
	            this.copyFile(dbFile, file);
	            return true;
	         } catch (IOException e) {
	            Log.e(TAG, e.getMessage(), e);
	            return false;
	         }
	      }
	 
	      // can use UI thread here
	      protected void onPostExecute(final Boolean success) {
	         if (this.dialog.isShowing()) {
	            this.dialog.dismiss();
	         }
	         if (success) {
	            Toast.makeText(myContext, "Export successful!", Toast.LENGTH_SHORT).show();
	         } else {
	            Toast.makeText(myContext, "Export failed", Toast.LENGTH_SHORT).show();
	         }
	      }
	 
	      void copyFile(File src, File dst) throws IOException {
	         FileChannel inChannel = new FileInputStream(src).getChannel();
	         FileChannel outChannel = new FileOutputStream(dst).getChannel();
	         try {
	            inChannel.transferTo(0, inChannel.size(), outChannel);
	         } finally {
	            if (inChannel != null)
	               inChannel.close();
	            if (outChannel != null)
	               outChannel.close();
	         }
	      }
	 
	   }


	
	
	//////////
	// Import
	private class ImportDatabaseFileTask extends AsyncTask<String, Void, Boolean> {
	      private final ProgressDialog dialog = new ProgressDialog(myContext);
	 
	      // can use UI thread here
	      protected void onPreExecute() {
	         this.dialog.setMessage("Importing database...");
	         this.dialog.show();
	      }
	 
	      // automatically done on worker thread (separate from UI thread)
	      protected Boolean doInBackground(final String... args) {
	 
	         File dbFile = new File(Environment.getDataDirectory() + "/data/net.skink.swtor.toontracker/databases/"+DB_NAME);
	 
	         File importDir = new File(Environment.getExternalStorageDirectory(), "toontracker");
	         if (!importDir.exists()) {
	            return false;
	         }
	         File file = new File(importDir, dbFile.getName());
	 
	         try {
	            file.createNewFile();
	            this.copyFile(file,dbFile);
	            return true;
	         } catch (IOException e) {
	            Log.e(TAG, e.getMessage(), e);
	            return false;
	         }
	      }
	 
	      // can use UI thread here
	      protected void onPostExecute(final Boolean success) {
	         if (this.dialog.isShowing()) {
	            this.dialog.dismiss();
	         }
	         if (success) {
	            Toast.makeText(myContext, "Import successful!", Toast.LENGTH_SHORT).show();
	         } else {
	            Toast.makeText(myContext, "Import failed", Toast.LENGTH_SHORT).show();
	         }
	      }
	 
	      void copyFile(File src, File dst) throws IOException {
	         FileChannel inChannel = new FileInputStream(src).getChannel();
	         FileChannel outChannel = new FileOutputStream(dst).getChannel();
	         try {
	            inChannel.transferTo(0, inChannel.size(), outChannel);
	         } finally {
	            if (inChannel != null)
	               inChannel.close();
	            if (outChannel != null)
	               outChannel.close();
	         }
	      }
	 
	   }





	
	
	/*
	   private class xExportDataAsXmlTask extends AsyncTask<String, Void, String> {
	      private final ProgressDialog dialog = new ProgressDialog(myContext);
	 
	      // can use UI thread here
	      protected void onPreExecute() {
	         this.dialog.setMessage("Exporting database as XML...");
	         this.dialog.show();
	      }
	 
	      // automatically done on worker thread (separate from UI thread)
	      protected String doInBackground(final String... args) {
	         DataXmlExporter dm = new DataXmlExporter(DatabaseHelper.this.application.getDataHelper().getDb());
	         try {
	            String dbName = args[0];
	            String exportFileName = args[1];
	            dm.export(dbName, exportFileName);
	         } catch (IOException e) {
	            Log.e(TAG, e.getMessage(), e);
	            return e.getMessage();
	         }
	         return null;
	      }
	 
	      // can use UI thread here
	      protected void onPostExecute(final String errMsg) {
	         if (this.dialog.isShowing()) {
	            this.dialog.dismiss();
	         }
	         if (errMsg == null) {
	            Toast.makeText(myContext, "Export successful!", Toast.LENGTH_SHORT).show();
	         } else {
	            Toast.makeText(myContext.this, "Export failed - " + errMsg, Toast.LENGTH_SHORT).show();
	         }
	      }
	   }
	*/

       








 
 
}