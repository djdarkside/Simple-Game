package com.djdarkside.simple;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import com.djdarkside.simple.display.Display;
import com.djdarkside.simple.input.KeyInput;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -6398779311230661552L;	
	private static int scale = 2;	
	private static int width = 640 * scale;
	private static int height = width / 16 * 9;
	private static String title = "Simple Game";
	public static boolean paused = false;
	public int diff = 0; //0 = normal, 1 = Hard
	private Thread thread;
	private boolean running = false;
	private JFrame frame;
	private Display display;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	private Handler handler;
	private Random random;
	private HUD hud;	
	private Spawn spawn;
	private Menu menu;
	private BufferedImage background = null;
	private SpriteSheet sheet;
	private Coin coin;
	private float scroll = 0;	
	public enum STATE {
		Menu, Select, Game, Options, End
	};	
	public static STATE gameState = STATE.Menu;
	
	public Game() {		
		handler = new Handler();
		random = new Random();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop(1, 0.08f);
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		display = new Display(width, height);		
		spawn = new Spawn(handler, hud, this);
		BufferedImageLoader loader = new BufferedImageLoader();		
		
		if (gameState == STATE.Game) {	
			handler.addObject(new Player(width / 2, height / 2, ID.Player, handler));				
			//handler.addObject(new BasicEnemy(random.nextInt(width), random.nextInt(height), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(random.nextInt(width), random.nextInt(height), ID.MenuParticle, handler));
			}
		}
		
		try {
			background = loader.loadImage("/bg.png");  // Adds Background
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//Start Game Loop
	public void run() {		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;		
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			if (running) render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;				
			}
		}
		stop();
	}	
//End Game Loop
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}	
	
	public synchronized void stop() {	
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public void update() {
		if (!paused) {
			scroll -= 0.2;		
			
			if (gameState == STATE.Game) {
				hud.update();
				spawn.update();	
				handler.update();
				
				if (HUD.health <= 0) {
					HUD.health = 100;
					gameState = STATE.End;
					handler.clearEnemy();
				}	

			} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Options) {
				menu.update();	
				handler.update();
			} 
		}		
	}
	
	public void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		display.clear();
		Graphics g = buffer.getDrawGraphics();	
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	//Graphics Below		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(background, (int)scroll, 0, null); //renders the background
		handler.render(g);   	//Renders Player objects
		
		if (paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 350, 350);
		}
		if (gameState == STATE.Game) {
			hud.render(g);		    //Renders the HUD
			if (hud.getLevel() == 1) {
				Font font = new Font("Century", 1, 45);
				g.setFont(font);
				g.drawString("Use WSAD keys to collect coins and dodge enemys", (int)scroll + 200, 200);
			}
		} else if (gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.End || gameState == STATE.Select) {
			menu.render(g);			
		}
	//End Graphics
		g.dispose();
		buffer.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max)	return max;
		else if (var <= min) return min;
		else return var;
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.frame = new JFrame();
		//game.frame.setUndecorated(true);
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
	
	public static int getWindowWidth() {
		return width;
	}
	
	public static int getWindowHeight() {
		return height;
	}
}
