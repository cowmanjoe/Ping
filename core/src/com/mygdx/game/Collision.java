package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Collision {
	
	
	public static boolean intersect(Sprite b1, Sprite b2) {
		if (b1.getX() <= b2.getX() + b2.getWidth() && 
				b1.getX() + b1.getWidth() >= b2.getX() && 
				b1.getY() <= b2.getY() + b2.getHeight() && 
				b1.getY() + b1.getHeight() >= b2.getY())
			return true; 
		
		return false; 
	}
}
