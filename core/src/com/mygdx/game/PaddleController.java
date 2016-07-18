package com.mygdx.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PaddleController {
	private List<Paddle> paddles; 
	
	private float coolDown;
	private float timer; 
	
	private Sprite paddleMarker; 
	
	//private boolean paddleReady; 
	private int maxPaddles; 
	
	public static final float DEFAULT_COOLDOWN = 0.25f; 
	
	
	public PaddleController(float coolDown) {
		paddles = new ArrayList<Paddle>();
		
		paddleMarker = new Sprite(new Texture("paddleMarker.png")); 
		paddleMarker.setX(Paddle.DEFAULT_X);
		
		timer = 0;
		this.coolDown = coolDown;
		maxPaddles = 1; 
	}
	
	
	public void draw(SpriteBatch batch) {
		
		
		for (Paddle p : paddles) {
			p.draw(batch);
		}
		paddleMarker.draw(batch);
		
	}
	
	public void tick(float deltaTime) {
		timer -= deltaTime; 
		
		
		
		List<Paddle> paddlesToRemove = new ArrayList<Paddle>(); 
		
		for (Paddle p : paddles) {
			p.tick(deltaTime);
			
		}
		
		removeAllPaddles(paddlesToRemove); 
		
		
		paddleMarker.setY(GdxGame.getInstance().height - Gdx.input.getY() - Paddle.HEIGHT / 2);
	}
	
	public List<Paddle> getPaddles() {
		return paddles; 
	}
	
	//Add paddle if the timer is smaller than or equal to 0
	public void attemptAddPaddle(Paddle paddle) {
		System.out.println("Attempting to add paddle. paddles.size() = " + paddles.size()
		+ ". Max Paddles: " + maxPaddles); 
		if (isPaddleReady() && paddles.size() < maxPaddles) {
			timer = coolDown;
			paddles.add(paddle); 
			System.out.println("Paddle added"); 
		}
	}
	
	public void attemptAddPaddle() {
		System.out.println("Attempting to add paddle. paddles.size() = " + paddles.size()
		+ ". Max Paddles: " + maxPaddles); 
		if (isPaddleReady() && paddles.size() < maxPaddles) {
			timer = coolDown; 
			paddles.add(new Paddle(paddleMarker.getX(), paddleMarker.getY())); 
			System.out.println("Paddle added"); 
		}
	}
	
	public void removePaddle(Paddle paddle) {
		paddles.remove(paddle); 
	}
	
	public void clearPaddles() {
		paddles.clear(); 
	}
	
	public void removeAllPaddles(Collection<Paddle> toRemove) {
		paddles.removeAll(toRemove); 
	}
	
	public boolean isPaddleReady() {
		return GdxGame.getInstance().getBall().goingRight() && 
				paddles.size() < maxPaddles && 
				timer <= 0; 
	}
	
	
	public void setMaxPaddles(int maxPaddles) {
		this.maxPaddles = maxPaddles; 
	}
	
	public void extendPaddles() {
		for (Paddle p : paddles) {
			p.extendPaddle();
		}
	}
	
	public void shortenPaddles() {
		for (Paddle p : paddles) {
			p.shortenPaddle();
		}
	}
}
