package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame extends ApplicationAdapter {
	private Application app; 
	
	SpriteBatch batch;
	Texture img; 
	
	//List<Paddle> paddles; 
	PaddleController paddleController; 
	
	Ball ball; 
	
	private boolean gameOver; 
	
	public static int width; 
	public static int height; 
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth(); 
		height = Gdx.graphics.getHeight(); 
		
		gameOver = false; 
		
		batch = new SpriteBatch();
		
		//paddles = new ArrayList<Paddle>(); 
		paddleController = new PaddleController(PaddleController.DEFAULT_COOLDOWN); 
		
		
		ball = new Ball(width / 2, height / 2, 200f, 1f); 
	}

	@Override
	public void render () {
		tick(); 
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		paddleController.draw(batch);
		ball.draw(batch);
		batch.end();
	}
	
	private void tick() {
		float dt = Gdx.graphics.getDeltaTime(); 
		
		paddleController.tick(dt);
		
		if (ball.getX() < 0) gameOver = true; 
		
		if (gameOver) app.exit(); 
		
		ball.tick(dt, paddleController.getPaddles());
		
		if (ball.goingRight()) paddleController.setPaddleReady(true);
		if (ball.goingLeft()) paddleController.setPaddleReady(false);
		
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			Paddle p = new Paddle(Paddle.DEFAULT_X, height - Gdx.input.getY() - Paddle.HEIGHT / 2, Paddle.DEFAULT_LIFETIME); 
			paddleController.attemptAddPaddle(p);
		}
		
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		GdxGame.width = width; 
		GdxGame.height = height; 
	}
	
	public void setApp(Application app) {
		this.app = app; 
	}
}
