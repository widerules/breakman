package no.singh.models;

import java.beans.PropertyChangeSupport;

import android.graphics.Canvas;

import no.singh.controllers.ChipController;

public class Game {
	
	private static Game instance = null;
	private Player player;
	private Scoreboard scores;
	private Ball ball;
	private ChipController chipcontroller;
	private Controller controller;
	private Word word;
	
	private static PropertyChangeSupport pcs;
	
	
	public static Game getInstance(){
		if (instance==null) {
			instance = new Game();
			return instance;
		}
		return instance;
	}
	

	public Game(){
		scores = new Scoreboard();
		ball = new Ball();
		chipcontroller = new ChipController();
		controller = new Controller();
	}
	
	public void onDraw(Canvas c){
		chipcontroller.onDraw(c);
		ball.draw(c); 

	}
	
	public ChipController getChipController(){
		return chipcontroller;
	}
	
}
