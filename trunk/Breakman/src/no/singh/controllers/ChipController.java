package no.singh.controllers; 

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import android.graphics.Canvas;
import android.util.Log;

import no.singh.models.Ball;
import no.singh.models.Chip;
import no.singh.models.Game;
import no.singh.utility.Logic;
import no.singh.utility.Screenadapter;
import no.singh.utility.TestPaint;
import no.singh.utility.Values;

public class ChipController implements PropertyChangeListener {
	
	private ArrayList<Chip> chips;
	
	public ChipController(){
		initChips();
		Ball.addPropertyChangeListener(this);
	}

	private void initChips() {
		//can maybe remove the 4 lines beneath! 
		chips = new ArrayList<Chip>();
		float scaledWidth = Values.scaledChipWidth;
		float scaledHeight = Values.scaledChipHeight;
		float spaceWidth =  Values.chipSpaceWidth;
		float spaceHeight = Values.chipSpaceHeight;
		for (int i = 0; i < Values.CHIPS_ALONG_WIDTH; i++) {
			for (int j = 0; j < Values.CHIPS_ALONG_HEIGHT; j++) {
				Chip c = new Chip(i, j, spaceWidth+(i*spaceWidth) + (i*scaledWidth), spaceHeight+(j*spaceHeight) + (j*scaledHeight), 'y',true);
				c.resizeBitmap(scaledHeight, scaledWidth);
				chips.add(c);
				//Husk at chippene blir adda øverst til venstre og nedover!
			}
		}
	}
	
	public void onDraw(Canvas c){
		for (int i = 0; i < chips.size(); i++) {
			if(chips.get(i).isAlive()){
				c.drawBitmap(chips.get(i).getImage(), chips.get(i).getX(), chips.get(i).getY(), null);
				c.drawText(""+i, chips.get(i).getX(), chips.get(i).getY(), TestPaint.getTestPaint());
			}
		}
	}
	
	public ArrayList<Chip> getChips(){
		return chips;
	}
	
	@Override
	//Communication with Ball-class through property change support
	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equals("killchip")){
			chips.get((Integer) event.getNewValue()).kill();
		}
	}
	
	
	
	
	 

}
