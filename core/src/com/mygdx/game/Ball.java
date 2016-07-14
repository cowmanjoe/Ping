package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ball extends Sprite{
	private float dx; 
	private float dy; 
	
	private PaddleController paddleController; 
	
	public Ball(float x, float y, float dx, float dy, PaddleController pc) {
		super(new Texture("ball.jpg")); 
		
		setX(x); 
		setY(y); 
		this.dx = dx; 
		this.dy = dy; 
		paddleController = pc; 
	}
	
	public void tick(float deltaTime) {
		if (getX() + getWidth() >= GdxGame.width) {
			dx = -Math.abs(dx); 
			paddleController.attemptAddPaddle();
		}
		if (getY() <= 0) dy = Math.abs(dy);
		if (getY() + getHeight() >= GdxGame.height) dy = -Math.abs(dy);
		
		
		
		for (Paddle p : paddleController.getPaddles()) {
			if (getX() <= p.getX() + p.getWidth() && 
					getX() > p.getX() && 
					getY() <= p.getY() + p.getHeight() && 
					getY() + getHeight() >= p.getY()) {
				dx = Math.abs(dx); 
				dy = dy + 2f * (getY() + (getHeight() / 2) - (p.getY() + (p.getHeight() / 2)));
				paddleController.clearPaddles();
				break; 
			}
		}
		
		//if (goingRight()) paddleController.setPaddleReady(true);
		//if (goingLeft()) paddleController.setPaddleReady(false);
		
		
		setX(getX() + dx * deltaTime); 
		setY(getY() + dy * deltaTime); 
		
		
	}
	
	public float getWidth() {
		return getTexture().getWidth(); 
	}
	
	public float getHeight() {
		return getTexture().getHeight(); 
	}
	
	private boolean goingRight() {
		return dx > 0; 
	}
	
	private boolean goingLeft() {
		return dx < 0; 
	}
}
