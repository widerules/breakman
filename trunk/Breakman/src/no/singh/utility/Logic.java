package no.singh.utility;

import android.view.animation.BounceInterpolator;
import no.singh.models.Ball;
import no.singh.models.Chip;

public class Logic {
	
//	public static float getScaledChipWidth(){
//		return Screenadapter.width/(Values.CHIPS_ALONG_WIDTH+1);
//	}
//	
//	public static float getScaledChipHeight(){
//		return (getScaledChipWidth()*Chip.getProtoType().getImage().getHeight())/Chip.getProtoType().getImage().getWidth();
//	}
//	
//	public static float getChipSpaceWidth(){
//		return getScaledChipWidth()/(Values.CHIPS_ALONG_WIDTH+1);
//	}
//	
//	public static float getChipSpaceHeight(){
//		return getChipSpaceWidth();
//	}
	
	public static void detectCollisionHitPoint(Ball b, Chip c){
		if(c.getNorthEast()[0] - b.getX() < Values.COLLISION_THRESHOLD || c.getNorthWest()[0] - b.getX() < Values.COLLISION_THRESHOLD){
			b.bounceRight();
		}
		else if(c.getSouthWest()[1] - b.getY() < Values.COLLISION_THRESHOLD || c.getNorthEast()[1] - b.getY() < Values.COLLISION_THRESHOLD ){
			b.bounceDown();
		}
	}
	
 
}
