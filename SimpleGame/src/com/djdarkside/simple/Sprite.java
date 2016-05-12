package com.djdarkside.simple;

public class Sprite {

	private int x;
	private int y;
	private int width;
	private int height;
	public int[] pixels;
	protected SpriteSheet sheet;
	public final int SIZE;
	
	public static Sprite sprite = new Sprite(SpriteSheet.sheet, 0, 0, 50, 50);
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(SpriteSheet sheet, int x, int y, int width, int height) {
		SIZE = width;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}
	
	public void load() {
		for (int y = 0; y <= height; y++) {
			for (x = 0; x <= width; x++) {
				pixels[x + y * width] = sheet.pixels[((x + this.x) + (y + this.y)) * sheet.SPRITE_WIDTH];
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
