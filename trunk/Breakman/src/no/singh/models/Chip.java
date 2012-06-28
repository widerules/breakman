package no.singh.models;

import no.singh.breakman.R;
import no.singh.utility.Logic;
import no.singh.utility.Screenadapter;
import no.singh.utility.Values;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class Chip {
	
	private int[] coord; //this chips coordinates on the chipController Grid
	private Bitmap image;
	private float x;
	private float y;
	private boolean alive;
	private float[] northWest; //Coordinates of the upper left corner 
	private float[] northEast; //Coordinates of the upper Right corner 
	private float[] southWest; //Coordinates of the bottom left corner 
	private float[] southEast; //Coordinates of the bottom right corner 
	
	public Chip(int indexX, int indexY, float posX, float posY, char c, boolean random){ 
		setChipColor(c,random);
		x = posX; 
		y = posY; 
		initCoordinates(indexX, indexY);
		alive = true;
	}
	
	private void setChipColor(char c, boolean random){
		if(random){
			char[] ranChar = {'y','g','r'};
			int ran = (int) (Math.random() * 150) %3;
			c = ranChar[ran];
		}
		switch (c) {
		case 'g':
			image = BitmapFactory.decodeResource(Screenadapter.context.getResources(), R.drawable.greenchip);
			break;
		case 'y':
			image = BitmapFactory.decodeResource(Screenadapter.context.getResources(), R.drawable.yellowchip);
			break;
		case 'r':
			image = BitmapFactory.decodeResource(Screenadapter.context.getResources(), R.drawable.redchip);
			break;
		default:
			break;
		}
	}
	
	public void resizeBitmap(float newHeight, float newWidth) {
		int width = image.getWidth();
		int height = image.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		image = Bitmap.createBitmap(image, 0, 0, width, height, matrix, false);
	}
	
	public static Chip getProtoType(){
		Chip c = new Chip(0, 0, 0, 0, 'y',true);
		return c;
	}
	
	private void initCoordinates(int indexX, int indexY){
		int[] in = {indexX, indexY};
		coord = in;
		float[] nw = {this.x, this.y};
		northWest = nw;
		float[] ne = {this.x+Values.scaledChipWidth,this.y};
		northEast = ne;
		float[] sw = {this.x, this.y+Values.scaledChipHeight};
		southWest = sw; 
		float[] se = {this.x+Values.scaledChipWidth, this.y+Values.scaledChipHeight}; 
		southEast = se;
	}
	
	public void kill(){
		alive = false;
	}
	
	public boolean isAlive(){
		return alive; 
	}
	
	public Bitmap getImage(){
		return image;
	}
	
	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
	
	public int[] getCoord(){
		return coord;
	}

	public float[] getNorthWest() {
		return northWest;
	}

	public float[] getNorthEast() {
		return northEast;
	}

	public float[] getSouthWest() {
		return southWest;
	}

	public float[] getSouthEast() {
		return southEast;
	}
	
}
