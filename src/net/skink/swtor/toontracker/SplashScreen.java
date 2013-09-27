package net.skink.swtor.toontracker;

import java.io.IOException;
import java.io.InputStream;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
	private static String TAG = "SplashScreen";
	int screenWidth;
	int screenHeight;

	
	class RenderView extends View {
		Bitmap myBitmap;
		Rect dst = new Rect();
		
		public RenderView(Context context) {
			super(context);
			try {
				AssetManager assetManager = context.getAssets();
				
				// This one has white bg
				InputStream inputStream = assetManager.open("pictures/skinksmall.jpg");
				myBitmap = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
//				Log.d(TAG, "skinkdesktop.jpg format: "+myBitmap.getConfig());
				
			} catch (IOException e) {
				// silently ignored. Bad coder monkey, bad!
				Log.d(TAG, "EXCEPTION: Couldn't load file, "+e.getMessage());
			} finally {
				// We should really close our input streams here.
			}

		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			int bitmapWidth;
			int bitmapHeight;
			int myWidth;
			int myHeight;
			float scale;
			float scaleHorizontal;
			float scaleVertical;
			int horizontalDiff=0;
			int verticalDiff=0;
			
			// Determine our properties for screen and bitmap
			screenWidth = canvas.getWidth(); // 480
			screenHeight = canvas.getHeight(); // 800
			bitmapWidth = myBitmap.getWidth(); // 503
			bitmapHeight = myBitmap.getHeight(); // 266
			
			// Determine what is our limiting resource - screen or bitmap
			scaleHorizontal = (float)screenWidth/(float)bitmapWidth;			 
			scaleVertical = (float)screenHeight/(float)bitmapHeight;
			if (scaleHorizontal < scaleVertical) {
				scale = scaleHorizontal;
			} else {
				scale = scaleVertical;
				
			}
			// Our scaled bitmap dimensions
			myWidth = (int) (bitmapWidth*scale);
			myHeight = (int) (bitmapHeight*scale);
			
			// Now adjust coordinates so that we center the picture
			// Which
			if (myHeight == screenHeight) {
				// It uses full screen Height
				horizontalDiff = (screenWidth-myWidth)/2;
				
			} else {
				// It uses full screen Width
				verticalDiff = (screenHeight-myHeight)/2;
				
			}
			
			// display the centered and sized bitmap.
			dst.set(0+horizontalDiff,
					0+verticalDiff,
					myWidth+horizontalDiff,
					myHeight+verticalDiff);// left,top, right, bottom
			canvas.drawBitmap(myBitmap,null,dst,null);
			invalidate();			
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		int flags, mask;
		flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
		mask = flags;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(flags, mask);
		setContentView(new RenderView(this));
		
		// thread for displaying the SplashScreen
	    Thread splashTread = new Thread() {
			protected boolean _active = true;
			protected int _splashTime = 1000; // time to display the splash screen in ms

	    	
	    	@Override
	        public void run() {
	            try {
	                int waited = 0;
	                while(_active && (waited < _splashTime)) {
	                    sleep(100);
	                    if(_active) {
	                        waited += 100;
	                    }
	                }
	            } catch(InterruptedException e) {
	                // do nothing
	            } finally {
	                finish();
	        	    Intent intent = new Intent("net.skink.swtor.toontracker.MainScreen");
	        	    intent.putExtra("screenWidth",screenWidth);
	        	    intent.putExtra("screenHeight",screenHeight);	                
	                startActivity(intent);
//	                startActivity(new Intent("net.skink.swtor.toontracker.MainScreen"));
//	                stop();
	            }
	        }
	    };
	    splashTread.start();
		
	}
}
