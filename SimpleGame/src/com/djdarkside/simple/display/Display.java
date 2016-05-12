package com.djdarkside.simple.display;

import com.djdarkside.simple.SpriteSheet;
import com.djdarkside.simple.Sprite;


public class Display {
	
	public int width;
	public int height;
	public int[] pixels;
	private final int ALPHA_COL = 0xffff00ff;
	
	public Display(int width, int height) {		
		this.width = width;
		this.height = height;
		pixels = new int [width * height];		
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet) {
		for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
			}
		}
	}
	public void renderSprite(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = col  ;
			}
		}
	}
}
