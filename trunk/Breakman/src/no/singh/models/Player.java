package no.singh.models;

public class Player {

	private String name;
	private int id; //kommmer sikkert godt med fremover
	private int score;
	
	
	public Player(String n){
		name = n;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	
}
