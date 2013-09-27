package net.skink.swtor.toontracker;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MyArmorsOnItemClickListener implements OnItemClickListener {
	 private static String TAG = "OnGiftsItemClickListener";
	 private SearchArmor theActivity;
	 
	public MyArmorsOnItemClickListener(SearchArmor a) {
		theActivity = a;
	}	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//		Log.d(TAG, "onitemselected"); 
		String armor;
   	
    	switch (parent.getId()){
    	case R.id.listView1:
    		armor = parent.getItemAtPosition(pos).toString();
    		//Toast.makeText(v.getContext(), companionName, Toast.LENGTH_SHORT).show();
    		break;

    	default:
    		return;
    	}
    	
    	theActivity.findArmors(armor);

	}

}
