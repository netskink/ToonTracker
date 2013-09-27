package net.skink.swtor.toontracker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyOnItemSelectedListener implements OnItemSelectedListener {


	
	
	int[] availfactionID = {1,2}; // 1 is imperial
// These were used when lists were hard coded using either resources or initialized arrays.	
//	int[] availClassID = {207,212,205,206,202,203,201,210,209,204,208,211}; // 1 is imperial
//	int[] availCrewSkillID = {101,105,106,107,102,108,109,111,112,103,104,110,113,114}; // 1 is imperial
	NewToonActivity newToonActivity = null;
	NewNeedsActivity newNeedsActivity = null;
	
    public MyOnItemSelectedListener(NewToonActivity theActivity) {
		newToonActivity = theActivity;
	}

	public MyOnItemSelectedListener(NewNeedsActivity theActivity) {
		newNeedsActivity = theActivity;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    	String textString;
    	int iNumber;
    	
    	switch (parent.getId()){
    	// These are for the new toons activity
    	case R.id.spinnerFaction:
    		textString = "Faction ID is ";
    		iNumber = availfactionID[pos];
    		newToonActivity.factionID = iNumber;
    		break;
    	case R.id.spinnerClass:
    		textString = "Class ID is ";
    		iNumber = newToonActivity.arrayAdvClassSpecialID[pos];
    		newToonActivity.advClassID = iNumber;
    		break;
    	case R.id.spinnerCrewSkill1:
    		textString = "CrewSkill1 ID is ";
    		iNumber = newToonActivity.arrayCrewSkillID[pos];
    		newToonActivity.crewSkillID1 = iNumber;
    		break;
    	case R.id.spinnerCrewSkill2:
    		textString = "CrewSkill2 ID is ";
    		iNumber = newToonActivity.arrayCrewSkillID[pos];
    		newToonActivity.crewSkillID2 = iNumber;
    		break;
    	case R.id.spinnerCrewSkill3:
    		textString = "CrewSkill3 ID is ";
    		iNumber = newToonActivity.arrayCrewSkillID[pos];
    		newToonActivity.crewSkillID3 = iNumber;
    		break;
    	// These are for the new needs window
    	case R.id.spinnerToon:
    		newNeedsActivity.toonName = parent.getItemAtPosition(pos).toString();
    		break;
    	case R.id.spinnerYield:
    		newNeedsActivity.yield = parent.getItemAtPosition(pos).toString();
    		break;
    	case R.id.spinnerGrade:
    		newNeedsActivity.grade = parent.getItemAtPosition(pos).toString();
    		break;
    	case R.id.spinnerCount:
    		newNeedsActivity.count = parent.getItemAtPosition(pos).toString();
    		break;
    	default:
    		return;
    	}
    	
 //   	Toast.makeText(parent.getContext(), textString +
 //   			parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

}
