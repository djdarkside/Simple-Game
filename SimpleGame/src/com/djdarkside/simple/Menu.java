package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.djdarkside.simple.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();		
		if (mouseOver(mx, my, 510, 175, 250, 64)) {  //Play Button
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.getWindowWidth() / 2, Game.getWindowHeight() / 2, ID.Player, handler));	
		}		
		
		if (mouseOver(mx, my, 510, 475, 250, 64)) {  //Quit Button
			System.exit(0);
		}
		
		if (mouseOver(mx, my, 510, 325, 250, 64)) {  //Options Button
			game.gameState = STATE.Options;
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else return false;			
		} else return false;
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 75);
			Font font2 = new Font("Century", 1, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Simple Game", 400, 110);
			g.setFont(font2);
			g.drawRect(510, 175, 250, 64);
			g.drawString("Play", 595, 217);
			g.drawRect(510, 325, 250, 64);
			g.drawString("Options", 575, 367);
			g.drawRect(510, 475, 250, 64);
			g.drawString("Quit", 597, 517);
		} else if (game.gameState == STATE.Options) {
			Font font = new Font("arial", 1, 75);
			Font font2 = new Font("Century", 1, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Options", 400, 110);
		}
	}
	
	public void update() {
		
	}

}
