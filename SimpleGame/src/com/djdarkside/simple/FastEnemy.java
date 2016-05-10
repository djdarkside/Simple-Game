package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {
	
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 2;
		velY = 7;
	}

	@Override
	public void update() {
		x += velX;
		y += velY;		
		if (x <= 0 || x >= Game.getWindowWidth() - 16) velX *= -1;
		if (y <= 0 || y >= Game.getWindowHeight() - 16) velY *= -1;
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
