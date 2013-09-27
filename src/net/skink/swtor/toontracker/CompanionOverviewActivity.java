package net.skink.swtor.toontracker;



import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;


public class CompanionOverviewActivity extends TabActivity {

	Bundle theBundle;
	String companionName;
	int companionID;
	TextView textViewName;
	TextView textViewPartnerClass;
	String companionFaction;
	String toonClass;

	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.companion_overview);
	    
	    // the name
		theBundle = getIntent().getExtras();
		companionName = theBundle.getString("name");
		companionFaction = theBundle.getString("faction");
		toonClass = theBundle.getString("toonClass");
		companionID = theBundle.getInt("id");
		// todo: add the toon class here
		
	    textViewName = (TextView) findViewById(R.id.textViewName);	  
	    textViewName.setText(companionName);
	    if (companionFaction.equals("Imperial")) {
	    	textViewName.setTextColor(MyColors.ImperialRedBright);	  // red  	
	    } else {
	    	textViewName.setTextColor(MyColors.RepublicBlue);	    	
	    }
	    textViewPartnerClass = (TextView) findViewById(R.id.textViewPartnerClass);	  
	    textViewPartnerClass.setText(toonClass);
	    	    
	    // tab stuff ===========================================
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, CompanionGeneral.class);
//	    intent.putExtra("name",companionName);
	    intent.putExtra("id",companionID);


	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("general").setIndicator("General",res.getDrawable(R.drawable.ic_tab_general)).setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, CompanionGifts.class);
//	    intent.putExtra("name",companionName);
	    intent.putExtra("id",companionID);
	    spec = tabHost.newTabSpec("gifts").setIndicator("Gifts",res.getDrawable(R.drawable.ic_tab_gifts)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, CompanionSkills.class);
//	    intent.putExtra("name",companionName);
	    intent.putExtra("id",companionID);
	    spec = tabHost.newTabSpec("skills").setIndicator("Skills",res.getDrawable(R.drawable.ic_tab_skills)).setContent(intent);
	    tabHost.addTab(spec);

	    
	    // set the first tab as current
	    tabHost.setCurrentTab(1);
	    
	
	
	}

}
