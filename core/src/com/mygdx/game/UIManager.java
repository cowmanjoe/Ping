package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIManager {
	
	private Score score; 
	private PowerupText powerupText; 
	
	private int scoreX; 
	private int scoreY; 
	
	private int powerupTextX; 
	private int powerupTextY;
	
	public UIManager() {
		scoreX = 20; 
		scoreY = 20; 
		score = new Score(scoreX, scoreY, "Score: "); 
		
		powerupText = new PowerupText(); 
	}
	
	
	public void draw(SpriteBatch batch) {
		score.draw(batch);
		
		powerupText.setX(GdxGame.getInstance().width - powerupText.getWidth(batch)); 
		powerupText.setY(powerupText.getHeight()); 
		
		powerupText.draw(batch);
		
	}
	
	public boolean hasPowerup(Powerup powerup) {
		return powerupText.hasPowerup(powerup);
	}
	
	public void addPowerup(Powerup powerup) {
		powerupText.addPowerup(powerup);
	}
	
	public void setScore(int score) {
		this.score.setScore(score);
	}
	
	public void increaseScore(int score) {
		this.score.increaseBy(score);
	}
}
