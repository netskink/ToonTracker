package net.skink.swtor.toontracker;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MyNeedsMatsBaseAdapter extends BaseAdapter implements ListAdapter {

	private Context mContext;
    public String[] mGridViewArrayStrings;
    public int minPosition=0;
    public float fFontSize=8.0f;
	
	 public MyNeedsMatsBaseAdapter(Context c) {
	        mContext = c;
	        //todo: what happens if they dont set the array?
	 }
	
	public MyNeedsMatsBaseAdapter(Context c,int stringArrayID) {
		 mContext = c;
		 mGridViewArrayStrings = mContext.getResources().getStringArray(stringArrayID);
	}

	@Override
	public int getCount() {
		return mGridViewArrayStrings.length;
	}

	@Override
	public Object getItem(int position) {
		 return mGridViewArrayStrings[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
//            textView.setLayoutParams(new GridView.LayoutParams(60, 15));
//            if (position >6) {
            	textView.setPadding(1, 15, 1, 15);
//            }
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(mGridViewArrayStrings[position]);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,fFontSize); 
        //TypedValue.COMPLEX_UNIT_PX : Pixels
        //TypedValue.COMPLEX_UNIT_SP : Scaled Pixels
        //TypedValue.COMPLEX_UNIT_DIP : Device Independent Pixels
//        if (position==minPosition){
//        	textView.setTextColor(MyColors.GiftYellow);
//        }
        return textView;
	}
	 // references to our images
//    private Integer[] mGearTextIDs = mContext.getResources().getStringArray(R.array.gear_array);
    
}
