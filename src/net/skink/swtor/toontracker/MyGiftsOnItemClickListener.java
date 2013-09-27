package net.skink.swtor.toontracker;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MyGiftsOnItemClickListener implements OnItemClickListener {
	 private static String TAG = "OnGiftsItemClickListener";
	 private SearchGifts theActivity;
	 
	public MyGiftsOnItemClickListener(SearchGifts a) {
		theActivity = a;
	}	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//		Log.d(TAG, "onitemselected"); 
		String gift;
   	
    	switch (parent.getId()){
    	case R.id.listView1:
    		gift = parent.getItemAtPosition(pos).toString();
    		//Toast.makeText(v.getContext(), companionName, Toast.LENGTH_SHORT).show();
    		break;

    	default:
    		return;
    	}
    	
    	theActivity.findGifts(gift);

	}

}
