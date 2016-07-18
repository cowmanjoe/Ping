package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// A powerup that appears on the screen. 
// Different effects based on powerupType
// 
public class Powerup extends Sprite {
	
	private PowerupType powerupType; 
	
	private float initLifetime; 
	private float lifetime; 
	private float activeTime; 
	
	//false when the powerup is on the screen 
	//true when it disappears after being hit and has taken effect
	private boolean isActive; 
	private boolean removed; 
	
	public static final float DEFAULT_LIFETIME = 20f; 
	public static final float DEFAULT_ACTIVE_TIME = 10f; 
	
	
	public Powerup(int x, int y, PowerupType powerupType) {
		super(getTextureFromType(powerupType)); 
		
		this.powerupType = powerupType; 
		
		setX(x); 
		setY(y); 
		
		initLifetime = DEFAULT_LIFETIME; 
		lifetime = DEFAULT_LIFETIME; 
		activeTime = DEFAULT_ACTIVE_TIME; 
		
		isActive = false; 
		
	}
	
	public Powerup(int x, int y, PowerupType powerupType, float lifetime, float activeTime) {
		super(getTextureFromType(powerupType)); 
		
		this.powerupType = powerupType; 
		
		setX(x); 
		setY(y);
		
		this.initLifetime = lifetime; 
		this.lifetime = lifetime; 
		this.activeTime = activeTime;
		
		isActive = false; 
	}
	
	public void tick(float deltaTime) {
		if (!isActive) {
			if (lifetime < 0) {
				removed = true;
				return; 
			}
			lifetime -= deltaTime; 
			if (Collision.intersect(this, GdxGame.getInstance().getBall()))
				isActive = true; 
				
		}
		else {
			if (activeTime < 0) {
				removed = true; 
				return; 
			}
			activeTime -= deltaTime; 
			
		}
		
		setColor(1, 1, 1, lifetime / initLifetime);
	}
	
	public void draw(SpriteBatch batch) {
		if (!isActive && !removed) {
			super.draw(batch);
		}
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
	
	public boolean isActive() {
		return isActive; 
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isRemoved() {
		return removed; 
	}
	
	public PowerupType getType() {
		return powerupType; 
	}
	
	public String toString() {
		switch (powerupType) {
		case TWO_PADDLES : 
			return "Two Paddles";
		case THREE_PADDLES : 
			return "Three Paddles"; 
		case LONG_PADDLE : 
			return "Long Paddle"; 
		default: 
			return null; 
			
		}
	}
	
	public float getActiveTime() {
		return activeTime; 
	}
}
