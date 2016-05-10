package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int health = 100;
	private int greenValue = (int) (((float)health/100f)*255f);
	private int score = 0;
	private int level = 1;
	
	public void update() {
		health = (int) Game.clamp(health, 0, 100);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		greenValue = health * 2;
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 400, 24);		
		g.setColor(new Color(255-greenValue, greenValue, 0));
		g.fillRect(15, 15, health * 4, 24);		
		g.setColor(Color.white);
		g.drawRect(15, 15, 400, 24);
		g.drawString("Level: " + level, 15, 12);
		g.drawString("Score: " + score, 72, 12);		
	}	
	
	public void score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
