package no.singh.activities;

import no.singh.views.GameView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameActivity extends Activity implements OnTouchListener {
	
	GameView gv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gv = new GameView(this);
        gv.setOnTouchListener(this);
        setContentView(gv);
        
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	gv.pause();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	gv.resume();
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		try {
			//onTouch runs continously. 20 times per second is maybe enough? 
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		return false;
	}
}