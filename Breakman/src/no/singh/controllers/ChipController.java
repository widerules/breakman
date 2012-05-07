package no.singh.controllers;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.util.Log;

import no.singh.models.Chip;
import no.singh.utility.Logic;
import no.singh.utility.Screenadapter;
import no.singh.utility.Values;

public class ChipController {
	
	private ArrayList<Chip> chips;
	
	public ChipController(){
		initChips();
	}

	private void initChips() {
		chips = new ArrayList<Chip>();
		float scaledWidth = Logic.getScaledChipWidth();
		float scaledHeight = Logic.getScaledChipHeight();
		float spaceWidth =  Logic.getChipSpaceWidth();
		float spaceHeight = Logic.getChipSpaceHeight();
		for (int i = 0; i < Values.CHIPS_ALONG_WIDTH; i++) {
			for (int j = 0; j < Values.CHIPS_ALONG_HEIGHT; j++) {
				Chip c = new Chip(i, j, spaceWidth+(i*spaceWidth) + (i*scaledWidth), spaceHeight+(j*spaceHeight) + (j*scaledHeight), 'y',true);
				c.resizeBitmap(scaledHeight, scaledWidth);
				chips.add(c);
			}
		}
	}
	
	public void onDraw(Canvas c){
		for (int i = 0; i < chips.size(); i++) {
			c.drawBitmap(chips.get(i).getImage(), chips.get(i).getX(), chips.get(i).getY(), null);
		}
	}
	
	 

}
