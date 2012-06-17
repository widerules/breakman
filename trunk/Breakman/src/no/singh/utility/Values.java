package no.singh.utility;

import no.singh.models.Chip;

public class Values {
	
	public static final float BALL_VELOCITY_CONSTANT = 2f;
	public static final int CHIPS_ALONG_WIDTH = 8;
	public static final int CHIPS_ALONG_HEIGHT = 8;
	public static final float COLLISION_THRESHOLD = 1f;
	public static float scaledChipWidth;
	public static float scaledChipHeight;
	public static float chipSpaceWidth;
	public static float chipSpaceHeight;
	
	public static void setScaledChipWidth(){
		scaledChipWidth = Screenadapter.width/(Values.CHIPS_ALONG_WIDTH+1);
	}
	public static void setScaledChipHeight(){
		scaledChipHeight = (Values.scaledChipWidth*Chip.getProtoType().getImage().getHeight())/Chip.getProtoType().getImage().getWidth();
	}
	public static void setChipSpaceWidth(){
		chipSpaceWidth = Values.scaledChipWidth/(Values.CHIPS_ALONG_WIDTH+1);
	}
	public static void setChipSpaceHeight(){
		chipSpaceHeight = chipSpaceWidth;
	}
	 
}
 