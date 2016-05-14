package com.djdarkside.simple;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public String path;
	public int SIZE;
	public int SPRITE_WIDTH, SPRITE_HEIGHT;
	
	private int width;
	private int height;
	public int[] pixels;
	private BufferedImage image;
	

	SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage loadImage(String path) throws IOException {		
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}	
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
		return img;		
	}
	
		
	public SpriteSheet(String path, int width, int height ) {
		this.path = path;
		SIZE = -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int [SPRITE_WIDTH * SPRITE_HEIGHT];
		try {
			loadImage(path);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void load() {
		try {
			System.out.print("Trying to load: " + path + "...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" success!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int [width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(" failed!");
		}
	}	
}
