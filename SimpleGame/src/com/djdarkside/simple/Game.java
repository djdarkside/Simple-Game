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
	private static int width = 640;
	private static int height = width / 16 * 9;
	private static int scale = 2;	
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
	
	
	public Game() {		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		Dimension size = new Dimension(getWindowWidth(), getWindowHeight());
		setPreferredSize(size);
		display = new Display(width, height);		
		hud = new HUD();
		handler.addObject(new Player(getWindowWidth() / 2,getWindowHeight() / 2, ID.Player));		
		random = new Random();
		handler.addObject(new BasicEnemy(random.nextInt(getWindowWidth()),random.nextInt(getWindowHeight()), ID.BasicEnemy));	
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
		//Renders Player objects
		handler.render(g);
		//Renders the HUD
		hud.render(g);
	//End Graphics
		g.dispose();
		buffer.show();		
	}
	
	public static int clamp(int var, int min, int max) {
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
		return width * scale;
	}
	public static int getWindowHeight() {
		return height * scale;
	}
}
