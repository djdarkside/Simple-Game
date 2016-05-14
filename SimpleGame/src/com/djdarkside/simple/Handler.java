package com.djdarkside.simple;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void update() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemy() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			if (tempObj.getID() == ID.Player) {
				object.clear();
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player((int)tempObj.getX(), (int)tempObj.getY(), ID.Player, this));	
				}
			}
		}
	}
}
