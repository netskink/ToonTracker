package net.skink.swtor.toontracker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainScreen extends MyMenuActivity implements OnClickListener {

	Button buttonTodo;
	Button buttonNeedsMats;
	Button buttonCrewskillSummary;
	Button buttonSearch;
	Button buttonBrowse;
	Button buttonArmor;
	Button buttonWeapons;
	Button buttonGifts;
	Button buttonNewToon;
	Button buttonListToons;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main_screen);

    	buttonNeedsMats = (Button) findViewById(R.id.buttonNeedsMats);
        buttonNeedsMats.setOnClickListener(this);    	    	
    	buttonTodo = (Button) findViewById(R.id.buttonTodo);
        buttonTodo.setOnClickListener(this);    	
    	buttonCrewskillSummary = (Button) findViewById(R.id.buttonCrewskillSummary);
        buttonCrewskillSummary.setOnClickListener(this);    	
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);
        buttonBrowse = (Button) findViewById(R.id.buttonBrowse);
        buttonBrowse.setOnClickListener(this);
        buttonArmor = (Button) findViewById(R.id.buttonArmor);
        buttonArmor.setOnClickListener(this);
        buttonWeapons = (Button) findViewById(R.id.buttonWeapons);
        buttonWeapons.setOnClickListener(this);
        buttonGifts = (Button) findViewById(R.id.buttonGifts);
        buttonGifts.setOnClickListener(this);
        buttonNewToon = (Button) findViewById(R.id.buttonNewToon);
        buttonNewToon.setOnClickListener(this);
        buttonListToons = (Button) findViewById(R.id.buttonListToons);
        buttonListToons.setOnClickListener(this);

        
        
        buttonArmor.setClickable(false);
        buttonArmor.setEnabled(false);
        buttonWeapons.setClickable(false);
        buttonWeapons.setEnabled(false);
        buttonGifts.setClickable(false);
        buttonGifts.setEnabled(false);     
        

    }

    @Override
    protected void onResume() {
    	super.onResume();
        buttonArmor.setClickable(false);
        buttonArmor.setEnabled(false);
        buttonWeapons.setClickable(false);
        buttonWeapons.setEnabled(false);
        buttonGifts.setClickable(false);
        buttonGifts.setEnabled(false);                
    }
    
    
	@Override
	public void onClick(View v) {
	  	Intent intent;
	  	
       switch(v.getId()) {
       	case R.id.buttonBrowse:
       		
    	    intent = new Intent(this,BrowseCompanionActivity.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
//    	    intent.putExtra("toonClass", toonClass);
    	    startActivity(intent);
       		break;
       	case R.id.buttonSearch:
            buttonArmor.setClickable(true);
            buttonArmor.setEnabled(true);
            buttonWeapons.setClickable(true);
            buttonWeapons.setEnabled(true);
            buttonGifts.setClickable(true);
            buttonGifts.setEnabled(true);
       		break;
       	case R.id.buttonGifts:
    	    intent = new Intent(this,SearchGifts.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       	case R.id.buttonArmor:
    	    intent = new Intent(this,SearchArmor.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       	case R.id.buttonNewToon:
    	    intent = new Intent(this,NewToonActivity.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       	case R.id.buttonListToons:
    	    intent = new Intent(this,BrowseToonsActivity.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       	case R.id.buttonTodo:
    	    intent = new Intent(this,TodoListActivity.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       	case R.id.buttonCrewskillSummary:
    	    intent = new Intent(this,CrewskillSummaryActivity.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       	case R.id.buttonNeedsMats:
    	    intent = new Intent(this,NeedsMatsActivity.class);
    	    intent.putExtra("screenWidth",screenWidth);
    	    intent.putExtra("screenHeight",screenHeight);	                
    	    startActivity(intent);
       		break;
       }		
	}
}
