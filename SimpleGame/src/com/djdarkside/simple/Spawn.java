package com.djdarkside.simple;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	private Coin coin;	
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void update() {
		scoreKeep++;		
		if (scoreKeep >= 200) {
			scoreKeep = 0;
			
			hud.setLevel(hud.getLevel() + 1);	
			
			if (hud.getLevel() == 2) {
				coin = new Coin(r.nextInt(Game.getWindowWidth()),r.nextInt(Game.getWindowHeight()),ID.Coin,handler);
				handler.addObject(coin);
			} else if (hud.getLevel() == 3) {
				handler.removeObject(coin);
				coin = new Coin(r.nextInt(Game.getWindowWidth()),r.nextInt(Game.getWindowHeight()),ID.Coin,handler);
				handler.addObject(coin);
				handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 4) {
				handler.removeObject(coin);
				handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
			} else if (hud.getLevel() == 5) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.SmartEnemy, handler));
			} else if (hud.getLevel() == 6) {
				handler.addObject(new EnemyBoss(Game.getWindowWidth() / 2, -65, ID.EnemyBoss, handler));
			}
		}
	}	
}
