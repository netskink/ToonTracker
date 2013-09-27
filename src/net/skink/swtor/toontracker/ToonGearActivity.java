package net.skink.swtor.toontracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

public class ToonGearActivity extends MyMenuActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toon_gear);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MyBaseAdapter(this,R.array.gear_array,screenWidth, screenHeight));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ToonGearActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}