package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int health = 100;
	
	public void update() {
		health--;
		health = Game.clamp(health, 0, 100);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 400, 24);
		g.setColor(Color.green);
		g.fillRect(15, 15, health * 4, 24);
		g.setColor(Color.white);
		g.drawRect(15, 15, 400, 24);
	}
	
	
}
