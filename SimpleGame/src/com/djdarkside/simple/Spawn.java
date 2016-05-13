package com.djdarkside.simple;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 100;
	private Random r = new Random();
	private Coin coin;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void genCoin() {	
		coin = new Coin(r.nextInt(Game.getWindowWidth()),r.nextInt(Game.getWindowHeight()),ID.Coin,handler);
		handler.addObject(coin);		
	}
	
	public void update() {
		scoreKeep++;	
		if (scoreKeep >= 200) {
			scoreKeep = 0;			
			hud.setLevel(hud.getLevel() + 1);
			if (hud.getLevel() == 2) {	
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
				genCoin();
				genCoin();
				genCoin();
				//handler.addObject(new EnemyBoss(Game.getWindowWidth() / 2, -65, ID.EnemyBoss, handler));
			}
		} 			
	}
}	

