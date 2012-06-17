package no.singh.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import no.singh.breakman.R;
import no.singh.controllers.ChipController;
import no.singh.activities.GameActivity;
import no.singh.utility.Logic;
import no.singh.utility.Screenadapter;
import no.singh.utility.TestPaint;
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
	private float midX, midY;
	private float dX, dY;
	private float velocity;
	private Bitmap image;
	private boolean chipmirror[] = new boolean[Values.CHIPS_ALONG_HEIGHT*Values.CHIPS_ALONG_WIDTH];
	
	private static PropertyChangeSupport pcs; //Legger til denne for å oppdatere chipkontroller 

	/*
	 * 
	 * PCS: hittil propertyNames:* * killchip
	 */
	
	public Ball(){
		image = BitmapFactory.decodeResource(Screenadapter.context.getResources(), R.drawable.blue_ball);
		posX = (Screenadapter.width/2) - (image.getWidth()/2); //initialize ball in the center of the screen
		posY = (Screenadapter.height/2) - (image.getHeight()/2); //initialize ball in the center of the screen
		midX = posX + (Screenadapter.width/2);
		midY = posY + (Screenadapter.height/2);
		velocity = Values.BALL_VELOCITY_CONSTANT;
		pcs = new PropertyChangeSupport(this);
		initChipmirror();
		initRandomDirection(); 
		
	} 
	
	public static void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
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
		dY *= -velocity;
	}
	
	public void update(){
		posX += dX;
		posY += dY;
		checkWallBounce();
		collisionDetection();
		
	}
	
	private void checkWallBounce(){
		dX = (posX <= 0 || (posX+image.getWidth()) >= Screenadapter.width)? dX*-1:dX;
		dY = (posY <= 0 || posY+image.getHeight() >= Screenadapter.height)? dY*-1:dY;
	}
	
	public void draw(Canvas c){
		update(); 
		c.drawBitmap(image, posX, posY, null);
		c.drawCircle(posX, posY, 2f, TestPaint.getTestPaint());
	}
	
	public void collisionDetection(){
		if(posY < (Values.CHIPS_ALONG_HEIGHT* (Values.chipSpaceHeight + Values.scaledChipHeight)) ){
			//Locate Ball pos
			//The four lines beneath can most probably 
			float spaceHeight = Values.chipSpaceHeight;
			float spaceWidth = Values.chipSpaceWidth;
			float chipHeight = Values.scaledChipHeight;
			float chipWidth = Values.scaledChipWidth;
			int x = (int) ((int) posX/(spaceWidth+chipWidth));
			int y = (int) ((int) posY/(spaceHeight+chipHeight));
			
			//BOUNCE
			if (chipmirror[(x*Values.CHIPS_ALONG_HEIGHT)+y]){
				pcs.firePropertyChange("killchip", -1, (x*Values.CHIPS_ALONG_HEIGHT)+y);
				chipmirror[(x*Values.CHIPS_ALONG_HEIGHT)+y] = false;
				Logic.detectCollisionHitPoint(this, Game.getInstance().getChipController().getChips().get((x*Values.CHIPS_ALONG_HEIGHT)+y));
			}
		}
	}
	
	private void initChipmirror(){
		for (int i = 0; i < chipmirror.length; i++) {
			chipmirror[i]=true;
		}
	}
	
	public void bounceTop(){
		dY = -dY;
	}
	
	public void bounceDown(){
		bounceTop();
	}
	
	public void bounceRight(){
		dX = -dX;
	}
	
	public void bounceLeft(){
		bounceRight();
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
	
	public float getTopLeftX(){
		return posX;
	}
	
	public float getTopLeftY(){
		return posY;
	}
	
	public float getBottomRightX(){
		return posX+image.getWidth();
	}
	
	public float getBottomRightY(){
		return posY+image.getHeight();
	}


}
