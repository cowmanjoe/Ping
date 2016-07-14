package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img; 
	
	List<Paddle> paddles; 
	Ball ball; 
	
	public static int width; 
	public static int height; 
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth(); 
		height = Gdx.graphics.getHeight(); 
		
		batch = new SpriteBatch();
		
		paddles = new ArrayList<Paddle>(); 
		
		paddles.add(new Paddle(50, height / 2, Paddle.DEFAULT_LIFETIME)); 
		paddles.add(new Paddle(width - 50, height / 2, Paddle.DEFAULT_LIFETIME)); 
		
		ball = new Ball(width / 2, height / 2, -50f, 100f); 
	}

	@Override
	public void render () {
		tick(); 
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Paddle p : paddles) {
			p.draw(batch);
		}
		ball.draw(batch);
		batch.end();
	}
	
	private void tick() {
		float dt = Gdx.graphics.getDeltaTime(); 
		
		ball.tick(dt, paddles);
		
		List<Paddle> paddlesToRemove = new ArrayList<Paddle>(); 
		
		for (Paddle p : paddles) {
			p.tick(dt);
			if (p.isDead())
				paddlesToRemove.add(p); 
		}
		
		paddles.removeAll(paddlesToRemove); 
		
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			Paddle p = new Paddle(50, height - Gdx.input.getY() - Paddle.HEIGHT / 2, Paddle.DEFAULT_LIFETIME); 
			paddles.add(p); 
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
}
