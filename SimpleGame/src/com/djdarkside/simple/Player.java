package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.djdarkside.simple.GameObject;
import com.djdarkside.simple.ID;

public class Player extends GameObject {
	
	Random random = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	@Override
	public void update() {
		x += velX;
		y += velY;		
		x = Game.clamp(x, 0, Game.getWindowWidth() - 32);
		y = Game.clamp(y, 0, Game.getWindowHeight() - 32);
		handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 32, 32, 0.05f, handler));
		collision();
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}
	@Override
	public Rectangle getBounds() {		
		return new Rectangle(x, y, 32,32);
	}
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.BasicEnemy) { //tempObject is now basic enemy
				if (getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.health -= 2;
				}
			}			
		}
	}
}
