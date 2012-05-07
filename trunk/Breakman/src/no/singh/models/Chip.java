package no.singh.models;

import no.singh.breakman.R;
import no.singh.utility.Screenadapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class Chip {
	
	private int[] index;
	private Bitmap image;
	private float x;
	private float y;
	private boolean alive;
	
	public Chip(int indexX, int indexY, float posX, float posY, char c, boolean random){
		setChipColor(c,random);
		int[] in = {indexX, indexY};
		index = in;
		x = posX;
		y = posY;
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
	
	public Bitmap getImage(){
		return image;
	}
	
	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
	
}
