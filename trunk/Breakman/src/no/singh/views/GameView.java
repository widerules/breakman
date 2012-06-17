package no.singh.views;

import no.singh.controllers.ChipController;
import no.singh.models.Ball;
import no.singh.models.Game;
import no.singh.utility.FPScalculator;
import no.singh.utility.Screenadapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback{
	
	Thread t = null;
	SurfaceHolder holder;
	boolean clearToRun = false;
	private FPScalculator FPScalc;
	boolean first = true;
	private Game game;

	public GameView(Context context) {
		super(context);
		holder = getHolder();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDraw(Canvas c) {
		super.onDraw(c);
		
		c.drawColor(Color.BLUE);
		game.onDraw(c);		
		FPScalc.drawFPS(c);
	}
	
	@Override
	public void run() {
		first = true;
		while(clearToRun == true){ 
			if (!holder.getSurface().isValid()) {
				continue;
			}
			Canvas c = holder.lockCanvas();
			if(first)initGameView(c, getContext());
			onDraw(c);
			holder.unlockCanvasAndPost(c); 
		}
		
	} 
	
	public void pause(){
		clearToRun = false;
		while(true){
			try{
				t.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		t = null;
	}
	
	public void resume(){
		clearToRun = true;
		t = new Thread(this);
		t.start();
	}
	
	private void initGameView(Canvas c, Context cont){
		Screenadapter.inititalizeScreenAdapter(c, cont);
		game = Game.getInstance();
		FPScalc = new FPScalculator();
		first = false;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}



}
