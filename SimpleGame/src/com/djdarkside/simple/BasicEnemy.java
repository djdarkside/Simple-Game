package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GameObject {

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		velX = 5;
		velY = 5;

	}

	@Override
	public void update() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.getWindowWidth() - 16) velX *= -1;
		if (y <= 0 || y >= Game.getWindowHeight() - 16) velY *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 16, 16);
		
	}

}
