package com.djdarkside.simple;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	
	private int width;
	private int height;
	public int[] pixels;
	
	public static SpriteSheet sheet = new SpriteSheet("/res/bg.png", Game.getWindowWidth(),Game.getWindowHeight());
		
	public SpriteSheet(String path, int width, int height ) {
		this.path = path;
		SIZE = -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int [SPRITE_WIDTH * SPRITE_HEIGHT];
		load();		
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
