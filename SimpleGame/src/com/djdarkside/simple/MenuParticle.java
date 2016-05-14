package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	
	private int red =  r.nextInt(255);
	private int green =  r.nextInt(255);
	private int blue =  r.nextInt(255);
	private Color col;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = r.nextInt((7 - -7) + -7);
		velY = r.nextInt((7 - -7) + -7);
		if (velX == 0) velX = 1;
		if (velY == 0) velY = 1;
		col = new Color(red, green, blue);
	}

	@Override
	public void update() {
		x += velX;
		y += velY;		
		if (x <= 0 || x >= Game.getWindowWidth() - 16) velX *= -1;
		if (y <= 0 || y >= Game.getWindowHeight() - 16) velY *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.02f, handler));
	}
	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);		
	}
	@Override
	public Rectangle getBounds() {		
		return new Rectangle((int)x, (int)y, 16,16);
	}

}
