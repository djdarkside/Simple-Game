package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.djdarkside.simple.GameObject;
import com.djdarkside.simple.ID;

public class Player extends GameObject {
	
	Random random = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);
	}
	@Override
	public void update() {
		x += velX;
		y += velY;		
		x = Game.clamp(x, 0, Game.getWindowWidth() - 32);
		y = Game.clamp(y, 0, Game.getWindowHeight() - 32);
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}

}
