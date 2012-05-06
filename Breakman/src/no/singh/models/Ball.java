package no.singh.models;

import no.singh.breakman.R;
import no.singh.activities.GameActivity;
import no.singh.utility.Screenadapter;
import no.singh.utility.Values;
import no.singh.views.GameView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class Ball {
	
	MediaPlayer sound;
	private float posX ,posY;
	private float dX, dY;
	private float velocity;
	private Bitmap image;
	
	
	public Ball(){
		image = BitmapFactory.decodeResource(Screenadapter.context.getResources(), R.drawable.blue_ball);
		posX = (Screenadapter.width/2) - (image.getWidth()/2); //initialize ball in the center of the screen
		posY = (Screenadapter.height/2) - (image.getHeight()/2); //initialize ball in the center of the screen
		System.out.println("X: " + posX);
		System.out.println("Y: " + posY);
		velocity = Values.BALLVELOCITYCONSTANT;
		initRandomDirection(); 
	} 
	
	
	public void initRandomDirection(){
		dX = (int) (Math.random()*4) + 1;
		dY = (int) (Math.random()*4) + 1;
		normalize(); 
	} 
	 
	private void normalize() {
		double vel = (double) Math.sqrt(Math.pow(dX, 2)+Math.pow(dY, 2));
		dX /=vel;
		dY /=vel;
		dX *= velocity;
		dY *= velocity;
	}
	
	public void update(){
		posX += dX;
		posY += dY;
		checkWallBounce();
		
	}
	
	private void checkWallBounce(){
		dX = (posX <= 0 || (posX+image.getWidth()) >= Screenadapter.width)? dX*-1:dX;
		dY = (posY <= 0 || posY+image.getHeight() >= Screenadapter.height)? dY*-1:dY;
	}
	
	public void draw(Canvas c){
		update(); 
		c.drawBitmap(image, posX, posY, null);
	}
	
	public float getX(){
		return posX;
	}
	
	public float getY(){
		return posY;
	}
	
	public Bitmap getImage(){
		return image;
	}


}
