package com.djdarkside.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.djdarkside.simple.Game.STATE;
import com.djdarkside.simple.display.Display;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();	
		
		if (game.gameState == STATE.Menu) {
			//Play Button
			if (mouseOver(mx, my, 510, 175, 250, 64)) {  //Play Button
				game.gameState = STATE.Select;
				AudioPlayer.getSound("click").play(1, 0.05f);
				return;
			}		
			//Quit Button
			if (mouseOver(mx, my, 510, 475, 250, 64)) {  //Quit Button
				AudioPlayer.getSound("click").play(1, 0.05f);
				System.exit(0);
			}
			//Options Button
			if (mouseOver(mx, my, 510, 325, 250, 64)) {  //Options Button
				game.gameState = STATE.Options;
				AudioPlayer.getSound("click").play(1, 0.05f);
			}
		}
		
		if (game.gameState == STATE.Select) {
			//Normal
			if (mouseOver(mx, my, 510, 175, 250, 64)) {  //Play Button
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.getWindowWidth() / 2, Game.getWindowHeight() / 2, ID.Player, handler));	
				handler.clearEnemy();
				handler.addObject(new BasicEnemy(Game.getWindowWidth() - 50, Game.getWindowHeight() -50, ID.BasicEnemy, handler));	
				game.diff = 0;
				AudioPlayer.getSound("click").play(1, 0.05f);
			}		
			//Hard Button
			if (mouseOver(mx, my, 510, 325, 250, 64)) {  //Options Button
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.getWindowWidth() / 2, Game.getWindowHeight() / 2, ID.Player, handler));	
				handler.clearEnemy();
				handler.addObject(new HardEnemy(Game.getWindowWidth() - 50, Game.getWindowHeight() -50, ID.BasicEnemy, handler));	
				game.diff = 1;
				AudioPlayer.getSound("click").play(1, 0.05f);
			}
			//back Button
			if (mouseOver(mx, my, 510, 475, 250, 64)) { 
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("click").play(1, 0.05f);
				return;
			}
		}
		
			//Back Button
		if (game.gameState == STATE.Options) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("click").play(1, 0.05f);
				return;
			}			
		}
		//Resets
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 510, 325, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("click").play(1, 0.05f);
			}			
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
			Font font3 = new Font("arial", 1, 75);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Options", 400, 110);			
			g.setFont(font3);
			g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);			
			g.setFont(font2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		} else if (game.gameState == STATE.End) {
			Font font = new Font("arial", 1, 75);
			Font font2 = new Font("Century", 1, 30);
			Font font3 = new Font("arial", 1, 75);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Over", Game.getWindowWidth() / 2 - 200, 110);			
			g.setFont(font3);
			g.drawString("You lost with a score of: " + hud.getScore(), Game.getWindowWidth() / 2 - 475, 200);			
			g.setFont(font2);
			g.drawRect(510, 325, 250, 64);
			g.drawString("Try Again", 560, 367);
		} else if (game.gameState == STATE.Select) {
			Font font = new Font("arial", 1, 75);
			Font font2 = new Font("Century", 1, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 400, 110);
			g.setFont(font2);
			g.drawRect(510, 175, 250, 64);
			g.drawString("Normal", 595, 217);
			g.drawRect(510, 325, 250, 64);
			g.drawString("Hard", 575, 367);
			g.drawRect(510, 475, 250, 64);
			g.drawString("Back", 597, 517);
		}
	}
	
	public void update() {
		
	}

}
