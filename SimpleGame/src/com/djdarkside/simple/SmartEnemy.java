package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player) {
				player = handler.object.get(i);
			}
		}		
	}

	@Override
	public void update() {
		x += velX;
		y += velY;		
		
		float diffX = x - player.getX() - 6;
		float diffY = y - player.getY() - 6;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velX = (float) ((-1/distance) * diffX);
		velY = (float) ((-1/distance) * diffY);
		
		if (x <= 0 || x >= Game.getWindowWidth() - 16) velX *= -1;
		if (y <= 0 || y >= Game.getWindowHeight() - 16) velY *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.005f, handler));
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);		
	}
	@Override
	public Rectangle getBounds() {		
		return new Rectangle((int)x, (int)y, 16,16);
	}
}

