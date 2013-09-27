package net.skink.swtor.toontracker;



import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;


public class ToonOverviewActivity extends TabActivity {

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
	TextView textViewToonName;
	TextView textViewClass;
	

	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.toon_overview);
	    // Get the UI components
	    textViewToonName = (TextView) findViewById(R.id.textViewToonName);	  
	    textViewClass = (TextView) findViewById(R.id.textViewClass);	  
	    
	    
	    
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
		
		
		
		// Using the bundle, set the UI components.		
		textViewToonName.setText(toonName);
	    if (toonFactionName.equals("Imperial")) {
	    	textViewToonName.setTextColor(MyColors.ImperialRedBright);	  // red  	
	    } else {
	    	textViewToonName.setTextColor(MyColors.RepublicBlue);	    	
	    }
	    textViewClass.setText(toonClassName);
	    	    
	    // tab stuff ===========================================
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    
///////////// TAB ONE ///////////////////////////////////////	    
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, ToonGeneral.class);
	    intent.putExtra("toonName",toonName);
	    intent.putExtra("toonFactionName",toonFactionName);
		intent.putExtra("toonID",toonID);
	    
	    intent.putExtra("toonClassID", toonClassID);
	    intent.putExtra("toonAdvClassID", toonAdvClassID);
	    intent.putExtra("toonAdvClassSpecialID", toonAdvClassSpecialID);
	    
	    intent.putExtra("toonClassName", toonClassName);
	    intent.putExtra("toonAdvClassName", toonAdvClassName);
	    intent.putExtra("toonAdvClassSpecialName", toonAdvClassSpecialName);


	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("general").setIndicator("General",res.getDrawable(R.drawable.ic_tab_general)).setContent(intent);
	    tabHost.addTab(spec);

///////////// TAB TWO ///////////////////////////////////////	    
	    
	    
	    intent = new Intent().setClass(this, ToonSkillsTabActivity.class);
	    intent.putExtra("toonName",toonName);
	    intent.putExtra("toonFactionName",toonFactionName);
		intent.putExtra("toonID",toonID);
	    
	    intent.putExtra("toonClassID", toonClassID);
	    intent.putExtra("toonAdvClassID", toonAdvClassID);
	    intent.putExtra("toonAdvClassSpecialID", toonAdvClassSpecialID);
	    
	    intent.putExtra("toonClassName", toonClassName);
	    intent.putExtra("toonAdvClassName", toonAdvClassName);
	    intent.putExtra("toonAdvClassSpecialName", toonAdvClassSpecialName);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("skills").setIndicator("Skills",res.getDrawable(R.drawable.ic_tab_skills)).setContent(intent);
	    tabHost.addTab(spec);
	    

///////////// TAB THREE ///////////////////////////////////////	    
	    
	    
	    intent = new Intent().setClass(this, ToonGearActivity.class);
	    intent.putExtra("toonName",toonName);
	    intent.putExtra("toonFactionName",toonFactionName);
		intent.putExtra("toonID",toonID);
	    
	    intent.putExtra("toonClassID", toonClassID);
	    intent.putExtra("toonAdvClassID", toonAdvClassID);
	    intent.putExtra("toonAdvClassSpecialID", toonAdvClassSpecialID);
	    
	    intent.putExtra("toonClassName", toonClassName);
	    intent.putExtra("toonAdvClassName", toonAdvClassName);
	    intent.putExtra("toonAdvClassSpecialName", toonAdvClassSpecialName);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("gear").setIndicator("Gear",res.getDrawable(R.drawable.ic_tab_gear)).setContent(intent);
	    tabHost.addTab(spec);
	    

	    

	    
	    
	    // set the first tab as current
	    tabHost.setCurrentTab(0);
	
	
	}

}
