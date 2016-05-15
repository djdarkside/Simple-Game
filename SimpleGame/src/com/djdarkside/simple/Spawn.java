package com.djdarkside.simple;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 100;
	private Random r = new Random();
	private Coin coin;
	private Game game;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void genCoin() {	
		coin = new Coin(Game.getWindowWidth() + r.nextInt(1000),r.nextInt(Game.getWindowHeight()),ID.Coin,handler);
		handler.addObject(coin);		
	}
	
	public void update() {
		scoreKeep++;	
		if (scoreKeep >= 600) {
			scoreKeep = 0;			
			genCoin();
			genCoin();
			hud.setLevel(hud.getLevel() + 1);
			
			if (game.diff == 0) {
				
				handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
				if (hud.getLevel() == 2) {	
					genCoin();
					genCoin();
					handler.addObject(new BasicEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
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
				}
			} else if (game.diff == 1) {
				handler.addObject(new HardEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.FastEnemy, handler));
				if (hud.getLevel() == 2) {	
					genCoin();
					genCoin();
					handler.addObject(new HardEnemy(r.nextInt(Game.getWindowWidth()- 50), r.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
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
				}
			}
		} 			
	}
}	

