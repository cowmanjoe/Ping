package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ball extends Sprite{
	private float dx; 
	private float dy; 
	
	
	
	public Ball(float x, float y, float dx, float dy) {
		super(new Texture("ball.jpg")); 
		
		setX(x); 
		setY(y); 
		this.dx = dx; 
		this.dy = dy; 
	}
	
	public void tick(float deltaTime, List<Paddle> paddles) {
		if (getX() + getWidth() >= GdxGame.width) {
			dx = -Math.abs(dx); 
		}
		if (getY() <= 0) dy = Math.abs(dy);
		if (getY() + getHeight() >= GdxGame.height) dy = -Math.abs(dy); 
		
		for (Paddle p : paddles) {
			if (getX() <= p.getX() + p.getWidth() && 
					getX() > p.getX() && 
					getY() <= p.getY() + p.getHeight() && 
					getY() + getHeight() >= p.getY()) {
				dx = Math.abs(dx); 
				dy = dy + 2f * (getY() + (getHeight() / 2) - (p.getY() + (p.getHeight() / 2)));
				paddles.clear(); 
				break; 
			}
		}
		
		
		setX(getX() + dx * deltaTime); 
		setY(getY() + dy * deltaTime); 
		
		
	}
	
	public float getWidth() {
		return getTexture().getWidth(); 
	}
	
	public float getHeight() {
		return getTexture().getHeight(); 
	}
	
	public boolean goingRight() {
		return dx > 0; 
	}
	
	public boolean goingLeft() {
		return dx < 0; 
	}
}
