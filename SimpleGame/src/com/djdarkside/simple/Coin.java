package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Coin extends GameObject {
	
	private BufferedImage coinsp = null;
	Random r = new Random();
	Handler handler;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	

	public Coin(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		try {
			coinsp = loader.loadImage("/coin.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(coinsp, (int)x, (int)y, 32,32,null);
		//g.setColor(Color.yellow);
		//g.fillOval((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
