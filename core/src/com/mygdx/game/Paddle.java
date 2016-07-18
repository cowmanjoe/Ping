package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle extends Sprite{
	
	private float lifeTime; 
	private float initLifeTime; 
	
	public static final int WIDTH = 10; 
	public static final int HEIGHT = 60; 
	public static final int DEFAULT_X = 50; 
	
	public static final float DEFAULT_LIFETIME = 8.0f;
	
	private Sprite paddleSprite;
	private Sprite extendedPaddleSprite; 
	
	public Paddle(float x, float y) {
		super(new Texture("paddle.jpg"));
		paddleSprite = new Sprite(this); 
		extendedPaddleSprite = new Sprite(new Texture("extendedPaddle.jpg")); 
		
		setX(x);
		setY(y); 
		this.lifeTime = DEFAULT_LIFETIME; 
		initLifeTime = DEFAULT_LIFETIME; 
	}
	
	public Paddle(float x, float y, float lifeTime) {
		super(new Texture("paddle.jpg")); 
		paddleSprite = new Sprite(this); 
		extendedPaddleSprite = new Sprite(new Texture("extendedPaddle.jpg")); 
		
		setX(x); 
		setY(y); 
		this.lifeTime = lifeTime; 
		initLifeTime = lifeTime; 
	}
	
	public void tick(float deltaTime) {
		lifeTime -= deltaTime; 
	}
	
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		
	}
	
	
	public void extendPaddle() {
		if (this.getTexture() != extendedPaddleSprite.getTexture()) {
			float x = getX(); 
			float y = getY(); 
			y -= (extendedPaddleSprite.getHeight() - paddleSprite.getHeight()) / 2;
			this.set(extendedPaddleSprite);
			this.setX(x); 
			this.setY(y); 
		}
	}
	
	public void shortenPaddle() {
		if (this.getTexture() != paddleSprite.getTexture()) {
			float x = getX(); 
			float y = getY(); 
			this.set(paddleSprite);
			this.setX(x); 
			this.setY(y); 
		}
	}
	
}
