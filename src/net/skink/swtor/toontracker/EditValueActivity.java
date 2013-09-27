package net.skink.swtor.toontracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditValueActivity extends Activity implements OnClickListener {
	Button buttonOK;
	Button buttonCancel;
	EditText editTextValue;
	
	String result=null;
	
	public void onCreate(Bundle savedInstanceState) {	    		    	
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.edit_value);
	    
	    // Get the UI components
	    buttonOK = (Button) findViewById(R.id.buttonOK);	  
	    buttonCancel = (Button) findViewById(R.id.buttonCancel);
	    editTextValue = (EditText) findViewById(R.id.editText1);
	    
	    
	    buttonOK.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
    	
		Intent outData=new Intent();

    	switch(v.getId()) {
        
    	case R.id.buttonOK:   
        	result= new String();
        	result = editTextValue.getText().toString();
        	
    	    outData.putExtra("result",result);
        	setResult(Activity.RESULT_OK,outData);
    		break;
        
        case R.id.buttonCancel:
        	setResult(Activity.RESULT_CANCELED,outData);
    		break;	
        }
    	finish();
	}


}
