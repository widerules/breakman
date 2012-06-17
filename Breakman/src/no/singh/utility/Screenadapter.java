package no.singh.utility;

import android.content.Context;
import android.graphics.Canvas;

/**This class will always save the screen dimensions of the android devices
 * They will be reachable through their static property
 *   */
public class Screenadapter {
	
	public static float width;
	public static float height;
	public static Context context;
	
	
	public static void inititalizeScreenAdapter(Canvas can, Context con){
		width = can.getWidth();
		height = can.getHeight();
		context = con;
		Values.setScaledChipWidth();
		Values.setScaledChipHeight();
		Values.setChipSpaceWidth();
		Values.setChipSpaceHeight();
	}
	
	
	
}
