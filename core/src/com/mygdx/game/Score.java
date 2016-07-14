package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {
	
	private BitmapFont font; 
	
	private String text; 
	private int score; 
	
	private int x; 
	private int y; 
	
	// Makes a new Score object (on screen score display) with
	// at the given x and y coordinates with the value of 
	// text + score
	// score is initialized to 0
	public Score(int x, int y, String text) {
		font = new BitmapFont(); 
		font.setColor(Color.RED);
		
		this.x = x; 
		this.y = y; 
		this.text = text; 
		score = 0; 
	}
	
	
	public void draw(SpriteBatch batch) {
		font.draw(batch, text + score, x, y);
	}
	
	
	public void setScore(int score) {
		this.score = score; 
	}
	
	public int getScore() {
		return score; 
	}
	
	public void increaseBy(int amount) {
		this.score += amount; 
	}
}
