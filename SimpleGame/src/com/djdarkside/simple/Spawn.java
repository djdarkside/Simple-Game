package com.djdarkside.simple;

import java.awt.Graphics;
import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = -1;
	private Random r = new Random();
	private Coin coin;
	private Game game;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void genCoin() {	
		coin = new Coin(Game.getWindowWidth() + r.nextInt(5000),r.nextInt(Game.getWindowHeight()),ID.Coin,handler);
		handler.addObject(coin);		
	}
	
	public void render(Graphics g) {

	}
	
	public void update() {
		//The begining level for both
		if (scoreKeep == -1) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
			genCoin();
			
		}
		scoreKeep++;			
		if (scoreKeep >= 600) { 
			scoreKeep = 0;	
			hud.setLevel(hud.getLevel() + 1);	
			handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
			genCoin();
			genCoin();
			genCoin();
			genCoin();
			if (game.diff == 0) {					
				if (hud.getLevel() == 2) {	
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) {
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 4) {
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
					//handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					genCoin();
				} else if (hud.getLevel() == 10) {
					handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					genCoin();
					genCoin();
				}
			} else if (game.diff == 1) {
				if (hud.getLevel() == 2) {	
					genCoin();
					genCoin();
					handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 3) {
					genCoin();
					genCoin();
					handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 4) {
					genCoin();
					genCoin();
					genCoin();
					handler.addObject(new SmartEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 5) {
					genCoin();
					handler.addObject(new EnemyBoss(Game.getWindowWidth() / 2 - 50, 20, ID.EnemyBoss, handler));

				}
			}
		} 			
	}
}	

