package com.djdarkside.simple;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
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
	
	public Game() {		
		handler = new Handler();
		random = new Random();
		this.addKeyListener(new KeyInput(handler));
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		display = new Display(width, height);		
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		handler.addObject(new Player(width / 2, height / 2, ID.Player, handler));	
		handler.addObject(new BasicEnemy(random.nextInt(Game.getWindowWidth()- 50), random.nextInt(Game.getWindowHeight()- 50), ID.BasicEnemy, handler));
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
		handler.update();
		hud.update();
		spawn.update();
	}
	
	public void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		display.clear();
		Graphics g = buffer.getDrawGraphics();	
	//Graphics Below		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);   	//Renders Player objects
		hud.render(g);		    //Renders the HUD
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
