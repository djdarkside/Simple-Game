package com.djdarkside.simple.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.djdarkside.simple.Game;
import com.djdarkside.simple.Game.STATE;
import com.djdarkside.simple.GameObject;
import com.djdarkside.simple.Handler;
import com.djdarkside.simple.ID;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { tempObject.setVelY(-5); keyDown[0] = true; }
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { tempObject.setVelY(5); keyDown[1] = true; }
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { tempObject.setVelX(5); keyDown[2] = true; }
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { tempObject.setVelX(-5); keyDown[3] = true; }
			}
		}		
		if (key == KeyEvent.VK_P) {
			if (game.gameState == STATE.Game) {
			if (Game.paused) Game.paused = false;
			else Game.paused = true;
			}		
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false;
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[2] = false;
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[3] = false;						
				if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0); //vertical movement				
				if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0); //horizontal movement
			}
		}
	}
}
