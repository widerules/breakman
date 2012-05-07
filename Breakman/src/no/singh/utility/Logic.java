package no.singh.utility;

import no.singh.models.Chip;

public class Logic {
	
	public static float getScaledChipWidth(){
		return Screenadapter.width/(Values.CHIPS_ALONG_WIDTH+1);
	}
	
	public static float getScaledChipHeight(){
		return (getScaledChipWidth()*Chip.getProtoType().getImage().getHeight())/Chip.getProtoType().getImage().getWidth();
	}
	
	public static float getChipSpaceWidth(){
		return getScaledChipWidth()/(Values.CHIPS_ALONG_WIDTH+2);
	}
	
	public static float getChipSpaceHeight(){
		return getChipSpaceWidth();
	}
	
 
}
