package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerupText {
	
	private BitmapFont font; 
	
	private List<Powerup> powerups; 
	
	private int x; 
	private int y; 
	
	private String text; 
	
	public PowerupText() {
		font = new BitmapFont(); 
		font.setColor(Color.GREEN); 
		
		text = ""; 
		
		powerups = new ArrayList<Powerup>(); 
		
	}
	
	public void addPowerup(Powerup p) {
		for (Powerup next : powerups) {
			if (p.getType() == next.getType()) {
				powerups.remove(next); 
				powerups.add(p); 
				return; 
			}
		}
		powerups.add(p); 
	}
	
	public void draw(SpriteBatch batch) {
		text = ""; 
		for (Powerup p : powerups) {
			text += p.toString() + ": " + (int) p.getActiveTime() + "\n"; 
			
		}
		
		
		System.out.println("powerupText = " + text); 
		
		font.draw(batch, text, x, y); 
	}
	
	public void tick() {
		
	}
	
	public boolean hasPowerup(Powerup powerup) {
		for (Powerup p : powerups) {
			if (p == powerup) return true; 
		}
		
		return false; 
	}
	
	public void setX(int x) {
		this.x = x; 
	}
	
	public int getX() {
		return x; 
	}
	
	public void setY(int y) {
		this.y = y; 
	}
	
	public int getY() {
		return y; 
	}
	
	public int getWidth(SpriteBatch batch) {
		return (int)(font.draw(batch, text, -100, -100).width);
	}
	
	public int getHeight() {
		return (int)(powerups.size() * font.getLineHeight());
	}
}
