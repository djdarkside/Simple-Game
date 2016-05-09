package com.djdarkside.simple.display;

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

}
