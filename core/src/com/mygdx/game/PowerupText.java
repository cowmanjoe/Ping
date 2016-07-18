package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerupText {
	
	private BitmapFont font; 
	
	private List<Powerup> powerups; 
	
	
	
	public PowerupText() {
		font = new BitmapFont(); 
		font.setColor(Color.GREEN); 
		
		
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
		String str = ""; 
		for (Powerup p : powerups) {
			str += p.toString() + ": " + (int) p.getActiveTime() + "\n"; 
			
		}
		
		int x = (int)(GdxGame.getInstance().width - font.draw(batch, str, -100, -100).width); 
		int y = (int)(powerups.size() * font.getLineHeight()); 
		System.out.println("powerupText = " + str); 
		
		font.draw(batch, str, x, y); 
	}
	
	public void tick() {
		
	}
	
	public boolean hasPowerup(Powerup powerup) {
		for (Powerup p : powerups) {
			if (p == powerup) return true; 
		}
		
		return false; 
	}
}
