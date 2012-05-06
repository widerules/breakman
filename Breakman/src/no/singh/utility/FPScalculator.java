package no.singh.utility;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FPScalculator {
	
	public long prevMS; //Time of last draw
	public long currentMS; //Current time difference from last draw
	public long second; //Times one second, used for calculating frames per second.
	public int oldFPS; // The one that is painted
	public int currentFPS; //We calculate this until a second has passed, then set value to old. paint old value for One whole second.
	public long dt;

	public FPScalculator() {
		prevMS = System.currentTimeMillis();
		currentMS = 0;
		second = 0; 
		oldFPS = 0; 
		currentFPS = 0; 
		dt = 0;
	}
	
	private void calcFPS() {
	  long tempTime = System.currentTimeMillis();
	  currentMS = tempTime - prevMS;
	  prevMS = tempTime;
	  second += currentMS;
	  if(second < 1000)currentFPS++;
	  else{
		   second = 0;
		   oldFPS = currentFPS;
		   currentFPS = 0;
	  }
	  dt+=currentMS;
	}
	
	public void drawFPS(Canvas c){ 
		calcFPS();
		Paint p = new Paint();
		p.setColor(Color.YELLOW);
		p.setTextSize(10);
		c.drawText("FPS: " + oldFPS, Screenadapter.width/2, Screenadapter.height/2, p);
	}
}
	
	
