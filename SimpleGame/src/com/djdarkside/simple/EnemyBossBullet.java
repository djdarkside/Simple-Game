package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

	private Handler handler;
	Random r = new Random();
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = r.nextInt((5 - -5) + -5);
		velY = 5;
	}

	@Override
	public void update() {
		x += velX;
		y += velY;		
		//if (x <= 0 || x >= Game.getWindowWidth() - 16) velX *= -1;
		//if (y <= 0 || y >= Game.getWindowHeight() - 16) velY *= -1;
		
		if (y >= Game.getWindowHeight()) handler.removeObject(this);
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 16, 16, 0.02f, handler));
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 16, 16);		
	}
	@Override
	public Rectangle getBounds() {		
		return new Rectangle((int)x, (int)y, 16,16);
	}
}