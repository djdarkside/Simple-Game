package com.djdarkside.simple.display;

import com.djdarkside.simple.SpriteSheet;
import com.djdarkside.simple.display.*;

public class Display {
	
	public int width;
	public int height;
	public int[] pixels;
	
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

}
