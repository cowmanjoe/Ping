package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GdxGame extends ApplicationAdapter {
	
	private static GdxGame instance; 
	
	private Random random; 
	
	private SpriteBatch batch;
	private Texture img; 
	
	//List<Paddle> paddles; 
	private PaddleController paddleController; 
	
	private Ball ball; 
	
	private boolean gameOver; 
	
	private static Score score; 
	
	private List<Powerup> powerups; 
	
	private PredictionLine predictionLine; 
	
	private ShapeRenderer shapeRenderer; 
	
	private PowerupText powerupText; 
	
	public int width; 
	public int height; 
	
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
		
		random = new Random(); 
		
		score = new Score(20, 20, "Score: "); 
		
		gameOver = false; 
		
		batch = new SpriteBatch();
		
		//paddles = new ArrayList<Paddle>(); 
		paddleController = new PaddleController(PaddleController.DEFAULT_COOLDOWN); 
		
		
		ball = new Ball(width / 2, height / 2, 200f, 1f, paddleController); 
		
		predictionLine = new PredictionLine(ball); 
		
		powerups = new ArrayList<Powerup>(); 
		
		
		shapeRenderer = new ShapeRenderer(); 
		
		powerupText = new PowerupText(); 
		
		spawnPowerup(PowerupType.THREE_PADDLES, 300, 0); 
	}

	@Override
	public void render () {
		tick(); 
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		predictionLine.render(shapeRenderer);
		
		batch.begin();
		
		
		paddleController.draw(batch);
		
		ball.draw(batch);
		
		for (Powerup pu : powerups) {
			pu.draw(batch);
		}
		
		score.draw(batch);
		
		powerupText.draw(batch);
		
		
		
		batch.end();
	}
	
	private void tick() {
		float dt = Gdx.graphics.getDeltaTime(); 
		
		paddleController.tick(dt);
		
		if (ball.getX() < 0) gameOver = true; 
		
		if (gameOver) Gdx.app.exit(); 
		
		ball.tick(dt);
		
		predictionLine.tick(); 
		
		tickPowerups(dt); 
		
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			Paddle p = new Paddle(Paddle.DEFAULT_X, height - Gdx.input.getY() - Paddle.HEIGHT / 2, Paddle.DEFAULT_LIFETIME); 
			paddleController.attemptAddPaddle(p);
		}
		
	}
	
	private void tickPowerups(float deltaTime) {
		List<Powerup> powerupsToRemove = new ArrayList<Powerup>(); 
		for (Powerup p : powerups) {
			p.tick(deltaTime);
			if (p.isRemoved()) powerupsToRemove.add(p); 
		}
		powerups.removeAll(powerupsToRemove);
		
		boolean twoPaddles = false; 
		boolean threePaddles = false; 
		boolean longPaddle = false; 
		
		
		for (Powerup p : powerups) {
			if (p.isActive()) {
				switch (p.getType()) {
					case TWO_PADDLES: 
						twoPaddles = true; 
						break;
						
					case THREE_PADDLES:
						threePaddles = true;
						break;
					
					case LONG_PADDLE:
						longPaddle = true; 
						break;
						
					default: 
						throw new UnsupportedOperationException("Powerup not recognized.");
				}
				if (!powerupText.hasPowerup(p)) {
					powerupText.addPowerup(p);
				}
			}
			
		}
		
		if (threePaddles) {
			paddleController.setMaxPaddles(3);
			
		} else if (twoPaddles) {
			paddleController.setMaxPaddles(2);
		} else {
			paddleController.setMaxPaddles(1);
		}
		
		if (longPaddle) {
			paddleController.extendPaddles();
		}
		else {
			paddleController.shortenPaddles();
		}
		
		// There is a 1/spawnChance chance every frame of a powerup spawning
		int spawnChance = 50; 
		
		if (random.nextInt(spawnChance) == 0) spawnPowerup(); 
		
		
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		this.width = width; 
		this.height = height; 
	}
	
	
	public void setScore(int score) {
		this.score.setScore(score); 
	}
	
	public void increaseScore(int amount) {
		this.score.increaseBy(amount);
	}
	
	public Ball getBall() {
		return ball; 
	}
	
	//spawns random powerup in random location
	private void spawnPowerup() {
		int x = random.nextInt(width - 200) + 100;
		int y = random.nextInt(height); 
		
		int pick = random.nextInt(PowerupType.values().length);
		PowerupType powerupType = PowerupType.values()[pick]; 
		
		spawnPowerup(powerupType, x, y); 
	}
	
	private void spawnPowerup(PowerupType powerupType, int x, int y) {
		powerups.add(new Powerup(x, y, powerupType));
	}
}
