package spaceShooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -8641822620884405078L;
	public static final int WIDTH = 683;
	public static final int HEIGHT = 384;
	
	private boolean isRunning;
	public static LinkedList<AbstrakObjek> objekPemain;
	public static LinkedList<AbstrakObjek> objekMusuh;
	public static Handler handlerPemain;
	public static Handler handlerMusuh;
	public static HUD hud;
	private Spawner spawner;
	private Menu menu;
	
	private Thread threadUtama;
	private Thread tickPemain;
	private Thread tickMusuh;
	
	public static long startTime;
	public static String namaPemain = "";
	public static int difficulty = 1;
	
	public enum STATE {
		Menu,
		Game,
		GameOver,
		Leaderboard
	}
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		menu = new Menu(this);
		this.addMouseListener(menu);
		initialize();
		new Window(WIDTH, HEIGHT, "2D Space Shooter", this);
	}
	
	public void initialize() {
		objekPemain = new LinkedList<AbstrakObjek>();
		objekMusuh = new LinkedList<AbstrakObjek>();
		handlerPemain = new Handler(objekPemain, objekMusuh);
		handlerMusuh = new Handler(objekMusuh, objekPemain);
		hud = new HUD();
		spawner = new Spawner();
		if(gameState == STATE.Game) {
			handlerPemain.addObjek(new Pemain(100, 168));
			this.addKeyListener(new KeyInput(handlerPemain));
		}
		handlerPemain.addObjek(new Tembok(-64, -64, Game.WIDTH+128, 10));
		handlerPemain.addObjek(new Tembok(-64, -64, 10, Game.HEIGHT+128));
		handlerPemain.addObjek(new Tembok(-64, Game.HEIGHT+64, Game.WIDTH+128, 10));
		handlerPemain.addObjek(new Tembok(Game.WIDTH+64, -64, 10, Game.HEIGHT+128));
		handlerMusuh.addObjek(new Tembok(-64, -64, Game.WIDTH+128, 10));
		handlerMusuh.addObjek(new Tembok(-64, -64, 10, Game.HEIGHT+128));
		handlerMusuh.addObjek(new Tembok(-64, Game.HEIGHT+64, Game.WIDTH+128, 10));
		handlerMusuh.addObjek(new Tembok(Game.WIDTH+64, -64, 10, Game.HEIGHT+128));
	}
	
	public synchronized void start() {
		isRunning = true;
		threadUtama = new Thread(this);
		threadUtama.start();
	}
	
	public synchronized void stop() {
		try {
			threadUtama.join();
			isRunning = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick(delta);
				delta = 0;
			}
			if(isRunning) {
				render();
			}
		}
		stop();
	}
	
	private void tick(double delta) {
		tickPemain = new TickThread(delta, handlerPemain);
		tickMusuh = new TickThread(delta, handlerMusuh);
		tickPemain.start();
		tickMusuh.start();
		try {
			tickPemain.join();
			tickMusuh.join();
			handlerPemain.collision();
			handlerMusuh.collision();
			handlerPemain.destroyCollided();
			handlerMusuh.destroyCollided();
		} catch (InterruptedException e) {}
		spawner.spawn();
		if(gameState == STATE.Game) {
			hud.update();
		} else if(gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Leaderboard) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handlerPemain.render(g);
		handlerMusuh.render(g);
		if(gameState == STATE.Game) {
			hud.render(g);
		} else if(gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Leaderboard) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
}