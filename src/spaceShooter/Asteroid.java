package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Asteroid extends AbstrakObjek {
	
	public Asteroid(int x, int y, int velX, int velY) {
		super(x, y, velX*Game.difficulty, velY);
		id = 3;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 24, 24);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 24, 24);
	}
	
}
