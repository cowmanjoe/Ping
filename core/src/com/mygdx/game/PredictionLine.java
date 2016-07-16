package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PredictionLine {
	
	private Ball ball; 
	
	private int x1; 
	private int x2; 
	private int y1; 
	private int y2; 
	
	
	public PredictionLine(Ball ball) {
		this.ball = ball; 
	}
	
	
	
	public void tick() {
		//integer representing first wall that it will hit
		// 0 is left, 1 is top, 2 is right, 3 is bottom
		int firstWall; 
		float timeToXWall; 
		float timeToYWall; 
		
		x1 = (int) ball.getX(); 
		y1 = (int) ball.getY(); 
		
		if (ball.goingRight()) {
			timeToXWall = Math.abs((GdxGame.getInstance().width - ball.getX()) / ball.getXVelocity()); 
		}
		else {
			timeToXWall = Math.abs((ball.getX() / ball.getXVelocity())); 
		}
		
		if (ball.getYVelocity() > 0) {
			timeToYWall = Math.abs((GdxGame.getInstance().height - ball.getY()) / ball.getYVelocity()); 
		} else {
			timeToYWall = Math.abs(ball.getY() / ball.getYVelocity()); 
		}
		
		
		if (timeToXWall < timeToYWall) {
			//ball will hit right
			if (ball.goingRight()) {
				x2 = GdxGame.getInstance().width; 
				y2 = (int)(timeToXWall * ball.getYVelocity() + ball.getY()); 
			} 
			//ball will hit left
			else {
				x2 = 0; 
				y2 = (int)(timeToXWall * ball.getYVelocity() + ball.getY()) ;
			}
		} else {
			//ball will hit top
			if (ball.getYVelocity() > 0) {
				x2 = (int) (timeToYWall * ball.getXVelocity() + ball.getX());
				y2 = GdxGame.getInstance().height; 
			}
			//ball will hit bottom
			else {
				x2 = (int) (timeToYWall * ball.getXVelocity() + ball.getX());
				y2 = 0; 
			}
		}
		
		//Move line ends to match middle of the ball
		x1 += ball.getWidth() / 2; 
		x2 += ball.getWidth() / 2; 
		y1 += ball.getHeight() / 2; 
		y2 += ball.getHeight() / 2;
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		
		
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.line(x1,  y1, x2, y2);
		shapeRenderer.end(); 
	}
	
}
