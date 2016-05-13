package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Coin extends GameObject {
	
	private BufferedImage coinsp = null;
	Random r = new Random();
	Handler handler;
	
	Image image;
	
	BufferedImageLoader loader = new BufferedImageLoader();

	public Coin(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		try {
			coinsp = loader.loadImage("/coin.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		velX = -1;
	}	

	@Override
	public void update() {
		x += velX;
	}

	@Override
	public void render(Graphics g) {
        URL url = this.getClass().getResource("/coin1.gif");
        image = new ImageIcon(url).getImage();
        g.drawImage(image, (int)x, (int)y, 32, 32, null);
        //g.drawImage(coinsp, (int)x, (int)y, 32,32,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
