package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Powerup extends Sprite {
	
	private PowerupType powerupType; 
	
	
	public Powerup(int x, int y, PowerupType powerupType) {
		super(getTextureFromType(powerupType)); 
		
		setX(x); 
		setY(y); 
	}
	
	
	
	private static Texture getTextureFromType(PowerupType powerupType) {
		String fileName;
		
		switch (powerupType) {
			case TWO_PADDLES : 
				fileName = "twoPaddles.jpg";
				break; 
			case THREE_PADDLES :
				fileName = "threePaddles.jpg"; 
				break; 
			case LONG_PADDLE :
				fileName = "longPaddle.jpg"; 
				break; 
			default: 
				fileName = ""; 
		}
		
		if (fileName == "") 
			return null; 
		
		return new Texture(fileName); 
	}
}
