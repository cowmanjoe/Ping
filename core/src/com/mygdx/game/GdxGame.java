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
	
	private static GdxGame instance; 
	
	private SpriteBatch batch;
	private Texture img; 
	
	//List<Paddle> paddles; 
	private PaddleController paddleController; 
	
	private Ball ball; 
	
	private boolean gameOver; 
	
	private static Score score; 
	
	private List<Powerup> powerups; 
	
	public static int width; 
	public static int height; 
	
	private GdxGame() {
		super(); 
	}
	
	public static GdxGame getInstance() {
		if (instance == null) {
			instance = new GdxGame(); 
		}
		
		return instance; 
	}
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth(); 
		height = Gdx.graphics.getHeight(); 
		
		score = new Score(20, 20, "Score: "); 
		
		gameOver = false; 
		
		batch = new SpriteBatch();
		
		//paddles = new ArrayList<Paddle>(); 
		paddleController = new PaddleController(PaddleController.DEFAULT_COOLDOWN); 
		
		
		ball = new Ball(width / 2, height / 2, 200f, 1f, paddleController); 
		
		powerups = new ArrayList<Powerup>(); 
		powerups.add(new Powerup(100, 100, PowerupType.TWO_PADDLES));
	}

	@Override
	public void render () {
		tick(); 
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		paddleController.draw(batch);
		ball.draw(batch);
		
		for (Powerup pu : powerups) {
			pu.draw(batch);
		}
		
		score.draw(batch);
		batch.end();
	}
	
	private void tick() {
		float dt = Gdx.graphics.getDeltaTime(); 
		
		paddleController.tick(dt);
		
		if (ball.getX() < 0) gameOver = true; 
		
		if (gameOver) app.exit(); 
		
		ball.tick(dt);
		
		
		
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
	
	public void setScore(int score) {
		this.score.setScore(score); 
	}
	
	public void increaseScore(int amount) {
		this.score.increaseBy(amount);
	}
}
