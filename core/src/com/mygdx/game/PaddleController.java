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
	
	private boolean paddleReady; 
	
	public static final float DEFAULT_COOLDOWN = 1.5f; 
	
	
	public PaddleController(float coolDown) {
		paddles = new ArrayList<Paddle>();
		
		paddleMarker = new Sprite(new Texture("paddleMarker.png")); 
		paddleMarker.setX(Paddle.DEFAULT_X);
		
		timer = 0;
		this.coolDown = coolDown;
		
		paddleReady = true; 
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
			if (p.isDead()) {
				paddlesToRemove.add(p); 
			}
		}
		
		removeAllPaddles(paddlesToRemove); 
		
		
		paddleMarker.setY(GdxGame.height - Gdx.input.getY() - Paddle.HEIGHT / 2);
	}
	
	public List<Paddle> getPaddles() {
		return paddles; 
	}
	
	//Add paddle if the timer is smaller than or equal to 0
	public void attemptAddPaddle(Paddle paddle) {
		if (paddleReady) {
			paddles.add(paddle); 
		}
	}
	
	public void attemptAddPaddle() {
		if (paddleReady) {
			paddles.add(new Paddle(paddleMarker.getX(), paddleMarker.getY())); 
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
		return paddleReady; 
	}
	
	public void setPaddleReady(boolean paddleReady) {
		this.paddleReady = paddleReady; 
	}
	
}
